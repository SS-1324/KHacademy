"""
    실습용 데이터 로더.
"""

import pandas as pd
import os

DATA_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data")

RAW_PATH = os.path.join(DATA_DIR, "raw-prices.csv")
ENCODING = "utf-8-sig"

def load(dedup=True):
    df = pd.read_csv(
        RAW_PATH,
        encoding=ENCODING,
        na_values=["N/A","-"], # na_values은 해당 배열에 있는 문자열을 결측(NaN)으로 취급하도록 함.
        thousands=",", # 1,000,000처럼 천단위 콤마가 든 값을 숫자로 가져옴.
    )

    #날짜 형식이 섞어 있을 때 format="mixed"형식으로 가져오면 행마다 따로 추론해서 날짜형식을 가져
    df["date"] = pd.to_datetime(df["date"], format="mixed")

    if dedup:
        # drop_duplicates : subset기준으로 중복데이터 제거
        # keep="first" : 먼저나온값을 남긴다.
        df = df.drop_duplicates(subset=["code", "date"], keep="first")

    # sort_values로 정렬 -> 인덱스가 섞인채로 정렬됨
    # reset_index(drop=True)는 인덱스를 다시 0,1,2,3...로 지정해준다.
    return df.sort_values(["code", "date"]).reset_index(drop=True)
