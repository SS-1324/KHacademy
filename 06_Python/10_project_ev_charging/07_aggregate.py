"""
STEP 7 · 집계
    정제가 끝났는지 숫자로 확인한다.
"""

import pandas as pd

from _config import path

pd.set_option("display.width", 140)

df = pd.read_pickle(path("_clean_final.pkl"))

print("=" * 60)
print("1. 전체 요약")
print("=" * 60)
print(f" 행 수         : {len(df):,}")
print(f" 충전소 수     : {df['station_id'].nunique():,}")
print(f" 이용자 수     : {df['user_id'].nunique():,}")
print(f" 총 충전량     : {df['energy_kwh'].sum():,.2f} kWh")
print(f" 총 매출       : {df['fee'].sum():,.0f} 원")
print(f" 평균 충전시간 : {df['duration_min'].mean():,.1f} 분")

print()
print("=" * 60)
print("2. 지역별 집계 (매출 내림차순)")
print("=" * 60)
by_region = df.groupby("region_name").agg(
    건수=("log_id", "count"),
    충전량=("energy_kwh", "sum"),
    매출=("fee", "sum"),
).sort_values("매출", ascending=False)
print(by_region.round(1).to_string())
top_region = by_region.index[0]
print(f"\n 매출 1위 지역 : {top_region} ({by_region.iloc[0]['매출']:,.0f}원)")

print()
print("=" * 60)
print("3. 충전 타입별 집계 (검산용)")
print("=" * 60)
by_type = df.groupby("charger_type").agg(
    건수=("log_id", "count"),
    평균충전량=("energy_kwh", "mean"),
    평균시간=("duration_min", "mean"),
)
print(by_type.round(1).to_string())
print("""
 급속이 완속보다 짧은 시간에 더 많이 충전하는 것이 정상이다.
 이 관계가 뒤집혔다면 어딘가에서 잘못 정제한 것이다.
""")

print("=" * 60)
print("4. 결제수단별 건수")
print("=" * 60)
print(df["payment_method"].value_counts().to_string())
