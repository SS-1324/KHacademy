"""
    이상치 탐색
"""

import os
import pandas as pd

from _load import step_path

pd.set_option("display.width", 130)

if not os.path.exists(step_path("_step1.pkl")):
    raise SystemExit("먼저 _step1.pkl이 data에 있는지 확인해 주세요.")

df = pd.read_pickle(step_path("_step1.pkl")).sort_values(["code", "date"]).reset_index(drop=True)
print(f"타입, 중복 처리까지 끝난 상태 불러오기 : {len(df)}행\n")

#이상치 확인
print(df["close"].describe().round(0).to_string())

# median = 중앙값     mean = 평균
med = df.groupby("code")["close"].transform("median")
print(f"""
    종목 중앙값과 비교
     중앙값의 50배 초과 : {(df['close'] > med * 50).sum()}건
     중앙값의 5%미만 : {(df['close'] < med * 0.05).sum()}건
    
    나름의 기준으로 너무 큰값이나 너무 작은값을 찾는다.
""")

# 이상치 탐지
# quantile : 값을 크기순으로 늘어놓았을 때 특정 위치의 값을 구함.
# s.quantile([0.25, 0.75]) 
# 목록을 주면 값 2개짜리 series가 나옴. 왼쪽 변수 둘에 순서대로 풀어서 담김
# IQR(사분위 범위) -> Q3 - Q1 
"""
    IQR규칙 : 어디까지를 정상으로 볼것인가?
    Q1 - 1.5 * IQR 보다 작거나, Q3 + 1.5 * IQR보다 크면 이상치로 본다.
    평균, 표준편차와 달리 극단값 자체에 흔들리지 않기 때문에 이상치 탐지에 사용한다.
"""

q1, q3 = df["close"].quantile([0.25,0.75])
print(f"q1 : {q1}   q3 : {q3}")
#q1 : 12061.0 -> 1사분위 값 (하위 25%)
#q3 : 55106.25 -> 3사분위 값 (75% -> 상위 25%)
iqr = q3 - q1
lo, hi = q1 - 1.5 * iqr, q3 + 1.5 * iqr
global_mask = (df["close"] < lo) | (df["close"] > hi)

print(f" q1={q1}  q3={q3}    IQR={iqr}")
print(f" 정상범위 : {lo} ~ {hi}")
print(f" 이상치 : {global_mask.sum()}건")

"""
    IQR로 이상치를 탐색하는게 부족할 수 있다.
    하안선이 음수가 나옴! -> 극소 이상치를 전혀 잡지 못한다.
    주가는 음수가 될 수 없으니, 이 기준은 의미가 없다.
"""

tiny = df["close"] < med * 0.05
print(f" 중앙값 5%미만 : {tiny.sum()}건")
print(f" 그중 전체 IQR로 잡은 것 : {(tiny & global_mask).sum()}건")

#iloc[0] : 조건을 만족한 행 중 첫번째 위치를 꺼냄
ex = df[tiny].iloc[0]
print(ex)
ex_med = df.loc[df["code"] == ex["code"], "close"].median()
print(f"""
    {ex['code']} : {ex['date'].date()}
    이 종목의 중앙값 : {ex_med}원  
    문제의 행 종가 : {ex['close']}원
    중앙값의 : {ex['close'] / ex_med * 100:.1f}%
    전체  IQR의 하한 : {lo:>.0f}원

    전체 기준으로는 하한선보다 크니가 정상
    하지만 이 종목 입장에서는 하루만에 99%정도 폭락한 값이다.
""")


# 종목별 IQR

def is_outlier(s):
    """
        한 종목의 종가목록을 받아서 같은 길이의 bool mask를 돌려준다.
        각 자리의 True/False로 이상치인가 아닌가의 값을 리턴
    """
    q1, q3 = s.quantile([0.25,0.75])
    iqr = q3 - q1
    lo, hi = q1 - 1.5 * iqr, q3 + 1.5 * iqr
    return (s < lo) | (s > hi)

by_stock = df.groupby("code")["close"].transform(is_outlier)

print(f"전체 기준 : {global_mask.sum()}건")
print(f"종목별 : {by_stock.sum()}건")

"""
종목별 쪽이 훨씬 작게 잡힌다.
전체만 잡은 건들은 대부분 오탐지다.
비싼 종목의 정상가격을 시장 편균보다 높다는 이유로 이상치로 본것.

반대로 종목별로만 잡은 건수는 대부분 전체로 했을 때 놓친 값들이다.
"""
print(f"전체에는 없는데 종목별로만 잡힌 수 : {(by_stock & ~global_mask).sum()}건")
print(f"전체에만 있는 수 : {(~by_stock & global_mask).sum()}건")
print(f"전체로 했을 때 탐지가 안됬던 이상치 : {by_stock[ex.name]}")

# 논리적인 오류의 이상치
broken = (df["close"] > df["high"]) | (df["close"] < df["low"])
neg_vol = df["volume"] < 0

print(f"최저가, 최고가에서 벚어난 종가 : {broken.sum()}건")
print(f"거래량이 음수인 행 : {neg_vol.sum()}건")

"""
    종가만 조작되었으니 그 행은 종가 > 고가 상태가 된다.
    시가,고가,저가,종가의 관계는 절대 깨질 수 없으므로 조건식 하나로 잡아준다.

    broken = (df["close"] > df["high"]) | (df["close"] < df["low"])

    거래량 음수도 IQR를 맞춰서 돌릴 필요가 없다.
    규칙으로 잡아내준다.
"""

print(f" 둘 다 잡음 :  {(by_stock & broken).sum()}건")
print(f" 통계적이상치 :  {(by_stock & ~broken).sum()}건")
print(f" 논리적이상치 :  {(~by_stock & broken).sum()}건")
# 보완적으로 통계를 통해서 잡고, 도메인 규칙에따라서 또 처리가 필요하다.


# 처리 - NaN으로 표기하고 이후에 처리.

# 시세는 그날의 값이 반드시 있어야하는 데이터다 다만,
# 부합하지 않는다고해서 행자체를 지우면 날짜에 구멍이 생김.
before_max = df["close"].max()
mask = by_stock | broken

#pd.NA : 결측 표시값이다. 값을 지우는대신 모른다.
df.loc[mask, "close"] = pd.NA

df["close"] = pd.to_numeric(df["close"], errors="coerce")
df.loc[df["volume"] < 0, "volume"] = pd.NA # 값이 없거나 모를 때 0으로 넣으면 안됨.

print()
print(f"이상치 -> NaN : {mask.sum()}건")
print(f"거래량 음수 : {neg_vol.sum()}건")
print(f"종가 결측 : {df['close'].isna().sum()}건")

print("====_step2.pkl로 저장====")
df.to_pickle(step_path("_step2.pkl"))
print(f"종가 최대값 : {before_max}")