"""
    groupby와 복사본.
"""

import pandas as pd
from _load import load

pd.set_option("display.width", 130)

df = load()

# agg = 그룹당 한줄

# SQL : select code, AVG(close) from prices group by code 
by_code = df.groupby("code")["close"].mean()
print(by_code.head(4).round(0).to_string())
print(f"결과 : {len(by_code)}행")

#여러개를 한번에 집계
summary = df.groupby("code").agg(
    평균종가=("close", "mean"),
    최고가=("close", "max"),
    거래일수=("date", "count"),
)
print(summary.head(4).round(0).to_string())

"""
agg(
    새로운_열_이름=("계산할_기존_열_이름", "적용할_함수_이름")
)
"""

# transform - 원본과 같은 길이로 맞춰주기 위함.
# 원본 DataFrame과 동일한 행 수(크기)를 유지한 채 결과를 반환 함수
df["code_mean"] = df.groupby("code")["close"].transform("mean")
df["vs_mean"] = df["close"] / df["code_mean"]

# round(2) -> date와같은 열에도 적용되어 에러발생.
shown = df[["code", "date", "close", "code_mean", "vs_mean"]].head(5)
print(shown.round({"close": 2, "code_mean": 2, "vs_mean": 2}).to_string(index=False))


"""
 각 행에 '자기가 속한 그룹의 값'을 붙여준다. 행 수가 줄지 않는다.

 agg : 그룹수만큼 행을 가진다. -> 요약결과를 생성
 transform : 원본과 동일한 행의 수를 가짐. -> 원본 열을 추가할 때
"""

