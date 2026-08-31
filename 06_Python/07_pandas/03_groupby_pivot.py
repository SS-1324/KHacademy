"""
    GroupBy와 피벗
"""

import pandas as pd
from _load import load_merged

pd.set_option("display.width", 140)

df = load_merged()
print(f"통합데이터 {len(df)}행 / {df['code'].nunique()}종목 / {df['sector'].nunique()}섹터\n")

# groupby + fun : split -> apply -> combine

# split : 키를 기준으로 그룹을 쪼갬
# apply : 각 그룹에 함수를 적용
# combine : 결과를 하나로 합친다.

# agg           -> 요약표를 만들때 그룹별로 어떤 결과를 도출
# transform     -> 원본에 열을 추가해서 무엇인가를 비교하려할 때 그룹별로 계산결과를 원본과 동일하게 도출
# filter        -> 그룹별로 검사해서 조건에 맞지않으면 버림

count_num = df.groupby("sector")["code"].count()
nunique_num = df.groupby("sector")["code"].nunique()

print(f"{count_num} : {nunique_num}")

"""
한 종목이 750행이면 count는 750, nunique는 1이다.
"""

# agg는 요약표
summary = df.groupby("sector").agg(
    종목수=("code", "nunique"),
    거래일수=("date", "count"),
    평균종가=("close", "mean"),
    최대거래량=("volume", "max"),
)
print(summary.round(0).to_string())

#filter는 그룹단위로 걸러내기
#조건을 만족하는 그룹 전체를 남기거나 버린다

#거래일이 700일 미만인 종목을 제외
filtered = df.groupby("code").filter(lambda g: len(g) >= 700)
print(f"{len(df)}행 {df['code'].nunique()}종목")
print(f" -> {len(filtered)}행 {filtered['code'].nunique()}종목")
# 전 종목이 750일 이라서 걸러지는게 없다.

# g["close"].mean() -> 750일의 종목평균
big = df.groupby("code").filter(lambda g: g["close"].mean() > 100_000)
print(f"{len(big)}행 {big['code'].nunique()}종목")
"""
    filter는 특정 행이 아니라 그룹 전체를 남기거나 버림.
    df[df["close"] > 100000]이런 식으로 작성시 조건에 맞는 행만 남김.
"""

# 다중그룹과 MultiIndex
# df.groupby(["sector", "market"])["close"].mean() -> 인덱스자체가 n개의 Series로 나옴

multi = df.groupby(["sector", "market"])["close"].mean()
print(f" 타입 : {type(multi.index).__name__}")
print(f"\n{multi.head(6).round(0).to_string()}")

print(f" .loc['금융']  ->  첫 레벨로 조회")
print(multi.loc['금융'].round(0).to_string())

print(f" .loc[('금융', 'GX-GROWTH')]  ->  {multi.loc[('금융', 'GX-GROWTH')]}")
# 레벨을 전부 지정할 때는 튜플로 묶어서 전달한다.

# .unstack() -> 인덱스를 열로 펼친다
# 인덱스의 안쪽 레벨을 열로 올림 -> 세로로 길게 늘어선 결과를 표 모양으로 변경해주기 위해서
print(multi.unstack().head(4).round(0).to_string())

# reset_index -> 인덱스를 보통 열로 되돌린다.
# 인덱스 값이 열이되고, 인덱스는 ,0,1,2,3...으로 새로 만들어진다.
print(multi.reset_index().head(3).round(0).to_string(index=False))

# groupby(as_index=False)를 쓰면 아예 처음으로 열로나옴
flat = df.groupby(["sector", "market"], as_index=False)["close"].mean()
print(flat.columns.tolist())

# 피벗 - 형식 바꾸기
"""
    pivot_table : 한 열은 행으로, 다른 열은 열로 펼쳐 집계한다.
    df.pivot_table(index="행기준", columns="열기준", values="집계할 열", aggfunc="mean")
"""

# to_period() :날짜를 기간으로 변경 ("Q", "M", "Y") -> 분기, 연, 월
print("="*60)
d = df.copy()
d["quarter"] = d["date"].dt.to_period("Q").astype(str)

pv = d.pivot_table(
    index="sector", #행
    columns="quarter", #열
    values="close", #셀데이터
    aggfunc="mean", #셀데이터 집계함수
)
print(pv.iloc[:5, :4].round(0).to_string())
# 행 10개 * 13개분기짜리 요약


# 넓은 형식 vs 긴형식
print("="*60)
wide = pv.iloc[:3,:3] # 사람이 보기 편하다
print(wide.round().to_string())

# melt : 넓은 형식을 긴 형식으로 녹인다.
# df.melt(id_vars="유지할 열", var_name="열이름을 담을 열", value_name="값을 담을 열")
# 열마다 흩어져있던 값을 한 열에 모으기 위함.
long = wide.reset_index().melt(id_vars="sector", var_name="quarter", value_name="close")
print(long.head(6).round().to_string(index=False))

# 사람이 보는 형태의 보고서, 엑셀, 데이터확인 -> 넓은 피봇형식이 좋음
# DB저장, 시각화 -> 긴 피봇형식(열이 늘어나지 않는다.)의 형식이 좋다.

back = long.pivot(index="sector", columns="quarter", values="close")
print(f"pivot()으로 다시 넓은 형식으로 전화이 가능함")
print(back.round().to_string())