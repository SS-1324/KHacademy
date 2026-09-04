"""
STEP 3 · 충전 기록 정제
    raw-charging-logs.csv (12,400행, 오염) -> logs (12,000행, 정제본)
"""

import pandas as pd

from _config import path, ENCODING

pd.set_option("display.width", 130)

df = pd.read_csv(path("raw-charging-logs.csv"), encoding=ENCODING, dtype=str, keep_default_na=False)
print(f"원본 : {len(df)}행")

# energy_kwh, fee : 콤마를 먼저 지우고 숫자로. 실패(N/A, -, 빈칸)는 결측.
for col in ["energy_kwh", "fee"]:
    df[col] = pd.to_numeric(df[col].str.replace(",", "", regex=False), errors="coerce")

# start_time, end_time : 3종 형식(대시 / 슬래시 / 14자리 압축) 혼재 -> format="mixed"
df["start_time"] = pd.to_datetime(df["start_time"], format="mixed")
df["end_time"] = pd.to_datetime(df["end_time"], format="mixed")

# payment_method : 공백 제거 후 대문자로 통일
df["payment_method"] = df["payment_method"].str.strip().str.upper()

print()
print("=" * 60)
print("타입 정제 결과 (중복 제거 전)")
print("=" * 60)
print(f" energy_kwh 결측 : {df['energy_kwh'].isna().sum()}건")
print(f" fee 결측 : {df['fee'].isna().sum()}건")
print(f" payment_method 고유값 : {sorted(df['payment_method'].unique())}")
print(f" start_time dtype : {df['start_time'].dtype}")

# 중복 제거 : log_id 기준 (타입 정제 후, 맨 마지막에 한다)
before = len(df)
df = df.drop_duplicates(subset=["log_id"], keep="first").reset_index(drop=True)
print()
print(f"중복 제거 : {before} -> {len(df)}행")
print(f" (중복 제거로 함께 빠진 결측 : energy_kwh {before - len(df)}행 중 일부."
      f" 최종 결측 - energy_kwh {df['energy_kwh'].isna().sum()}건 / fee {df['fee'].isna().sum()}건)")

print()
print(df.head(5).to_string(index=False))

df.to_pickle(path("_logs.pkl"))
print("\n_logs.pkl 저장 완료")
