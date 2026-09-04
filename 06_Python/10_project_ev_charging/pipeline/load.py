"""
Load - DB 에 적재한다. 가공하지 않는다.
"""

import os
import time

from .config import connect, CHUNK_SIZE

COLS = ["log_id", "station_id", "user_id", "start_time", "end_time",
        "energy_kwh", "fee", "payment_method"]
COL_SQL = ", ".join(f"`{c}`" for c in COLS)
PH = ", ".join(["%s"] * len(COLS))

UPSERT = f"""
INSERT INTO charging_log ({COL_SQL}) VALUES ({PH})
ON DUPLICATE KEY UPDATE
    station_id=VALUES(station_id), user_id=VALUES(user_id),
    start_time=VALUES(start_time), end_time=VALUES(end_time),
    energy_kwh=VALUES(energy_kwh), fee=VALUES(fee),
    payment_method=VALUES(payment_method)
"""

_SCHEMA_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "schema.sql")


def ensure_table(conn):
    with open(_SCHEMA_PATH, encoding="utf-8") as f:
        ddl = f.read()
    with conn.cursor() as cur:
        cur.execute(ddl)
    conn.commit()


def to_db(df, logger, chunk=CHUNK_SIZE):
    """UPSERT 로 적재하고 신규·갱신 건수를 돌려준다."""
    data = df[COLS].astype(object).where(df[COLS].notna(), None)
    rows = [tuple(r) for r in data.itertuples(index=False)]

    conn = connect()
    ensure_table(conn)

    inserted = updated = 0
    start = time.perf_counter()
    try:
        for i in range(0, len(rows), chunk):
            part = rows[i:i + chunk]
            with conn.cursor() as cur:
                affected = cur.executemany(UPSERT, part)
            conn.commit()

            if affected > len(part):
                updated += affected - len(part)
                inserted += len(part) * 2 - affected
            else:
                inserted += affected
    except Exception:
        conn.rollback()
        raise
    finally:
        conn.close()

    return inserted, updated, time.perf_counter() - start


def verify(df, logger):
    """적재 후 검증. 행 수만 보면 놓치므로 집계값도 대조한다."""
    conn = connect()
    with conn.cursor() as cur:
        cur.execute("""
            SELECT COUNT(*) AS n, COUNT(DISTINCT station_id) AS stations,
                   COUNT(DISTINCT user_id) AS users,
                   SUM(fee) AS fee_sum, SUM(energy_kwh) AS energy_sum,
                   MIN(start_time) AS min_t, MAX(start_time) AS max_t
            FROM charging_log
        """)
        db = cur.fetchone()
    conn.close()

    expected = {
        "행 수": (len(df), db["n"]),
        "충전소 수": (df["station_id"].nunique(), db["stations"]),
        "이용자 수": (df["user_id"].nunique(), db["users"]),
        "총 매출": (int(df["fee"].sum()), int(db["fee_sum"])),
        "총 충전량": (round(float(df["energy_kwh"].sum()), 2), round(float(db["energy_sum"]), 2)),
        "최소 날짜": (str(df["start_time"].min()), str(db["min_t"])),
        "최대 날짜": (str(df["start_time"].max()), str(db["max_t"])),
    }

    all_ok = True
    for name, (exp, act) in expected.items():
        ok = str(exp) == str(act)
        all_ok &= ok
        logger.info(f"  {'OK  ' if ok else 'FAIL'} {name:<10}{exp} / {act}")

    return all_ok
