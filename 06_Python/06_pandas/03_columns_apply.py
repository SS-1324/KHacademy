"""
    열 다루기와 타입변환
"""

import pandas as pd
from _load import load

pd.set_option("display.width", 130)


df = load()

# 열 추가, 삭제, 이름변경

day = df[df["date"] == df["date"].max()].reset_index(drop=True)

day["range"] = day["high"] - day["low"]
day["range_pct"] = day["range"] / day["low"] * 100

print(" 추가 : day['range', 'range_pct']")
print(day[["code","high","low","range","range_pct"]])

day["tmp"] = 0
print(day[["code","high","low","range","range_pct", "tmp"]])

#열 삭제
print(f"tmp 추가시 열 : {day.shape[1]}개")
day = day.drop(columns=["tmp"])
print(f"tmp 제거시 열 : {day.shape[1]}개")

#열 이름 변경
renamed = day.rename(columns={"close": "종가"})
print(f"close열 이름 변경 : {renamed.columns}")

# 열 연산자에는 반복문이 없다.

# .str 접근자
# 문자열 메서드를 반복문 없이 모든 행에 일괄 적용할 수 있게 해준다.
print(f".str.len() -> {day['code'].str.len().unique().tolist()}")
print(f".str[1:] -> {day['code'].str[1:].head(3).tolist()}")
print(f".str.startswith('G00') -> {day['code'].str.startswith('G00').sum()}")


sample = pd.Series(["   가온전자  ", "서라벌 바이오", "한빛중공업"])
print(f".str.strip()    -> {sample.str.strip().tolist()}")

new_sample = sample.str.replace(r"\s+", "", regex=True).tolist()
print(new_sample)

# astype과 to_numeric

dirty = pd.Series(["52000", "51500", "N/A", "1,250"])
print(f" dirty Series : {dirty.tolist()}")

# dirty.astype("float64") - > 에러발생

# 에러발생시 강제로 결측를 만들어 반환해라. errors="coerce"
converted = pd.to_numeric(dirty, errors="coerce")
print(f"\n to_numeric(dirty, errors='coerce') : {converted.tolist()}")
print(f" 결측 : {converted.isna().sum()}건")

converted = pd.to_numeric(dirty.str.replace(",","", regex=False), errors="coerce")
print(f"\n to_numeric(dirty, errors='coerce') : {converted.tolist()}")

"""
    astype은 하나라도 값이 이상하면 전체를 변경할 수 없다.
    to_numeric(errors='coerce')으로 변경시 오염된 데이터는 강제로 nan로 남기고 나머지만 변환 가능.
"""

print("="* 60)

import time

#apply -> 반복문
col = df['close']
print(f" {len(col):,}행")

# 시간 측정을 위해서 time.perf_counter()로 측정한 전후값을 빼주면 된다.
start = time.perf_counter()
vec = col * 1.1
t_vec = time.perf_counter() - start

print(f"vec = col * 1.1 : {t_vec * 1000:>.2f}ms")

start = time.perf_counter()
app = col.apply(lambda x: x * 1.1)
t_app = time.perf_counter() - start
print(f"app = col.apply(lambda x: x * 1.1) : {t_app * 1000:>.2f}ms")

"""
apply라는 반복을 사용시 행을 하나씩 돌면서 함수를 실행하는 방식이라 느리다.

백터화로 표현할 수 없는 복잡한 로직에는 어쩔 수 없지 apply사용한다.
단, 대부분 이런방식을 대체하는 pandas의 함수가 있지않을까? -> 있어요.
"""