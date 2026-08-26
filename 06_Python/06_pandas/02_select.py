"""
    조회 - sql과 비교
"""

import pandas as pd

from _load import load

#화면 표시 설정
pd.set_option("display.width", 130)
pd.set_option("display.max_columns", 20)

df = load()
# nunique() : 중복제거
print(f"{len(df)}행 / {df['code'].nunique()}종목 \n")

last = df["date"].max() #마지막 거래일
print(f"df['date'].max() : {last}")

#마지막 거래일의 기록
day = df[df["date"] == last].reset_index(drop=True)
print(f" 기준일 : {last.date()} / {len(day)}개 종목\n")

# 마지막 거래일 기준의 데이터만 120개 가져옴
#print(f"{day}")

# select - 열 고르기

# SQL    : SELECT code, close, changRate FROM prices
# Pandas : df[['code', 'close', 'changeRate']] 
print(day[["code", "close", "changeRate"]].head(5).to_string(index=False))


#대괄호 하나 vs 둘

one = day['close']
two = day[['close']]

print(f" day['close'] -> {type(one).__name__} shape {one.shape}")
print(f" two[['close']] -> {type(two).__name__} shape {two.shape}")

"""
    바깥 대괄호는 꺼내기위해 쓰는 것, 안쪽 대괄호는 "목록"지정.
    df[[...]]은 열 이름 리스트를 넘기는 것이 아니라 결과가 DataRrame이다.
"""


# where - 조건(행을 걸러내기)
# SQL    : WHERE close > 100000
# Pandas : df[df['close'] > 100000] 
expensive = day[day['close'] > 100_000]
print(f" 결과 {len(expensive)}건")
print(expensive[['code', 'close']].to_string(index=False))

mask = day['close'] > 100_000
print(f" 결과 {mask.sum()}건")

# AND / OR - & | 와 괄호
# SQL    : WHERE close > 50000 AND changeRate > 0
# Pandas : df[(df['close'] > 50000) & (df['changeRate'] > 0)]

both = day[(day['close'] > 50_000) & (day['changeRate'] > 0)]
print(f" 결과 {len(both)}건") 
print(both[['code', 'close']].head(3).to_string(index=False))


"""
    SQL                 pandas
    ordey by 컬럼       df.sort_values(컬럼, ascending=False)
    LIMIT 개수          df.head(개수)
    DISTINCT 컬럼       df[컬럼].unique()    /   유니크한 값의 갯수를 구하고 싶다면 df[컬럼].nunique()
    COUNT(*) GROUP BY~  df[컬럼].value_counts()
    IN (...)            df[컬럼].isin([...])
"""

top = day.sort_values("close", ascending=False).head(3)
print(f"가장 비싼 3종목")
print(top[['code', 'close']])

rise = day.sort_values("changeRate", ascending=False).head(3)
print("가장 많이 오른 3종목 : ")
print(rise[['code', 'close', 'changeRate']])

mid = day[(day['close'] >= 10_000) & (day['close'] <= 50_000)]
mid = day[day['close'].between(10_000, 50_000)]
print(f"종가기준 1만~5만 : {len(mid)}종목")

picked = day[day['code'].isin(["G0001","G0050","G0100"])]
print(f"G0001, G0050, G0100만 보고싶다.")
print(picked)


# loc와 iloc - 끝값포함 여부기준으로 보기
print(f"loc : {day.loc[0:2]}")
print(f"iloc : {day.iloc[0:2]}")

"""
    파이썬의 슬라이싱은 전부 끝을 제외하는데 df의 loc만 포함된다.
"""

# loc는 행조건 + 열선택이 가능
print(day.loc[day['close'] > 200_000, ['code', 'close', 'volume']].head(3))

#인덱스 변경하기
# set_index : 특정 열을 인덱스로 올린다. 그열은 일반 열에서 사라진다.
indexed = day.set_index("code")
print(f"day.set_index('code') : 인덱스 {indexed.index[:3].tolist()}")
print(indexed.head(3))
print("=" * 60)
print(indexed.loc["G0001", ["close", "changeRate"]].to_string())