"""
STEP 8-4 · 적재 검증
    행 수만 보면 놓친다. 집계값을 대조한다.
"""

import pandas as pd

from _config import path
from _db import get_engine

pd.set_option("display.width", 140)

expected = pd.read_pickle(path("_clean_final.pkl"))
engine = get_engine()
actual = pd.read_sql("SELECT * FROM charging_log", engine)

checks = [
    ("행 수", len(expected), len(actual)),
    ("충전소 수", expected["station_id"].nunique(), actual["station_id"].nunique()),
    ("이용자 수", expected["user_id"].nunique(), actual["user_id"].nunique()),
    ("총 매출", int(expected["fee"].sum()), int(actual["fee"].sum())),
    ("총 충전량", round(float(expected["energy_kwh"].sum()), 2), round(float(actual["energy_kwh"].sum()), 2)),
    ("최소 날짜", str(expected["start_time"].min()), str(actual["start_time"].min())),
    ("최대 날짜", str(expected["start_time"].max()), str(actual["start_time"].max())),
]

print(f"{'항목':<12}{'기대값':>22}{'실제값':>22}{'결과':>8}")
print("-" * 66)
all_ok = True
for name, exp, act in checks:
    ok = str(exp) == str(act)
    all_ok &= ok
    print(f"{name:<12}{str(exp):>22}{str(act):>22}{'OK' if ok else 'FAIL':>8}")

print()
print("모든 값 일치 :", all_ok)
