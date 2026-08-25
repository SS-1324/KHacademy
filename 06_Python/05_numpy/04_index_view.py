"""
    numpy의 인덱싱, 슬라이싱과 뷰
    - 기본 인덱싱은 list와 같다. 다만 슬라이싱 결과가 복사본이 아니다.
"""

import numpy as np

from _data import load_one_stock

# 기본 인덱싱과 슬라이싱
arr = np.array([10,20,30,40,50])
print(f" arr = {arr}")
print(f" arr[0] -> {arr[0]}")
print(f" arr[-1] -> {arr[-1]}")
print(f" arr[1:3] -> {arr[1:3]}")
print(f" arr[:3] -> {arr[:3]}")
print(f" arr[::2] -> {arr[::2]}")
print(f" arr[::2] -> {arr[::-1]}")

print("-" * 60)

#파이썬 list
lst = [1,2,3,4]
part_l = lst[1:3]
part_l[0] = 999

print(f"{lst} , {part_l}")

print("-" * 60)

arr = np.array([1,2,3,4])
part_a = arr[1:3]
part_a[0] = 999

# list 슬라이싱 -> 복사본
# numpy 슬라이싱 -> 뷰(원본의 일부를 가리키는 창)
# -> 복사하지 않고 하나를 사용해서 보여주는 구조이기 때문에 메모리가 늘지않고 속도가 빠름
print(f"{arr} , {part_a}")


#뷰와 복사를 확인

arr = np.arange(10)
view = arr[2:5]
copy = arr[2:5].copy()

# .base는 배열이 스스로 가지고 있는 속성
# 메모리를 직접 가지고있는지, 다른 메모리를 참조하고 있는지
print(f" view = arr[2:5]")
print(f" view.base is arr = {view.base is arr}")
print(f" copy.base is arr = {copy.base}")

# arr[1:3] 슬라이싱 -> 뷰
# arr[::2] -> 뷰

# reshape(행, 열) : 해당 구조를 n행n열로 다시 본다.
# arr.reshape(2,5) -> 뷰

# ravel() : 어떤 차원의 구조건 1차원으로 만든다.
# arr.ravel() -> 뷰

# flatter : ravel와 동일하게 1차원 만들지만 값을 복사
# arr.flatter() -> 복사

#원본을 망가뜨리기
prices = load_one_stock(0)[:10].copy()
print(f"{prices}")

def normalize_wrong(arr):
    """최근 5일치만 정규화... 원본변경?"""
    recent = arr[-5:]
    recent -= recent.min()
    return recent

def normalize_safe(arr):
    """최근 5일치만 정규화..."""
    recent = arr[-5:].copy()
    recent -= recent.min()
    return recent

backup = prices.copy()

normalize_wrong(prices)
print(f"normalize_wrong : {prices}")

prices = backup.copy()
normalize_safe(prices)
print(f"normalize_safe : {prices}")
