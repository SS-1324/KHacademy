"""
STEP 2 · 충전소 마스터 정제
    raw-stations.csv (42행, 오염) -> stations (40행, 정제본)
"""

import unicodedata

import numpy as np
import pandas as pd

from _config import path, ENCODING

pd.set_option("display.width", 130)

df = pd.read_csv(path("raw-stations.csv"), encoding=ENCODING, dtype=str, keep_default_na=False)
print(f"원본 : {len(df)}행")

# station_name : 전각 -> 반각(NFKC) 후 공백을 전부 제거(앞뒤 + 중간)
df["station_name"] = (
    df["station_name"]
    .map(lambda s: unicodedata.normalize("NFKC", s))
    .str.replace(r"\s+", "", regex=True)
)

# charger_type : 공백 제거 + 대문자 통일 후, '급속'/'완속' 단어 유무로 DC급속/AC완속 판정
#   완속(느림)은 항상 AC, 급속(빠름)은 항상 DC 라는 도메인 규칙을 이용한다.
normalized = df["charger_type"].str.replace(r"\s+", "", regex=True).str.upper()
df["charger_type"] = np.where(normalized.str.contains("급속"), "DC급속", "AC완속")

# region_code : 대문자로 통일, 빈 값은 결측
df["region_code"] = df["region_code"].str.strip().str.upper()
df.loc[df["region_code"] == "", "region_code"] = pd.NA

# capacity_kw, unit_price : 단위 문자 제거 후 정수로
for col in ["capacity_kw", "unit_price"]:
    digits = df[col].str.replace(r"[^\d]", "", regex=True)
    df[col] = pd.to_numeric(digits, errors="coerce").astype("Int64")

# installed_date : 3종 형식(yyyy-mm-dd / yyyy.mm.dd / yyyymmdd) -> datetime
df["installed_date"] = pd.to_datetime(df["installed_date"], format="mixed")

# 중복 제거 (완전 동일한 행이 station_id 기준으로 중복돼 있었다)
before = len(df)
df = df.drop_duplicates(subset=["station_id"], keep="first").reset_index(drop=True)
print(f"중복 제거 : {before} -> {len(df)}행")

print()
print("=" * 60)
print("정제 결과 확인")
print("=" * 60)
print(f" 행 수 : {len(df)}")
print(f" charger_type 고유값 : {sorted(df['charger_type'].unique())}")
print(f" region_code 고유값({df['region_code'].dropna().nunique()}종) : "
      f"{sorted(df['region_code'].dropna().unique())}  / 결측 {df['region_code'].isna().sum()}건")
print(f" capacity_kw 범위 : {df['capacity_kw'].min()} ~ {df['capacity_kw'].max()}")
print(f" unit_price 범위 : {df['unit_price'].min()} ~ {df['unit_price'].max()}")
print(f" installed_date dtype : {df['installed_date'].dtype}")
print()
print(df.head(5).to_string(index=False))

df.to_pickle(path("_stations.pkl"))
print("\n_stations.pkl 저장 완료")
