"""
STEP 4 · 결합
    charging_logs --(station_id)--> stations --(region_code)--> regions
"""

import pandas as pd

from _config import path, ENCODING

pd.set_option("display.width", 140)

logs = pd.read_pickle(path("_logs.pkl"))
stations = pd.read_pickle(path("_stations.pkl"))
regions = pd.read_csv(path("regions.csv"), encoding=ENCODING)

print(f"logs      : {len(logs):,}행")
print(f"stations  : {len(stations):,}행")
print(f"regions   : {len(regions):,}행")

# 1) logs -> stations : how='left', validate='many_to_one', indicator=True 로 매칭 실패를 확인
merged = logs.merge(
    stations, on="station_id", how="left", validate="many_to_one", indicator=True
)
print()
print("=" * 60)
print("1차 결합 (logs -> stations)")
print("=" * 60)
print(f" 결합 후 행 수 : {len(merged):,} (변화 없음 : {len(merged) == len(logs)})")
print(merged["_merge"].value_counts().to_string())

fail_mask = merged["_merge"] == "left_only"
fail_station_ids = sorted(merged.loc[fail_mask, "station_id"].unique())
print()
print(f" 매칭 실패 station_id : {fail_station_ids}")
print(f" 매칭 실패 건수 : {fail_mask.sum()}건")

"""
확인 문항 1, 2
    logs 에는 있지만 stations 마스터에는 없는 station_id (ST900, ST901, ST999) 이다.
    실무에서는 이런 값을 '고아 레코드(orphan record)' 라고 부른다.
    - 폐업/철거된 충전소이거나, 마스터 등록이 누락된 신규 충전소이거나, 오타일 수 있다.
    - 원인을 알 수 없으므로 일단 제외하고, 운영팀에 별도로 확인 요청하는 것이 맞다.
"""

# 매칭 실패 행 제외
merged = merged[merged["_merge"] == "both"].drop(columns=["_merge"]).reset_index(drop=True)
print()
print(f" 매칭 실패 제외 후 행 수 : {len(merged):,}")

# 2) stations -> regions : region_code 로 region_name 을 붙인다
full = merged.merge(
    regions, on="region_code", how="left", validate="many_to_one"
)

print()
print("=" * 60)
print("2차 결합 (+ regions)")
print("=" * 60)
print(f" 결합 후 행 수 : {len(full):,} (변화 없음 : {len(full) == len(merged)})")
print(f" region_name 결측 : {full['region_name'].isna().sum()}건")

"""
확인 문항 3
    지역명이 결측인 이유 : STEP2 에서 station 중 2곳이 region_code 자체가 빈 값(결측)이었다.
    그 2개 충전소에서 발생한 충전 기록 전부가 region_name 을 못 붙이고 결측이 된다.
"""
blank_region_stations = stations.loc[stations["region_code"].isna(), "station_id"].tolist()
print(f" region_code 가 비어있던 충전소 : {blank_region_stations}")
print(f" 그 충전소들의 로그 건수 : {full['station_id'].isin(blank_region_stations).sum()}건")

full.to_pickle(path("_merged.pkl"))
print("\n_merged.pkl 저장 완료")
