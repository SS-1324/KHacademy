"""
    시계열과 지표만든기
"""

import pandas as pd
from _load import load_merged

pd.set_option("display.width", 140)

df = load_merged()

one = df[df["code"] == "G0001"]


# 날짜를 인덱스로 사용
# set_index(인덱스열)
# 내가 지정한 열이 인덱스가 된 새로운 DataFreme반환
# -> 인덱스가 날짜가되면 pandas는 이를 시계열로 인식함 -> 문자열로 기간조회 가능
one = one.set_index("date").sort_index()

print(f" 기간 : {one.index.min().date()} ~ {one.index.max().date()}")

#문자열로 날짜 조회 된다
print(f"one.loc['2026-03'] -> {len(one.loc['2026-03'])}행 (3월 전체)")
print(f"one.loc['2026-01':'2026-06'] -> {len(one.loc['2026-01':'2026-06'])}행 (26년1월~6월 전체)")
# 실제로 2026-03같은 문자열을 pandas가 그달 전체로 해석한다.
# 항상 sort_index()를 통해서 정렬이 되어있어야, 슬라이싱이 가능.

# dt 접근자
#  [.dt] datetime series에서 연/월/일 같은 조각을 꺼낼 때 사용

d = df.head(3)
print(f" dt.year : {d['date'].dt.year.tolist()} -> 연도") 
print(f" dt.quarter : {d['date'].dt.quarter.tolist()} -> 분기") 
print(f" dt.dayofweek : {d['date'].dt.dayofweek.tolist()} -> (0=월 ... 일=6)")
# f-stinrg 안에서는 따옴표를 역슬래시로 감쌀 수 없다
period_label = '.dt.to_period("M")'
print(f"{period_label:<28} : {d['date'].dt.to_period('M').astype(str).tolist()}") 

dow = df["date"].dt.dayofweek.value_counts().sort_index()
for k, v in dow.items():
    print(f" {'월화수목금토일'[k]}요일 {v:,}건")


# resample - 시간 단위를 바꿔서 다시 묶음
# df.resample("ME").last() 
# 새 시간단위만큼의 행을 가진 결과. 뒤에 붙인 집계함수가 값을 정한다. => 시간기준 그룹바이
# D(일), W(주), ME(월말), QE(분기말), YE(연말)

monthly_last = one["close"].resample("ME").last()
monthly_mean = one["close"].resample("ME").mean()
monthly_vol = one["volume"].resample("ME").sum()
print(f"{'월':<12} {'last() 월말종가':<16} {'mean() 월평균가':<16}")
for idx in monthly_last.index[:4]:
    print(f"{idx.strftime('%Y-%m'):<12} {(monthly_last[idx]):>16,.0f} {(monthly_mean[idx]):>16,.0f}")

print(f"월별 거래량 합계 예시 : {monthly_vol.iloc[0]:,.0f}")

# resample('ME').ohlc() 
# ohlc : 시가, 고가, 저가, 종가
print(one["close"].resample("ME").ohlc().head(3).round(0).to_string())
# ohlc() : 기간별 시가, 고가, 저가, 종가 네열을 한번에 만들 수 있음

# rolling - 연속된 N개 행을 window단위로 훑으며 계산한다.
# 이동평균처럼 최근N일의 데이터가 필요할때 rolling으로 만듬
# s.rolling(20).mean()
print("="*60)
df = df.sort_values(["code","date"]).reset_index(drop=True)

# groupby(...).transform(함수) : 그룹마다 계산하고 결과를 원본길이로 돌림
# rolling은 집계 이름이 아님! -> lambda로 감싸서 넘김
wrong = df["close"].rolling(20).mean()
right = df.groupby("code")["close"].transform(lambda s: s.rolling(20).mean())

edge = df.index[df["code"] != df["code"].shift()][1] # 종목이 바귀는 첫 행
for i in [edge - 1, edge, edge + 1]:
    w = f"{wrong[i]:,.0f}" if pd.notna(wrong[i]) else "NaN"
    r = f"{right[i]:,.0f}" if pd.notna(right[i]) else "NaN"
    print(f" {i:<8}{df.loc[i,'code']:<9}{df.loc[i,'close']:>10,}{w:>20}{r:>20}")

"""
    groupby없이 계산하면 종목별 코드가 달라도 이평선 재계산없이 이어지는 평태도 계산이된다.
    그룹화를 해줘야 종목별 20일 이평선이 정상적으로 계산된다. 

    diff, shift, rolling... 전부 groupby가 필요하다.
"""

# 변화율 계산
# pct_change - 바로 위행대비 비율변화를 구한다.

# cumprod - 누적곱 
# s.cumprod(10) - s[0] * s[1]...s[10]
"""
    shift : 한칸밀기
    diff : 전일대비차이
    pct_change : 전일대비비율
    cumprod : 누적곱 -ex) 누적수익률
"""
print("="*60)
g = df.groupby("code")["close"]
df["ret"] = g.transform(lambda s: s.pct_change())

# iterrows() :  한줄씩 꺼내서 반복문을 돌림
sample = df[df["code"] == "G0001"].head(4)
for _, r in sample.iterrows():
    ret = f"{r['ret']:.4f}" if pd.notna(r["ret"]) else "NaN"
    print(f"{r['date'].date()!s:<14}{r['close']:>10,}{ret:>12}")

#누적수익률 : 첫날의 NaN을 0으로 채우고 (1+수익률)을 차례로 곱하면 됨
cum = (1+df[df["code"]=="G0001"]["ret"].fillna(0)).cumprod().iloc[-1]
print(f"G0001 누적 수익률 : {(cum - 1) * 100:.1f}")