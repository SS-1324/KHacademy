"""
    연산, 유니버설 함수/집계
"""

import numpy as np

from _data import load_dates, load_one_stock, load_codes

dates = load_dates()    # 거래일
codes = load_codes()    # 종목코드
prices = load_one_stock(0)  # 첫 종목의 750일 종가

#연산자가 배열 전체에 적용됨
a = np.array([10,20,30,40])
b = np.array([1,2,3,4])

print(f" a * 2 = {a * 2}")
print(f" a + b = {a + b}")
print(f" a / b = {a / b}")
print(f" a + b = {a > 25}")

# 유니버설 함수(ufunc)
# - 배열의 모든 요소에 하나씩 알아서 적용되는 함수
x = np.array([1,4,9,16])
print(f" x = {x}")

#제곱근 sqrt()
print(f" np.sqrt(x) = {np.sqrt(x)}")

#반올림 round(값, 3)소수점 넷째자이레서 반올림
#x = np.array([1.123423,4.23423,9.25234,16.2356234])
print(f" np.round(x) = {np.round(x, 3)}")

#절대값 abs()
x = np.array([-1,4,-9,16])
print(f" np.abs(x) = {np.abs(x)}")


# 집계함수

print(f" {codes[0]} 750일 종가")
print(f"전체 합 : {prices.sum():,}")
print(f"전체 평균 : {prices.mean():,.0f}")
print(f"전체 표준편차 : {prices.std():,.0f}")
print(f"전체 최저가 : {prices.min():,.0f}")
print(f"전체 최고가 : {prices.max():,.0f}")

# argmin / argmax - 값이 아니라 위치

# arg는 argument가 아니라 그값을 만든 자리를 뜻함
# max -> 가장 큰 값
# argmax -> 가장 큰 값이 있는 인덱스
print(f"prices.max() -> {prices.max():,.0f}")
print(f"prices.argmax() -> {prices.argmax()}")

peak_idx = prices.argmax()
low_idx = prices.argmin()

print(f"최고가 : {prices[peak_idx]:,}원  ({dates[peak_idx]})")
print(f"최고가 : {prices[low_idx]:,}원  ({dates[low_idx]})")

# 결측치가 존재한다면 nan가 나옴

# 수익률 계산
"""
    일간 수익률 = (오늘 - 어제) / 어제
    prices[1:] 둘째날부터 끝까지(오늘)
    prices[:-1] 첫날부터 끝에서 두번째까지 (어제)
    두 배열을 나란히 놓고 빼면 -> 전일대비 변화량
"""
np.set_printoptions(suppress=True)

result = (prices[1:] - prices[:-1]) / prices[:-1]

print(f"일간 수익률 {len(result)}")
print(f"평균 {result.mean() * 100 :.4f}")
print(f"표준편차 {result.std() * 100 :.4f}")
print(f"최대상승 {result.max() * 100 :.4f}({dates[result.argmax() + 1]})")
print(f"최대하락 {result.min() * 100 :.4f}({dates[result.argmin() + 1]})")

print(f"시작가 {prices[0]:,}원  ->  종가 {prices[-1]:,}원")