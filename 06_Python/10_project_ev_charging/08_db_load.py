"""
STEP 8 · DB 적재
    - 8-1 스키마 생성 (schema.sql)
    - 8-2 UPSERT 로 청크 단위 적재, 신규/갱신 건수 로그
    - 8-4 는 이 스크립트가 끝난 뒤 verify_load.py 로 별도 확인한다.

    재실행 검증(8-3)은 이 스크립트를 두 번 그대로 실행해서 확인한다.
    TRUNCATE 를 하지 않는다 - 재실행 안전성을 스스로 깨뜨리는 것이기 때문이다.
"""

import time

import pandas as pd

from _config import path
from _db import connect

CHUNK_SIZE = 2_000
COLS = ["log_id", "station_id", "user_id", "start_time", "end_time",
        "energy_kwh", "fee", "payment_method"]
COL_SQL = ", ".join(f"`{c}`" for c in COLS)
PH = ", ".join(["%s"] * len(COLS))

UPSERT_SQL = f"""
INSERT INTO charging_log ({COL_SQL}) VALUES ({PH})
ON DUPLICATE KEY UPDATE
    station_id=VALUES(station_id), user_id=VALUES(user_id),
    start_time=VALUES(start_time), end_time=VALUES(end_time),
    energy_kwh=VALUES(energy_kwh), fee=VALUES(fee),
    payment_method=VALUES(payment_method)
"""


def ensure_table(conn):
    with open("schema.sql", encoding="utf-8") as f:
        ddl = f.read()
    with conn.cursor() as cur:
        cur.execute(ddl)
    conn.commit()


def load(df, conn, chunk=CHUNK_SIZE):
    data = df[COLS].astype(object).where(df[COLS].notna(), None)
    rows = [tuple(r) for r in data.itertuples(index=False)]

    inserted = updated = 0
    start = time.perf_counter()
    for i in range(0, len(rows), chunk):
        part = rows[i:i + chunk]
        with conn.cursor() as cur:
            affected = cur.executemany(UPSERT_SQL, part)
        conn.commit()

        if affected > len(part):
            updated += affected - len(part)
            inserted += len(part) * 2 - affected
        else:
            inserted += affected

    return inserted, updated, time.perf_counter() - start


if __name__ == "__main__":
    df = pd.read_pickle(path("_clean_final.pkl"))

    conn = connect()
    ensure_table(conn)

    ins, upd, elapsed = load(df, conn)

    with conn.cursor() as cur:
        cur.execute("SELECT COUNT(*) AS n FROM charging_log")
        total = cur.fetchone()["n"]
    conn.close()

    print(f"입력 {len(df):>8,}행")
    print(f"신규 {ins:>8,}행")
    print(f"갱신 {upd:>8,}행")
    print(f"소요 {elapsed:>8.2f}초")
    print(f"테이블 전체 행 수 : {total:,}")
