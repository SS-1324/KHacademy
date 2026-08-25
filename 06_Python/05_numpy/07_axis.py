"""
    axis - 차원의 축
    axis=0 행방향
    axis=1 열방향
"""

import numpy as np

from _data import load_dates, load_codes, load_matrix

#지수표기 제거
np.set_printoptions(suppress=True)

dates, codes = load_dates(), load_codes()
matrix = load_matrix()

# 2행 3열 2차원 배열
m = np.array([[1,2,3],
              [4,5,6]])

print(f" sum : {m.sum()}")
print(f" sum(axis=0) : {m.sum(axis=0)} 세로로 더함")
print(f" sum(axis=1) : {m.sum(axis=1)} 가로로 더함")

print(f" sum(axis=0) : {m.sum(axis=0).shape} 행이 사라짐")
print(f" sum(axis=1) : {m.sum(axis=1).shape} 열이 사라짐")

"""
    axis는 사라지는 축
    어느 방향으로 계산하는가?로 외우면 헷갈림. 어느축이 없어지나?로 확인.
    shape(120,750)
    axis=0 -> 0면 축(120)이 사라짐 -> (750,)
    axis=1 -> 1면 축(750)이 사라짐 -> (120,)
"""

print(f"matrix.shape = {matrix.shape}")
print(f"전체의 평균 : {matrix.mean()}")
print(f"날짜별 평균 : {matrix.mean(axis=0).shape}")
print(f"종목코드별 평균 : {matrix.mean(axis=1).shape}")


# keepdims=True
# 줄인 축을 없애지말고 크기 1로 남겨둬.
a = matrix.mean(axis=1)
b = matrix.mean(axis=1, keepdims=True)
print(f"matrix.mean(axis=1) : {a.shape}") # 1차원 -> 120개짜리 한줄
print(f"matrix.mean(axis=1, keepdims=True) : {b.shape}") # 2차원 120행 1열

#값을 동일하다. 축이 사라지지 않고 남아있다는 것만 다르다.
#원래의 행렬과 계산하려면 형태가 맞아야하기 때문에.