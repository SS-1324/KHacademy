"""
    DB연동 및 조심할 것 3가지
"""

import pandas as pd
import pymysql

from _db import connect, get_engine, HOST, PORT, USER, NAME, PASSWORD

pd.set_option("display.width", 130)

#db연동순서및 방식은 Java와 다르지 않다.

# pymysql의 connection객체를 만들어서 반환받는다.
conn = connect()

# cursor() -> Cursor객체를 만들어줌
# cur : execute(sql), execute(sql, (값1,값2...)) 를통해 sql을 전달 후 응답을 받는 객체
with conn.cursor() as cur:
    cur.execute("SELECT VERSION() AS v, DATABASE() AS db")
    row = cur.fetchone() # 한 행짜리 결과: fetchone, 결과가 여러행 : fetchall
print(f" 접속 : {USER}@{HOST}:{PORT}/{NAME}")
print(f" 서버 : {row['v']} / 현재 DB : {row['db']}")

# autocommit이 기본적으로 꺼저있다.
with conn.cursor() as cur:
    cur.execute("DROP TABLE IF EXISTS demo_commit")
    cur.execute("CREATE TABLE demo_commit (id INT, memo VARCHAR(50))")
conn.commit()

c1 = connect()
with c1.cursor() as cur:
    cur.execute("INSERT INTO demo_commit VALUES (1, '커밋 안함')")
    cur.execute("SELECT COUNT(*) AS n FROM demo_commit")
    print(f"insert후에 바로 확인 : {cur.fetchone()['n']}행")
c1.close()

c2 = connect()
with c2.cursor() as cur:
    cur.execute("SELECT COUNT(*) AS n FROM demo_commit")
    print(f"새로운 커넥션에서 확인 : {cur.fetchone()['n']}행")
c2.close()
"""
    커밋하지않고 커넥션반납시에도 따로 에러가 나지 않기때문에
    트랜잭션관리를 잘 해줘야한다.
"""

c3 = connect()
with c3:
    with c3.cursor() as cur:
        cur.execute("INSERT INTO demo_commit VALUES (2, '두번째 데이터')")
        
# c3.open -> c3 커넥션의 연결상태 확인
closed = not c3.open

c4 = connect()
with c4.cursor() as cur:
    cur.execute("SELECT COUNT(*) AS n FROM demo_commit")
    n_after = cur.fetchone()['n']
c4.close()

print(f"종료후 상태 : {closed}")
print(f"종료후 데이터 : {n_after}")

"""
    close()를하지 않아도 with가 종료되면 연결을 닫는다.
    연결을 자동으로 닫을 시 커밋하지않고 닫는다.
"""

c5 = connect()
try:
    with c5.cursor() as cur:
        cur.execute("INSERT INTO demo_commit VALUES (3, '명시적 커밋')")
    c5.commit()
finally:
    c5.close()

c6 = connect()
with c6.cursor() as cur:
    cur.execute("SELECT COUNT(*) AS n FROM demo_commit")
    n_after = cur.fetchone()['n']
c6.close()

print(f"종료후 상태 : {closed}")
print(f"종료후 데이터 : {n_after}")


#파라미터 바인딩
#executemany(sql, [(),(),()...]) : 같은 sql을 값만 바꿔서 여러번 전달.
with conn.cursor() as cur:
    cur.execute("DROP TABLE IF EXISTS demo_param")
    cur.execute("CREATE TABLE demo_param (code VARCHAR(10), price INT)")
    cur.executemany(
        "INSERT INTO demo_param VALUES(%s, %s)",
        [("G0001", 24000), ("G0002", 51000), ("G0003", 10000)],
    )
conn.commit()

"""
    executemany내부 변수는 타입과 무관한게 항상 %s다 -> ?와 같다.
"""
with conn.cursor() as cur:
    cur.execute("SELECT * FROM demo_param WHERE price > %s", (2000,))
    print(f" {len(cur.fetchall())}행")

# 파라미터는 한개를 넘기더라도 튜플로 넣는다.
# 물론 변수가 하나면 문자열을 그대로 전달해도 동작한다.
# 다만 변수가 여러개인데 문자열 하나 전달시 문자열을 알아서 쪼개사용한다 (문제발생요소)

# DictCursor와 Pandas
plain = pymysql.connect(host=HOST, port=PORT, user=USER,
                        passwd=PASSWORD, database=NAME, charset="utf8mb4")

#cursor의 기본 반환값은 튜플이다.
with plain.cursor() as cur:
    cur.execute("SELECT * FROM demo_param LIMIT 1")
    print(f" 기본커서 : {cur.fetchone()}")
plain.close()

with conn.cursor() as cur:
    cur.execute("SELECT * FROM demo_param LIMIT 1")
    print(f" Dict커서 : {cur.fetchone()}")

# SQLAlchemy를 사용하는 가장 큰 이유는 Pandas와의 연계성이다.
# create_engine : 커넥션풀을 만들어서 관리해주는 engine을 생성
engine = get_engine()

# 가능하다면 DB에서 정렬 후 데이터를 가져오는게 빠르다.
df = pd.read_sql("SELECT * FROM demo_param ORDER BY price DESC", engine)
print(df.to_string(index=False))

with conn.cursor() as cur:
    for t in ["demo_commit", "demo_param"]:
        cur.execute(f"DROP TABLE IF EXISTS {t}")
conn.commit()
conn.close()

print("테이블 정리 완료")