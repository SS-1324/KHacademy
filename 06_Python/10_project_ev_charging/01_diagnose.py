"""
STEP 1 · 진단
    정제하기 전에 무엇이 얼마나 망가졌는지 파악한다.
"""

import pandas as pd
from _config import path, ENCODING

pd.set_option("display.width", 130)
pd.set_option("display.max_columns", 20)


def read_literal(name):
    """파일에 적힌 그대로 읽는다. (dtype=str, 자동 결측 처리 끔)"""
    return pd.read_csv(path(name), encoding=ENCODING, dtype=str, keep_default_na=False)


regions = read_literal("regions.csv")
stations = read_literal("raw-stations.csv")
logs = read_literal("raw-charging-logs.csv")

print("=" * 60)
print("파일별 행/열 수")
print("=" * 60)
for name, df in [("regions", regions), ("raw-stations", stations), ("raw-charging-logs", logs)]:
    print(f"{name:<20}{df.shape[0]:>8,}행{df.shape[1]:>4}열")

print()
print("=" * 60)
print("dtype (옵션 없이 read_csv 했을 때)")
print("=" * 60)
stations_infer = pd.read_csv(path("raw-stations.csv"), encoding=ENCODING)
logs_infer = pd.read_csv(path("raw-charging-logs.csv"), encoding=ENCODING)
print("[raw-stations]")
print(stations_infer.dtypes.to_string())
print("\n[raw-charging-logs]")
print(logs_infer.dtypes.to_string())

print()
print("=" * 60)
print("문항 1 · station_id 중복")
print("=" * 60)
print(f" 전체 행 : {len(stations)}")
print(f" station_id 고유값 : {stations['station_id'].nunique()}개")
dup_ids = stations.loc[stations.duplicated(subset=['station_id'], keep=False), 'station_id'].unique()
print(f" 중복된 station_id : {sorted(dup_ids)}")
print(f" -> 완전 중복행 : {stations.duplicated().sum()}건, station_id만 중복(내용 다름) : "
      f"{stations.duplicated(subset=['station_id']).sum() - stations.duplicated().sum()}건")

print()
print("=" * 60)
print("문항 2 · charger_type 고유값")
print("=" * 60)
print(f" 원본 고유값({stations['charger_type'].nunique()}종) : {sorted(stations['charger_type'].unique())}")
print(" -> 실제로는 DC급속 / AC완속 2종이어야 함 (대소문자, '완속'단독표기 섞임)")

print()
print("=" * 60)
print("문항 3 · energy_kwh 숫자 변환 실패 값")
print("=" * 60)
bad_energy = pd.to_numeric(logs["energy_kwh"], errors="coerce")
bad_mask = bad_energy.isna() & (logs["energy_kwh"] != "")
print(f" 변환 실패(빈 문자열 제외) : {bad_mask.sum()}건")
print(f" 예시 : {logs.loc[bad_mask, 'energy_kwh'].unique()[:10].tolist()}")
print(f" 빈 문자열(결측) : {(logs['energy_kwh'] == '').sum()}건")

print()
print("=" * 60)
print("컬럼별 결측 수/비율 (원본 표기 그대로 - 빈 문자열 기준)")
print("=" * 60)
for name, df in [("raw-stations", stations), ("raw-charging-logs", logs)]:
    print(f"\n[{name}]")
    for col in df.columns:
        n = (df[col].astype(str).str.strip() == "").sum()
        if n:
            print(f" {col:<16}{n:>6}건  ({n/len(df)*100:5.2f}%)")

print()
print("=" * 60)
print("고유값 목록")
print("=" * 60)
print(f" region_code (stations) : {sorted(stations['region_code'].unique())}")
print(f" payment_method : {sorted(logs['payment_method'].unique())}")

print()
print("=" * 60)
print("station_id / log_id 형식·범위 확인")
print("=" * 60)
print(f" stations station_id 예시 : {stations['station_id'].head(3).tolist()}")
print(f" logs station_id 고유값 개수 : {logs['station_id'].nunique()}개")
print(f" logs station_id 중 stations에 없는 값 : "
      f"{sorted(set(logs['station_id'].unique()) - set(stations['station_id'].unique()))}")

print()
print("=" * 60)
print("날짜 형식 (installed_date / start_time) 섞인 패턴 확인")
print("=" * 60)
print(" installed_date 예시 5개 :", stations["installed_date"].head(5).tolist())
print(" start_time 예시 5개 :", logs["start_time"].head(5).tolist())
