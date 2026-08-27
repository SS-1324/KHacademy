"""
    결측 처리
"""

import os
import pandas as pd

from _load import step_path

pd.set_option("display.width", 130)

if not os.path.exists(step_path("_step2.pkl")):
    raise SystemExit("먼저 _step2.pkl이 data에 있는지 확인해 주세요.")

df = pd.read_pickle(step_path("_step2.pkl")).sort_values(["code", "date"]).reset_index(drop=True)
print(f"이상치 표기까지 끝난 상태 불러오기 : {len(df)}행\n")
print("현재 결측 : ")
for col in ["open", "high", "low", "close", "volume"]:
    n = df[col].isna().sum()
    if n:
        print(f"{col}:{n}건  ({n/len(df) * 100:.2f}%)")


#결측처리 전략 3가지
# 삭제 : dropna(subset=, how=, thresh=) -> 삭제할 행 없이도 분석이 가능한가?
# 대치 : fillna(값/ 평균 / 중앙값) -> 대표값으로 적절한것이 있는가?
# 보간 : interpolate() -> 시계열 - 앞뒤 값 사이를 잇는다.

"""
    dropna : 결측이 있는 행을 걸러냄
    fillna : 결측자리를 지정한 값으로 채움
    interpolate: 앞뒤 값 사이를 직선으로 이어 빈칸을 추정
"""

# 보간(중간값을 추정해서 채워 넣는다)은 반드시 종목별로 한다.
edge = df.index[df["code"] != df["code"].shift()][1]
demo = df.loc[edge - 2: edge + 2, ["code", "date", "close"]].copy()
demo.loc[edge, "close"] = pd.NA
demo["close"] = pd.to_numeric(demo["close"], errors="coerce")
print("종목이 바뀌는 지점에 결측이 있다고 하자:")
print(demo.to_string(index=False))

wrong = demo["close"].interpolate()
# transform에 lambda를 넘기는 형태이다.
# df.groupby("code")["close"].transform(lambda s: sinterpolate())
# 그룹 하나의 series가 s로 들어가고, 돌려준 series가 그 그룹의 자리에 채워진다.
# 원본과 길이가 같은 series를 반환해서 옆에 그대로 대입한다.
right = demo.groupby("code")["close"].transform(lambda s: s.interpolate())

print(f" w :\n {wrong.to_string(index=False)}")
print(f" r :\n {right.to_string(index=False)}")

"""
groupby없이 보간하면 앞 종목의 마지막종가와 뒷종목의 다음종가사이를 이어버린다.
groupby를 넣으면 그 종목 안에서만 잇는다.
"""

print("="*60)
#열별도 각각 다른 전략을 적용해서 처리한다.
# 시가,고가,저가,종가 -> 종목별로 보간 / 시계열 연속성
# 거래량 -> 결측 유지 / 0으로 채우면 안됨

OHLC = ["open", "high", "low", "close"]
before = df[OHLC].isna().sum().sum()

for col in OHLC:
    df[col] = df.groupby("code")[col].transform(lambda s: s.interpolate())

mid = df[OHLC].isna().sum().sum()

#종목의 맨 앞/뒤에 남은 결측은 이을 값이 없다. -> 가장 가까운 값으로 채움
# s.ffill() : 원본과 길이가 같은 시리즈, 결측을 바로 앞의 값으로 채움
# s..bfill() : 원본과 길이가 같은 시리즈, 결측을 바로 뒤의 값으로 채움
for col in OHLC:
    df[col] = df.groupby("code")[col].transform(lambda s: s.ffill().bfill())

after = df[OHLC].isna().sum().sum()

print(f"OHLC 결측 : {before}건")
print(f"종목별 interpolate 후 : {mid}건")
print(f"ffill + bfill : {after}건")

#거래량은 채우지않고 그냥 둔다.
print(f" 거래량 결측 : {df['volume'].isna().sum()}건")