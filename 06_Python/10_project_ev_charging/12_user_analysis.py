"""
심화 5 · 이용자 분석
    재방문율, 이용자당 평균 충전 횟수
"""

import matplotlib
matplotlib.use("Agg")

import matplotlib.pyplot as plt
import pandas as pd

from _style import setup, out
from _config import path

setup()

df = pd.read_pickle(path("_clean_final.pkl"))

visits = df.groupby("user_id").agg(
    충전횟수=("log_id", "count"),
    총충전량=("energy_kwh", "sum"),
    총결제금액=("fee", "sum"),
)

n_users = len(visits)
n_repeat = (visits["충전횟수"] >= 2).sum()

print("=" * 60)
print("이용자 분석")
print("=" * 60)
print(f" 전체 이용자 수         : {n_users:,}명")
print(f" 전체 충전 건수         : {len(df):,}건")
print(f" 이용자당 평균 충전 횟수 : {visits['충전횟수'].mean():.2f}회")
print(f" 이용자당 평균 결제금액  : {visits['총결제금액'].mean():,.0f}원")
print()
print(f" 재방문(2회 이상) 이용자 : {n_repeat:,}명")
print(f" 재방문율                : {n_repeat / n_users * 100:.1f}%")

print()
print("충전 횟수 분포 :")
dist = visits["충전횟수"].value_counts().sort_index()
print(dist.head(10).to_string())

print()
print("상위 5명 (총 결제금액 기준) :")
print(visits.sort_values("총결제금액", ascending=False).head(5).round(1).to_string())

# ── 시각화 : 방문 횟수 분포 ────────────────────────────
fig, ax = plt.subplots(figsize=(9, 5))
capped = visits["충전횟수"].clip(upper=10)  # 11회 이상은 '10+' 로 묶어서 본다
counts = capped.value_counts().sort_index()
labels = [str(i) if i < 10 else "10+" for i in counts.index]
ax.bar(labels, counts.values, color="slateblue")
ax.set_title(f"이용자별 충전 횟수 분포 (재방문율 {n_repeat / n_users * 100:.1f}%)")
ax.set_xlabel("충전 횟수")
ax.set_ylabel("이용자 수")
fig.savefig(out("04_user_visit_dist.png"), dpi=120)
plt.close(fig)
print("\n저장 : 04_user_visit_dist.png")
