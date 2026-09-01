"""
    상관 히드맵과 그래프
"""


import matplotlib
matplotlib.use("Agg")

import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

from _style import setup, out
from _merged import load_merged

setup()

df = load_merged()
# 종목별 일간 수익률(%)
df["ret"] = df.groupby("code")["close"].transform(lambda s: s.pct_change())

# 상관히트맵 만들기
# 여러 변수들간의 상관관계를 계산한 표를 색상의 농도와 밝기로 표현한 시각화 그래프

# 긴형식(한행=한종목의 하루) -> 넓은형식(행=날짜, 열=종목)
# pivot_table() -> (750, 120)이다.
# 열끼리 상관 -> 비교하고 싶은 것(종목)을 열로 세워주면된다.
pivot = df.pivot_table(index="date", columns="code", values="ret")

# 섹터순으로 열을 정렬해야 블록이 보인다.
# 같은 섹터 종목이 이웃하게 놓여야 대각선 블록이 눈에 보인다.
order = (df[["code", "sector"]].drop_duplicates()
         .sort_values(["sector", "code"])["code"].tolist())
# df[리스트]는 그 순서대로 열을 다시 세운 표를 반환, 값은 그대로고 순서만 바뀜
pivot = pivot[order]

# corr(): 열끼리 상관계수 행렬. (120,120)나온다
# -1(정반대) ~ 0(무관) ~ 1(동일하게 움직임). 대각선은 자기 자신과 비교기때문에 항상 1
# NaN가 있는 칸은 알아서 빼고 계산.
corr = pivot.corr()

print(f"pivot : {pivot.shape} (행=날짜, 열=종목)")
print(f"corr : {corr.shape} (종목 * 종목 상관계수)")

fig, ax = plt.subplots(figsize=(9, 7.5))

# sns.heatmap(2차원표, ...)
# cmap="coolwarm" : 발산형 팔레트. 파랑 - 횐색 - 빨강순으로 
# center=0 : 횐색(팔레트의 중앙)을 어느값에 놓을지.
# vmin/vmax : 색 범위에 양 끝을 고정한다. 지정하지 않으면 최소, 최대에 맞춰져, 
# 그림마다 같은 색이 다른값을 뜻하게 됨.
# xticklabels=False : 눈금 글자를 끈다. 
sns.heatmap(corr, cmap="coolwarm", center=0, vmin=-1, vmax=1,
            xticklabels=False, yticklabels=False, ax=ax)

ax.set_title("종목 간 수익률 상관(섹터순 정렬)")
fig.savefig(out("08_heatmap.png"), dpi=120)
plt.close(fig)

"""
 coolwarm + center=0은 반드시 넣어줘야한다.
 그래야 -1~1까지 기준점0을 가지고 색상을 직관적으로 표현할 수 있다.
"""

fig, axes = plt.subplots(1,2, figsize=(13, 5))
sns.heatmap(corr, cmap="coolwarm", xticklabels=False, yticklabels=False, ax=axes[0])
axes[0].set_title("center미지정")

sns.heatmap(corr, cmap="coolwarm", center=0, vmin=-1, vmax=1,
            xticklabels=False, yticklabels=False, ax=axes[1])
axes[1].set_title("center=0, vmin/vmax")

fig.tight_layout()
fig.savefig(out("09_center.png"), dpi=120)
plt.close(fig)

#섹터 블록을 숫자로 확인

# 종목코드 -> 섹터를 찾을 수 있는 표를 만들자.
sector_of = df[["code", "sector"]].drop_duplicates().set_index("code")["sector"]
codes = corr.columns

# 상관행렬은 대칭이고 대각선은 항상 1이다.
# j를 i+1부터 돌면 위쪽 삼각형만 훑게 되어, 같은 쌍을 두 번 세거나
# 자기 자신(1.0)을 섞는 일이 없다. 120 * 119 / 2 = 7,140 쌍
same, diff = [], []
for i in range(len(codes)):
    for j in range(i+1, len(codes)):
        v = corr.iloc[i, j]
        if sector_of[codes[i]] == sector_of[codes[j]]:
            same.append(v)
        else:
            diff.append(v)

