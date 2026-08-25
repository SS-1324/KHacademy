"""
    2차원 배열과 reshape
"""

import numpy as np

from _data import load_flat, load_codes

#지수표기 제거
np.set_printoptions(suppress=True)

codes = load_codes() # (120,)

# 1차원을 2차원으로 변경
flat = load_flat()
print(f"flat : {flat.shape}")

# reshape(120, 750) -> 120행 750열로 보겠다.
# 행열로 변경할때 곱해서 90000이 나와야한다.
matrix = flat.reshape(120,750)
print(f"flat.reshape(120,750) : {matrix.shape}")
print(f"ndim {flat.ndim} : size {flat.size}")

#같은 데이터를 어떤 모양으로 볼것인가만 바꿔주는 것
print(f"matrix.base is flat : {matrix.base is flat}")

# reshape은 한줄로 늘어선 값을 앞에서부터 순서대로 잘라서 채운다.

#행과 열 꺼내기
print(f"matrix[0]   ->  shape {matrix[0].shape} {codes[0]}의 750일치")
print(f"{matrix[0][:5]}")

#matrix[:, 0]에서 쉼표 앞의 :은 모든 행, 뒤의 0은 0번의 열
print(f"matrix[:, 0] -> {matrix[:, 0].shape}") # 모든 종목의 첫날
# matrix[:,0] -> 2차원 행렬의 0번째 열 전체가 1차원 배열로 추출
print(f"{matrix[:,0][:5]}...") 
print()

#행과 열을 둘 다 숫자로 지정하면 값이 하나가 나옴
print(f" matrix[3,10] -> {matrix[3,10]} 4번째 종목의 11일차 값")

"""
    쉼표 앞이 행, 뒤가 열 matrix[행, 열]
    콜론(:)은 전부가 된다.

    matrix[0] = matrix[0, :]        0번 행 전체
    matrix[:, 0]                    0번 열 전체
"""

print(f"matrix[0] -> {codes[0]} : {str(matrix[0].shape)}")
print(f"matrix[:, 0] -> 첫날 전 종목 : {str(matrix[:, 0].shape)}")
print(f"matrix[:5, :3] -> 5종목 * 3일 : {str(matrix[:5, :3].shape)}")
print(f"matrix[0, 0] -> {str(matrix[0, 0].shape)} : 요소가 1개인 배")

# reshape의 -1

# -1은 '이 자리 숫자는 네가 알아서 계산해라'라는 표시다.
print(f" flat.reshape(120, 750) -> {flat.reshape(120, 750).shape}")
print(f" flat.reshape(120, -1) -> {flat.reshape(120, -1).shape}")
print(f" flat.reshape(-1, 750) -> {flat.reshape(-1, 750).shape}")
# print(f" flat.reshape(-1, -1) -> {flat.reshape(-1, -1).shape}")