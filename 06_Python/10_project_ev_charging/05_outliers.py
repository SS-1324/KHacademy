"""
STEP 5 · 이상치와 논리 검사
    통계 기법(IQR)만으로는 찾을 수 없다. 급속(200kW)과 완속(7kW)이 섞여 있어서
    완속 충전소의 부풀려진 값이 급속의 정상값처럼 보이기 때문이다.
    -> 도메인 규칙(물리적으로 가능한가?)으로 걸러낸다.
"""

import pandas as pd

from _config import path

pd.set_option("display.width", 140)

df = pd.read_pickle(path("_merged.pkl"))
print(f"결합 완료 데이터 : {len(df):,}행")

# 파생 컬럼 : 충전 시간(분)
df["duration_min"] = (df["end_time"] - df["start_time"]).dt.total_seconds() / 60

# ① 종료 시각이 시작 시각보다 앞섬 : 물리적으로 불가능
rule1 = (df["duration_min"] < 0).fillna(False)

# ② 요금이 음수 : 논리적으로 불가능
rule2 = (df["fee"] < 0).fillna(False)

# ③ 충전량이 물리 상한을 넘음 : 용량(kW) x 시간(h) 이 이론적 최대치, 측정오차 감안 10% 여유
#   capacity_kw 가 nullable Int64 라 결측과 섞이면 비교 결과가 False 대신 pd.NA 가 된다.
#   fillna(False) 로 명시하지 않으면 energy_kwh 가 결측인 행이 '이상치'로 잘못 같이 빠진다.
physical_max = df["capacity_kw"] * (df["duration_min"] / 60) * 1.1
rule3 = (df["energy_kwh"] > physical_max).fillna(False)

print()
print("=" * 60)
print("규칙별 탐지 건수")
print("=" * 60)
print(f" ① 종료 <= 시작        : {rule1.sum()}건")
print(f" ② 요금 음수           : {rule2.sum()}건")
print(f" ③ 충전량 > 물리 상한  : {rule3.sum()}건")

bad = rule1 | rule2 | rule3
print(f" 합계(중복 제외)       : {bad.sum()}건")

# 참고 : IQR 로 잡았을 때는 완속/급속이 뒤섞여 왜곡되는지 비교
q1, q3 = df["energy_kwh"].quantile([0.25, 0.75])
iqr = q3 - q1
iqr_mask = (df["energy_kwh"] < q1 - 1.5 * iqr) | (df["energy_kwh"] > q3 + 1.5 * iqr)
print()
print(f" (참고) 전체 IQR 기준 이상치 : {iqr_mask.sum()}건 -> 도메인 규칙(③)과 겹치는 것 : "
      f"{(iqr_mask & rule3).sum()}건 / IQR만 잡은 것 : {(iqr_mask & ~rule3).sum()}건")

# 예시로 하나 확인 : 완속인데 급속 수준으로 충전된 값
example = df.loc[rule3].sort_values("energy_kwh", ascending=False).iloc[0]
print(f"""
 예시(③ 위반) : {example['log_id']} / {example['charger_type']}({example['capacity_kw']}kW)
   충전시간 : {example['duration_min']:.0f}분, 충전량 : {example['energy_kwh']}kWh
   물리적 상한(10% 여유) : {example['capacity_kw'] * (example['duration_min']/60) * 1.1:.1f}kWh
""")

before = len(df)
df = df[~bad].reset_index(drop=True)
print(f"제거 후 행 수 : {before} -> {len(df):,}")

df.to_pickle(path("_clean_step5.pkl"))
print("\n_clean_step5.pkl 저장 완료")
