"""
    멱등성과 upsert
"""

import time

import pandas as pd
from _db import connect, get_engine,prices_path, ENCODING

pd.set_option("display.width", 130)

N = 2_000
conn = connect()
engine = get_engine()

df = pd.read_csv(prices_path(), encoding= ENCODING, parse_dates=["date"])
COLS = ["code","date","open","high","low","close","volume","change","changeRate"]

sample = df.head(N)[COLS].copy()
rows = [tuple(r) for r in sample.itertuples(index=False)]

#열 이름과 자리표시자(%s)를 COLS로부터 만들어 둔다.
COL_SQL = ", ".join(f"`{c}`" for c in COLS)
PH = ", ".join(["%s"] * len(COLS))

"""
    우리가 만든 파이프라인 실패할 수 있다.
    네트워크가 끊긴다 / DB가 잠긴다 / DB가 꽉참 / 데이터 불러오기 실패 등...

    실패하면 다시 돌리는 것이 필요하다.
    다만 다시 돌릴 수 있으려면 조건이 있다.

    멱등성
    - 같은 입력으로 몇 번을 실행해도 결과가 같아야한다.
    ex) 1회 실행 -> 2,000행
        2회 실행 -> 2,000행
        2회 실행 -> 4,000행
"""

# 재실행 방법
def make_table(name, unique=False):
    """ 실습용 테이블을 만듬, unique가 True면 UNIQUE(code, date)넣음 """
    with conn.cursor() as cur:
        cur.execute(f"DROP TABLE IF EXISTS {name}")
        uk = ", UNIQUE KEY uk_code_date (code, date)" if unique else ""
        cur.execute(f"""
            CREATE TABLE {name}(
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                code VARCHAR(20) NOT NULL, date DATE NOT NULL,
                open BIGINT,high BIGINT,low BIGINT,close BIGINT,
                volume BIGINT,`change` BIGINT, changeRate DECIMAL(6, 2)
                {uk}
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 
        """)
    conn.commit()

def count(name):
    with conn.cursor() as cur:
        cur.execute(f"SELECT COUNT(*) AS n FROM {name}")
        return cur.fetchone()["n"]

"""
ON DUPLCATE KEY UPDATE -> 없으면 넣고, 있으면 고친다.
INSERT INTO t (열...) VALUES (%s...)
ON DUPLCATE KEY UPDATE 열=VALUES(열), ...

데이터의 중복을 막기위해 넣기 전에 SELECT로 값이 있는지를 환인하는 방식을 사용하면
SQL을 두배사용해야 하므로 확인과 삽입을 동시에 진행하기위해서 사용한다.
"""

# f-string에서 추후에 {t}를 .format(t="테이블명")로 채울 수 있다.
PLAIN = f"INSERT INTO {{t}} ({COL_SQL}) VALUES ({PH})"
UPSERT = PLAIN + """
ON DUPLICATE KEY UPDATE
    open=VALUES(open), high=VALUES(high), low=VALUES(low),
    close=VALUES(close), volume=VALUES(volume),
    `change`=VALUES(`change`), changeRate=VALUES(changeRate)
"""

cases = []

#1. 유니크 제약이 없음
make_table("t_noconstraint", unique=False)
for i in (1,2):
    with conn.cursor() as cur:
        cur.executemany(PLAIN.format(t="t_noconstraint"), rows)
    conn.commit()
n1 = count("t_noconstraint")
cases.append(("제약없음 + 일반 insert", "성공", n1, "데이터가 두배"))

# 2. 유니크 제약만 있고 그대로 insert반복.
make_table("t_unique", unique=True)
with conn.cursor() as cur:
    cur.executemany(PLAIN.format(t="t_unique"), rows)
conn.commit()

try:
    with conn.cursor() as cur:
        cur.executemany(PLAIN.format(t="t_unique"), rows)
    conn.commit()
    err = "성공"
except Exception as e:
    conn.rollback()
    err = type(e).__name__

n2 = count("t_unique")
cases.append(("제약있음 + 일반 insert", err, n2, "재실행 도중 에러발생"))

# 3. 유니크제약 + upsert
make_table("t_upsert", unique=True)
for i in (1,2):
    with conn.cursor() as cur:
        cur.executemany(UPSERT.format(t="t_upsert"), rows)
    conn.commit()
n3 = count("t_upsert")
cases.append(("제약있음 + upsert", "성공", n3, "그냥 다시 돌리면 됨"))


