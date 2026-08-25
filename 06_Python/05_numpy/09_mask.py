"""
    불리언 인덱싱과 마스킹
"""

import numpy as np
from _data import load_matrix, load_codes, load_column

np.set_printoptions(suppress=True)

codes = load_codes()
matrix = load_matrix()

#조건이 bool 배열을 만든다
a = np.array([10,25,30,15,40])
mask = a > 20

print(f" a = {a}")
print(f" a > 20 = {mask}")

# 대괄호 안에 마스크를 넣으면 true자리의 값만 뽑혀서 나온다.
print(f" a[mask] -> {a[mask]}")
print(f" True가 몇개인가? -> {mask.sum()}")

#2차원 배열도 동일
big = matrix > 500_000
print(f" matrix > 500_000 -> shape {big.shape}, dtype {big.dtype} ")
print(f" 해당하는 값은 몇개인가 : {big.sum()}개 / 전체 : {matrix.size}개")
# .2%를 사용하면 0.0013 -> 0.13%로 변경해서 소수점 둘째 자리까지.
# 100곱하고 %붙이기
print(f" 비율 : {big.mean():.2%}")

#조건을 만족하는 값만 1차원 배열로 추출
print(f"{matrix[big].shape}")

# and / or 쓸 수 없다
# np.diff(배열, axis=1) 옆칸끼리(행 방향으로) 차를 구한다.
result = np.diff(matrix, axis=1) / matrix[:,:-1] # 일간 수익률
print(result)

print("-" * 60)

# 거래량 -> 수익률과 자리를 맞추려고 첫날 뺀 값
volume = load_column("volume")[:, 1:] #(120, 749)의 거래량

cond1 = result > 0.03 # 3%넘게오른 날
cond2 = volume > 2_000_000 #거래량이 200만주 초과

# 파이썬은 and(값 하나)의 참, 거짓을 묻는 연산자
# 배열을 주면 이 배열 전체가 참이가?를 물음.
# 배열 논리 연산자는 &(and), |(or), ~(not)
# cond1 and cond2

both = cond1 & cond2
print(f" 수익률 3% 이상 & 거래량 200만주 이상 : {both.sum()}")
print(f" 수익률 3%가 되지 않은 날 : {(~cond1).sum()}")

"""
    (result > 0.03) & (volume > 2000000)
    조건마다 괄호로 감싸는 습관을 만들자.
"""

# np.where -> 3항 연산자 배열버전
sample = np.array([0.05, -0.02, 0.0, 0.11, -0.07])
print(f"수익률 : {sample}")

#np.where(조건, 참일때, 거짓일때) -> 요소마다 판단해서 새 배열을 만들어줌.
direction = np.where(sample > 0, "up", 
                     np.where(sample < 0, "down", "flat"))
print(f"{direction}")