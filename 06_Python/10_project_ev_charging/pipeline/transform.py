"""
Transform - 정제·결합·검증. 저장은 하지 않는다.

STEP 2~6 을 함수로 나눠 담았다. 각 단계마다 로그로 건수를 남긴다.
"""

import unicodedata

import numpy as np
import pandas as pd

from .config import PHYSICAL_MARGIN


def clean_stations(raw, logger):
    """STEP 2 · 충전소 마스터 정제."""
    df = raw.copy()

    df["station_name"] = (
        df["station_name"]
        .map(lambda s: unicodedata.normalize("NFKC", s))
        .str.replace(r"\s+", "", regex=True)
    )

    # 완속은 항상 AC, 급속은 항상 DC 라는 도메인 규칙으로 표기를 통일한다.
    normalized = df["charger_type"].str.replace(r"\s+", "", regex=True).str.upper()
    df["charger_type"] = np.where(normalized.str.contains("급속"), "DC급속", "AC완속")

    df["region_code"] = df["region_code"].str.strip().str.upper()
    df.loc[df["region_code"] == "", "region_code"] = pd.NA

    for col in ["capacity_kw", "unit_price"]:
        digits = df[col].str.replace(r"[^\d]", "", regex=True)
        df[col] = pd.to_numeric(digits, errors="coerce").astype("Int64")

    df["installed_date"] = pd.to_datetime(df["installed_date"], format="mixed")

    before = len(df)
    df = df.drop_duplicates(subset=["station_id"], keep="first").reset_index(drop=True)
    logger.info(f"  [stations] 정제 완료   {before} -> {len(df)}행")

    return df


def clean_logs(raw, logger):
    """STEP 3 · 충전 기록 정제."""
    df = raw.copy()

    for col in ["energy_kwh", "fee"]:
        df[col] = pd.to_numeric(df[col].str.replace(",", "", regex=False), errors="coerce")

    df["start_time"] = pd.to_datetime(df["start_time"], format="mixed")
    df["end_time"] = pd.to_datetime(df["end_time"], format="mixed")
    df["payment_method"] = df["payment_method"].str.strip().str.upper()

    logger.info(f"  [logs] 타입 정제       energy_kwh 결측 {df['energy_kwh'].isna().sum():,} / "
                f"fee 결측 {df['fee'].isna().sum():,}")

    before = len(df)
    df = df.drop_duplicates(subset=["log_id"], keep="first").reset_index(drop=True)
    logger.info(f"  [logs] 중복 제거       {before} -> {len(df)}행")

    return df


def merge_all(logs, stations, regions, logger):
    """STEP 4 · 결합. 매칭 실패는 로그로 남기고 제외한다."""
    merged = logs.merge(stations, on="station_id", how="left",
                         validate="many_to_one", indicator=True)

    fail_mask = merged["_merge"] == "left_only"
    fail_ids = sorted(merged.loc[fail_mask, "station_id"].unique())
    logger.info(f"  [merge] stations 매칭 실패  {fail_mask.sum()}건  {fail_ids}")

    merged = merged[merged["_merge"] == "both"].drop(columns=["_merge"]).reset_index(drop=True)

    full = merged.merge(regions, on="region_code", how="left", validate="many_to_one")
    logger.info(f"  [merge] region_name 결측    {full['region_name'].isna().sum()}건")
    logger.info(f"  [merge] 완료           {len(logs)} -> {len(full)}행")

    return full


def flag_outliers(df, logger):
    """
    STEP 5 · 이상치·논리 검사.

    완속/급속이 섞여 있어 IQR 로는 왜곡되므로, 물리적으로 가능한가를 직접 계산해서 판정한다.
    """
    df = df.copy()
    df["duration_min"] = (df["end_time"] - df["start_time"]).dt.total_seconds() / 60

    rule1 = (df["duration_min"] < 0).fillna(False)
    rule2 = (df["fee"] < 0).fillna(False)

    # nullable Int64(capacity_kw) 와 NaN(energy_kwh) 이 섞이면 비교 결과가 False 대신 pd.NA 가 된다.
    # fillna(False) 를 안 하면 energy_kwh 결측 행이 '이상치'로 잘못 함께 빠진다.
    physical_max = df["capacity_kw"] * (df["duration_min"] / 60) * PHYSICAL_MARGIN
    rule3 = (df["energy_kwh"] > physical_max).fillna(False)

    bad = rule1 | rule2 | rule3
    logger.info(f"  [outlier] ①종료<시작 {rule1.sum()}  ②요금음수 {rule2.sum()}  "
                f"③물리상한초과 {rule3.sum()}  합계(중복제외) {bad.sum()}")

    before = len(df)
    df = df[~bad].reset_index(drop=True)
    logger.info(f"  [outlier] 제거 완료    {before} -> {len(df)}행")

    return df


def handle_missing(df, logger):
    """STEP 6 · 결측 처리. fee 는 복원, energy_kwh 는 근거가 없어 행을 제외."""
    df = df.copy()

    restore_mask = df["fee"].isna() & df["energy_kwh"].notna()
    df.loc[restore_mask, "fee"] = (
        df.loc[restore_mask, "energy_kwh"] * df.loc[restore_mask, "unit_price"]
    ).round(0)
    logger.info(f"  [missing] fee 복원(energy_kwh x unit_price)   {restore_mask.sum()}건")

    before = len(df)
    df = df.dropna(subset=["energy_kwh"]).reset_index(drop=True)
    logger.info(f"  [missing] energy_kwh 결측 제거    {before - len(df)}건  -> {len(df)}행")

    return df


def clean_all(raw, logger):
    """STEP 2~6 을 순서대로 실행해 최종 정제 데이터를 돌려준다."""
    stations = clean_stations(raw["stations"], logger)
    logs = clean_logs(raw["logs"], logger)
    merged = merge_all(logs, stations, raw["regions"], logger)
    no_outliers = flag_outliers(merged, logger)
    final = handle_missing(no_outliers, logger)
    return final


def validate(df, logger):
    """정제가 끝났다는 것을 숫자로 확인한다. 실패하면 여기서 멈춘다."""
    checks = [
        ("start_time 이 datetime", pd.api.types.is_datetime64_any_dtype(df["start_time"])),
        ("log_id 중복 0", df.duplicated(subset=["log_id"]).sum() == 0),
        ("energy_kwh 결측 0", df["energy_kwh"].isna().sum() == 0),
        ("fee 결측 0", df["fee"].isna().sum() == 0),
        ("종료 >= 시작", bool((df["end_time"] >= df["start_time"]).all())),
        ("요금 음수 0", bool((df["fee"] >= 0).all())),
        ("charger_type 2종", set(df["charger_type"].unique()) <= {"AC완속", "DC급속"}),
        ("payment_method 3종", set(df["payment_method"].unique()) <= {"APP", "CARD", "MEMBERSHIP"}),
    ]

    failed = [name for name, ok in checks if not ok]
    for name, ok in checks:
        logger.info(f"  {'OK  ' if ok else 'FAIL'} {name}")

    if failed:
        raise ValueError(f"검증 실패: {failed}")
    return True