print(f" 같은 {N:,}행을 두번 적재한 결과")
print(f"{'방식':<26}{'2회차':<14}{'최종행수':>10} - 결과")
for name, res, n, note in cases:
    print(f"{name:<26}{res:<14}{n:>10}  {note}")

"""
    1. 제약이 없으면 에러도 없다. -> 중복해서 무한하게 추가된다.
       나중에 집계가 이상해지고, 그때 되돌리고싶어도 쉽지않다.
    
    2. 제약만 넣으면 중복은 막을 수 있지만 재실행이 불가하다.

    3. UPSERT는 이러한 딜레마를 해결해주기 위한 방법이다.
       -> 있으면 갱신, 없으면 새로 추가한다.
    
    upsert가 성립하려면 유니크 제약이 필요하다.
    ON DEULICATE KEY는 식별로 사용하는 것이 아니라 데이터를 수정하기위한 값이다.
"""

# UPSERT 3가지 방식
"""
    INSERT IGNORE : 먼저 들어온 것이 맞을 때 -> 기존 값을 유지
    ON DUPLICATE KEY UPDATE : 대부분의 경우 -> 새 값으로 갱신
    REPLACE INTO : 지우고 다시 삽입 -> 있다고만 알아두자.
"""
make_table("t_replace", unique=True)
with conn.cursor() as cur:
    cur.execute(f"INSERT INTO t_replace ({COL_SQL}) VALUES ({PH})", rows[0])
conn.commit()

with conn.cursor() as cur:
    cur.execute("SELECT id,code,date,close FROM t_replace")
    before = cur.fetchone()

# close만 바꿔서 replace
r = list(rows[0])
r[COLS.index("close")] = 99999
with conn.cursor() as cur:
    cur.execute(f"REPLACE INTO t_replace ({COL_SQL}) VALUES ({PH})", tuple(r))
conn.commit()
with conn.cursor() as cur:
    cur.execute("SELECT id,code,date,close FROM t_replace")
    after_rep = cur.fetchone()

# ON DUPLICATE KEY UPDATE로 동일하게 진행
make_table("t_odku", unique=True)
with conn.cursor() as cur:
    cur.execute(f"INSERT INTO t_odku ({COL_SQL}) VALUES ({PH})", rows[0])
conn.commit()
with conn.cursor() as cur:
    cur.execute(UPSERT.format(t="t_odku"), tuple(r))
conn.commit()
with conn.cursor() as cur:
    cur.execute("SELECT id,code,date,close FROM t_odku")
    after_odku = cur.fetchone()

print(f"""
    REPLACE INTO가 위험한 이유 - ID
    최초 INSERT : {before['id']} - {before['close']}
    REPLACE INTO : {after_rep['id']} - {after_rep['close']}
    ON DUPLICATE KEY : {after_odku['id']} - {after_odku['close']}
""")

"""
    REPLACE는 내부적으로 DELETE 후 INSERT한다.
    그래서 ID를 다시 생성해서 부여한다.
    추후 원래 행을 ID로 조회할 수 없으며, 만약 외래키로 사용됬다면 데이터가 깨진다.
"""

# 신규데이터와 갱신데이터를 구분하여 기록
"""
    MYSQL에서 ON DUPLICATE KEY UPDATE의 결과를 rowcount로 알려준다.
    신규 -> 1
    값이 변경된 갱신 -> 2
    값이 같아 변화 없음 -> 0

    이를 통해서 몇건이 새로 들어오고 몇건이 갱신됐는지 알 수 있다.
"""

def upsert_with_stats(table, data, chunk=1000):
    """ 청크마다 커밋하면서 신규, 갱신 건수를 집계하는 함수 """
    inserted = updated = 0
    start = time.perf_counter()

    for i in range(0, len(data), chunk):
        part = data[i:i+chunk]
        with conn.cursor() as cur:
            affected = cur.executemany(UPSERT.format(t=table), part)
        conn.commit() # 청크마다 커밋

        """
            affected = 신규*1 + 갱신*2 + 변화없음*0
            신규를 i, 값이 바뀐 갱신을 u, 값이 같아 변화 없는 것을 z라고하면
            i + u + z = len(part)
            i + 2u = affected
            미지수가 셋이라서 한 식으로는 못품, 그래서 두 경우로 나눠서 근사값을 구함.
            affected > len(part) : 갱신이 어느정도 있다, z=0이라고보면
                                    u = affected - len(part), 
                                    i = len(part)*2 - affected
            그 외... : 갱신이 없다고 보고 affected를 전부 신규로 센다.
        """
        inserted += max(0, len(part) * 2 - affected) if affected > len(part) else affected
        update += affected - len(part) if affected > len(part) else 0
