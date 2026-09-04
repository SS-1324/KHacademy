"""
심화 1 · ETL 분리 실행/검증
    pipeline/ 패키지(extract -> transform -> validate -> load)를 두 번 돌려서
    재실행 안전성을 확인한다.
"""

from _db import connect
from pipeline.pipeline import run


conn = connect()
with conn.cursor() as cur:
    cur.execute("DROP TABLE IF EXISTS charging_log")
conn.commit()
conn.close()

print("=== 1회차 실행 (빈 테이블) ===")
print("=" * 60)
ok1 = run()

print("=" * 60)
print("=== 2회차 실행 (재실행) ===")
print("=" * 60)
ok2 = run()

conn = connect()
with conn.cursor() as cur:
    cur.execute("SELECT COUNT(*) AS n FROM charging_log")
    n = cur.fetchone()["n"]
    cur.execute("SELECT COUNT(DISTINCT station_id) AS c FROM charging_log")
    codes = cur.fetchone()["c"]
conn.close()

print("=" * 60)
print("=== 검증 ===")
print("=" * 60)
checks = [
    ("1회차 성공", ok1),
    ("2회차 성공", ok2),
    ("최종 행 수 11,459", n == 11459),
    ("충전소 수 40", codes == 40),
]
for name, ok in checks:
    print(f"{name} : {'통과' if ok else '실패'}")
