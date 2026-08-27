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

 agg : 여러 집계를 한 번에, 결과 열 이름까지 붙여서 나옴.
 df.groupby("기준열").agg(새열이름=("대상열","집계함수"), ...)
 -> 그룹 수만큼의 행 반환
 -> 그룹화하여 어떤 결과를 요약해 보고싶을 

 transform : 그룹별로 계산하되, 기존 df과 연산을 위해서 결과를 각 행의 원래 자리에 되돌려 놓는다.
 df.groupby("기준열")["대상열"].transform("집계함수")
"""

# diff : 바로 위 행과의 차이를 구한다.
# s.diff() -> s[i] - s[i-1], 맨 첫행은 NaN

#shift : 열을 통째로 한 칸 아래로 민다.
# s.shift()  ->  원본과 길이가 같은 Series를 반환, i번째 값 = s[i-1]변경, 맨첫행은 NaN]

two = df[df["code"].isin(["G0001","G0002"])].reset_index(drop=True)
print(f"two : {two}")

wrong = two["close"].diff()
print(f"wrong : \n{wrong}")

right = two.groupby("code")["close"].diff()
print(f"right : \n{right}")


# two.index[...] -> 인덱스만 직접 뽑아내겠다.
# shift로 한칸 민 뒤 "현재형 != 바로위행" 비교하면 경계를 찾을 수 있음.
#값이 바뀌는 지점만 True
# [0]은 맨 첫행(위가 없으니까 무조건 True), [1]번째가 경게가 된다.
index = two.index[two["code"] != two["code"].shift()][1]
print(f"종목이 바뀌는 지점 : {index}행")

# groupby없이 계산하면 각 코드의 마지막 종가에서 다음코드의 첫 종가를 빼버린다.
# 두 종목사이에는 아무 관계가 없다 -> 전일대비값을 임의로 만들어버림.
# diff, shift, interpolate, rolling, cumsum ... 
for i in range(index - 2, index + 2):
    w = f"{wrong[i]:,.0f}" if pd.notna(wrong[i]) else "NaN"
    r = f"{right[i]:,.0f}" if pd.notna(right[i]) else "NaN"
    print(f" {i:<8} {two.loc[i, 'code']:<9} {two.loc[i, 'close']:<12,.0f} {w:>20} {r:>20}")


# 복사본이 pandas버전에 따라 달라질 수 있다.

version = pd.__version__
print(f"현재 판다스 버전 : {version}")

sub = df[df["close"] > 100_000]
sub["flag"] = 1
print(sub)
print('flag' in df.columns)

# 판다스3.0이상에서는 df[조건] -> 항상 복사본이 생긴다.
# 원본을 바꿀 생각이면 -> df.loc[조건, '열'] = 값
# 따로 떼어 쓸 생각이면 -> df.[조건].copy()