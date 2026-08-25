"""
    브로드캐스팅
    - 모양이 서로 다른 배열끼리 사칙연산을 수행할 때, 
      작은 배열을 자동으로 확장하여 크기를 맞춰주는 기능
"""

import numpy as np
from _data import load_matrix, load_codes

np.set_printoptions(suppress=True)

codes = load_codes()
matrix = load_matrix()

# 브로드캐스팅
a = np.array([[1,2,3],
              [4,5,6]])
print(f" a + 10 = \n{a + 10}")

"""
    10이라는 값 하나가 배열 전체에 적용됨.
    numpy가 10을 (2,3)크기로 늘려서 계산한 것처럼 동작 -> 브로드캐스팅
"""

# 1. 뒤에서부터 차원을 하나씩 비교
# 2. 크기가 같거나, 둘중 하나가 1이면 통과
# 3. 차원 수가 다르면 앞쪽에 1을 채워 맞춰줌
# 예) (120,750) + (750,)
#     (120,750) + (1,750)
#     (120, 750)


#되는 경우와 안 되는 경우
cases = [
    ((120,750), None, "None"),
    ((120,750),(750,), "(750,)"),
    ((120,750),(120, 1), "(120, 1)"),
    ((120,750),(1, 750), "(1, 750)"),
    ((120,750),(120,), "(120,)"),
    ((120,750),(100,), "(100,)"),
]

for a_shape, b_shape, label in cases:
    # np.ones(모양) 그 모양의 1로 채워진 배열을 만듬
    A = np.ones(a_shape)
    B = 2.0 if b_shape is None else np.ones(b_shape)

    try:
        result = str((A+B).shape)
    except ValueError:
        result = "에러"

    print(f"{a_shape} + {label} : {result}")

"""
    (120,)은 왜 안됨?
    (1, 120)으로 변경됨.
    뒤에서부터 비교시 750 vs 120이 되어 실패
"""

#에러나는 상황

wrong = matrix.mean(axis=1) # (120,)

# matrix - wrong not be broadcast에러발생시
print(f" matrix.shape -> {matrix.shape}")
print(f" wrong.shape -> {wrong.shape}")

fixed1 = matrix.mean(axis=1, keepdims=True)
fixed2 = matrix.mean(axis=1).reshape(-1, 1)
print(f" fixed1.shape -> {fixed1.shape}")

print(f" 두 배열의 모양이 같은가 : {np.array_equal(fixed1, fixed2)}")

#종목별 평균
means = matrix.mean(axis=1, keepdims=True) # (120,1)
centered = matrix - means # (120,750) - (120,1)

print(f" means.shape : {means.shape}")
print(f" centered.shape : {centered.shape}")

print(f"{centered}")