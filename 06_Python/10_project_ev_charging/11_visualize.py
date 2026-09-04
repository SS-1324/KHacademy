"""
심화 4 · 시각화
    지역별 매출 막대그래프, 시간대별 이용 패턴
"""

import matplotlib
matplotlib.use("Agg")

import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd

from _style import setup, out
from _config import path

setup()

df = pd.read_pickle(path("_clean_final.pkl"))

# ── 1) 지역별 매출 막대그래프 ────────────────────────────
by_region = (
    df.groupby("region_name")["fee"].sum().sort_values(ascending=False) / 1e8
)

fig, ax = plt.subplots(figsize=(9, 5))
sns.barplot(x=by_region.index, y=by_region.values, ax=ax, color="steelblue")
ax.set_title("지역별 매출")
ax.set_xlabel("지역")
ax.set_ylabel("매출(억 원)")
for i, v in enumerate(by_region.values):
    ax.text(i, v, f"{v:.2f}", ha="center", va="bottom", fontsize=9)
fig.savefig(out("01_region_revenue.png"), dpi=120)
plt.close(fig)
print("저장 : 01_region_revenue.png")

# ── 2) 시간대별 이용 패턴 (충전 시작 시각 기준) ───────────
df["hour"] = df["start_time"].dt.hour
by_hour = df.groupby(["hour", "charger_type"]).size().unstack(fill_value=0)

fig, ax = plt.subplots(figsize=(11, 5))
by_hour.plot(kind="bar", stacked=True, ax=ax,
             color={"AC완속": "#4C72B0", "DC급속": "#DD8452"})
ax.set_title("시간대별 충전 시작 건수 (충전기 타입별)")
ax.set_xlabel("시(0~23시)")
ax.set_ylabel("건수")
ax.legend(title="충전기 타입")
ax.tick_params(axis="x", rotation=0)
fig.savefig(out("02_hourly_pattern.png"), dpi=120)
plt.close(fig)
print("저장 : 02_hourly_pattern.png")

# ── 3) 요일별 이용 패턴 (보너스) ──────────────────────────
df["dow"] = df["start_time"].dt.dayofweek
dow_labels = ["월", "화", "수", "목", "금", "토", "일"]
by_dow = df.groupby("dow").size().reindex(range(7), fill_value=0)

fig, ax = plt.subplots(figsize=(8, 4))
sns.barplot(x=dow_labels, y=by_dow.values, ax=ax, color="mediumseagreen")
ax.set_title("요일별 충전 건수")
ax.set_xlabel("요일")
ax.set_ylabel("건수")
fig.savefig(out("03_weekday_pattern.png"), dpi=120)
plt.close(fig)
print("저장 : 03_weekday_pattern.png")

print()
print("지역별 매출(억 원) :")
print(by_region.round(2).to_string())
print()
print("피크 시간대 Top 3 :", by_hour.sum(axis=1).sort_values(ascending=False).head(3).index.tolist())
