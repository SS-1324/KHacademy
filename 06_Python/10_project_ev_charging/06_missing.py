"""
STEP 6 · 결측 처리
    fee 결측    : 충전량 x 단가로 복원 가능하므로 복원한다.
    energy_kwh 결측 : 복원할 근거가 없으므로 해당 행을 제외한다.

    "채울 수 있는 근거가 있는가" 가 판단 기준이다. 평균으로 때우는 것과는 다르다.
"""

import pandas as pd

from _config import path

pd.set_option("display.width", 140)

df = pd.read_pickle(path("_clean_step5.pkl"))
print(f"이상치 제거까지 끝난 데이터 : {len(df):,}행")

print()
print("현재 결측 :")
print(f" energy_kwh : {df['energy_kwh'].isna().sum()}건")
print(f" fee        : {df['fee'].isna().sum()}건")

# fee 복원 : 충전량(energy_kwh) x 단가(unit_price)
before_fee_na = df["fee"].isna().sum()
restored = df["fee"].isna() & df["energy_kwh"].notna()
df.loc[restored, "fee"] = (df.loc[restored, "energy_kwh"] * df.loc[restored, "unit_price"]).round(0)

print()
print(f"fee 복원 : {restored.sum()}건 (energy_kwh x unit_price)")
print(f" 복원 후에도 남은 fee 결측 : {df['fee'].isna().sum()}건 "
      f"(= energy_kwh 도 함께 결측이라 복원 불가능한 것)")

# energy_kwh 결측 : 복원 불가 -> 행 제외
before = len(df)
df = df.dropna(subset=["energy_kwh"]).reset_index(drop=True)
print()
print(f"energy_kwh 결측 제거 : {before - len(df)}건")
print(f"최종 행 수 : {len(df):,}")

print()
print("최종 결측 확인 :")
print(f" energy_kwh : {df['energy_kwh'].isna().sum()}건")
print(f" fee        : {df['fee'].isna().sum()}건")

df.to_pickle(path("_clean_final.pkl"))
print("\n_clean_final.pkl 저장 완료")