print(f" 같은 섹터 쌍 : {len(same):>5}개 평균상관 {np.mean(same):.4f}")
print(f" 다른 섹터 쌍 : {len(diff):>5}개 평균상관 {np.mean(diff):.4f}")

"""
    수치로보니 상관관계가 섹터별로 존재한다 -> 그림으로는 전혀 보이지 않는다.
    시장 전체가 함께 움직이는 요인이 워낙 커서
    섹터차이가 심하게 발생하지는 않기때문에 바로 식별할 정도가 되지 않는다.
"""

# axis=1 : 행(날짜)방향으로 데이터(수익률)의 평균을 남기겠다. 열을 날리겠다. (750,)
market = pivot.mean(axis=1)

# sub(값, axis=0) : 각 행에서 market의 같은 날짜 값을 뺀다
resid = pivot.sub(market, axis=0)
corr2 = resid.corr()

same2, diff2 = [], []
for i in range(len(codes)):
    for j in range(i+1, len(codes)):
        v = corr2.iloc[i, j]
        # (A if 조건 else B).append(v) :조건에 따라서 append할 리스트 선택
        (same2 if sector_of[codes[i]] == sector_of[codes[j]] else diff2).append(v)

print(f"\n {'':<16} {'같은 섹터':>12}{'다른 섹터':>12}{'차이':>10}")
print(f" {'원본':<16} {np.mean(same):>12.4f}{np.mean(diff):>12.4f}{np.mean(same) - np.mean(diff):>10.4f}")
print(f" {'시장요인제거':<16} {np.mean(same2):>12.4f}{np.mean(diff2):>12.4f}{np.mean(same2) - np.mean(diff2):>10.4f}")

# 종목 단위 히트맵으로는 여전히 잘 안보임. 섹터 단위로 집계.
# 120*120 -> 10*10로 줄이면 칸마다 수백쌍의 평균이 되어 노이즈가 줄어듬.

sector_sorted = sorted(sector_of.unique())
# np.zeors((행, 열)) : 0으로 채운 빈 표.
block = np.zeros((len(sector_sorted), len(sector_sorted)))

# enumerate(목록)은 (번호, 값)을 함께 준다.
for a, sa in enumerate(sector_sorted):
    # sector_of[sector_of == sa]는 불리언 인덱싱
    # .index로 그 섹터에 속한 종목 코드만 꺼낸다
    ca = sector_of[sector_of == sa].index
    for b, sb in enumerate(sector_sorted):
        cb = sector_of[sector_of == sb].index
        # .loc[행목록, 열목록]은 이름으로 잘나낸다. 섹터 A * 섹터 B부분만 떼어내서 가져온다.
        # .values를 붙여 numpy배열로 변경.
        sub = corr2.loc[ca, cb].values
        if a == b:
            #같은 섹터끼리 -> 대각선은 빼고 평균을 내준다.
            # np.triu_indices(len(ca), k=1) 은 n*n 정사각 표의 대각선 위쪽 위치
            # (행번호들, 열번호들) -> k=1 대각선을 제외하겠다.
            iu = np.triu_indices(len(ca), k=1)
            block[a, b] = sub[iu].mean()
        else:
            block[a, b] = sub.mean()

fig, axes = plt.subplots(1, 2, figsize=(14,5.5))

sns.heatmap(corr2, cmap="coolwarm", center=0, vmin=-0.5, vmax=0.5,
            xticklabels=False, yticklabels=False, ax=axes[0])
axes[0].set_title("종목 단위(120*120) - 노이즈가 크다")

# annot=True : 각 칸에 숫자를 함께 사용. fmt=".3f"는 숫자의 형식
# 칸이 많이 않기때문에 사용이 가능
#  annot_kws={"size": 7} : 글자 스타일
sns.heatmap(block, cmap="coolwarm", center=0, annot=True, fmt=".3f",
            annot_kws={"size": 7},
            xticklabels=False, yticklabels=False, ax=axes[1])
axes[1].set_title("섹터단위로 집계(10*10)")

fig.tight_layout()
fig.savefig(out("10_sector_heatmap.png"), dpi=120)
plt.close(fig)