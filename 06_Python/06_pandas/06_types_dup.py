"""
    타입 정제와 중복제거
"""

import pandas as pd
from _load import RAW_PATH, ENCODING, step_path

#화면 표시 설정
pd.set_option("display.width", 130)
pd.set_option("display.max_columns", 20)

df = pd.read_csv(RAW_PATH, encoding=ENCODING)
print(f"원본 : {len(df)}행")

NUM_COLS = ["open", "high", "low", "close", "volume", "change", "changeRate"]

#숫자변환 - 순서중요
print("1. 콤마를 지우지 않고 변환")
naive = pd.to_numeric(df["close"], errors="coerce") # errors="raise" 기본값으로 하나라도 변환이 안되면 멈춤.
print(f" close 결측 : {naive.isna().sum()}건")

#콤마를 먼저 지우고 변환.
proper = pd.to_numeric(
    df["close"].astype(str).str.replace(",","", regex=False), errors="coerce"
)
print(f" close 결측 : {proper.isna().sum()}건")
print(f" 차이 : {naive.isna().sum() - proper.isna().sum()}건")

for col in NUM_COLS:
    df[col] = pd.to_numeric(
        df[col].astype(str).str.replace(",","", regex=False), errors="coerce"
    )

print(f"전체 숫자 열 변환 후 dtypes :")
print(df[NUM_COLS].dtypes.to_string())

print(f"\n변환 후 결측 : ")
for col in NUM_COLS:
    n = df[col].isna().sum()
    if n:
        print(f"{col} : {n}건")

# 날짜 형식변환
raw_dates = df["date"].astype(str)
dot = raw_dates.str.contains(r"\.", regex=True)
eight = raw_dates.str.len() == 8
print(f" yyyy-mm-dd 형식 : {(~dot & ~eight).sum()}건 예 : {raw_dates[~dot & ~eight].iloc[0]}")
print(f" yyyy.mm.dd 형식 : {dot.sum()}건 예 : {raw_dates[dot].iloc[0]}")
print(f" yyyymmdd 형식 : {eight.sum()}건 예 : {raw_dates[eight].iloc[0]}")

# parse_dates으로 변경시 형식이 섞여있다면 str로 변경
# pd.to_datetime(s, format="mixed")

df["date"] = pd.to_datetime(df["date"], format="mixed")
print(f" dtype : {df['date'].dtype}")
print(f" 변환 실패 : {df['date'].isna().sum()}건")

print(f" 주말 : {(df['date'].dt.dayofweek >= 5).sum()}건")


#중복제거
dup = df.duplicated(subset=["code", "date"]).sum()
print(f" (code, date) 중복 : {dup}건")

# keep="first" -> 첫행만 남김
#      "last"  -> 마지막행 남김
#      False   -> 둘다 제
df = df.drop_duplicates(subset=["code", "date"], keep="first").reset_index(drop=True)
print(f" 중복 제거 후 : {len(df)}행")

# to_pickle : df.to_pickle(경로) -> 파일로 저장함. 리턴값x
#             pd.read_pickle(경로) -> 저장한 DataFrame을 그대로 돌려줌.
print("====df 저장=====")
df.to_pickle(step_path('_step1.pkl'))