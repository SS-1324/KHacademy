"""
    Numpy
    - 파이썬의 기본 list를 사용하지 않는 이유
    - 백터화가 무엇인가
"""

import numpy as np
import sys

#파이썬 list는 아무 타입이나 담는다

mixed = [1, "two", 3.0, [4]]
print(f" {mixed}")
print(f" 각 요소의 타입 : {[type(x).__name__ for x in mixed]}")

# 편리하지만 다른 리스트와 연산을 할때는 문제가 될 수 있다.
# ndarray는 타입이 하나로 고정된 numpy의 자료구조다. -> 연산이 쉽다.

arr = np.array([1,2,3,4])
print(f"type : {arr.dtype}")

# 숫자 배열 + 문자 배열과같은게 성립하지 않는다.
#np.array([1,2,3]) + np.array(["a","b","c"])

# JAVA배열과 비슷하지만
# 연산시에 반복문을 사용하지않고 배열단위 연산이 가능.

n = 100_000
print(n)

py_list = list(range(n))
np_arr = np.arange(n)

#list는 참조들의 배열 이므로 실제 정수 객체가 따로 있다.
list_bytes = sys.getsizeof(py_list) + sum(sys.getsizeof(x) for x in py_list[0:1000]) / 1000 * n
arr_bytes = np_arr.nbytes

print(f" 요소 {n:,}개 기준")
print(f" list : {list_bytes / 1024 / 1024:6.2f} MB")
print(f" ndarray : {arr_bytes / 1024 / 1024:6.2f} MB")

# ndarray는 8바이트짜리 정수가 빈틈없이 값으로 저장이 되어있는 반면
# list는 각 요소가 별도의 파이썬 객체이고, 리스트는 그 주소만 들고있다.

"""
    백터화 - for문을 쓰지 않는다.

    prices라는 가격 배열이 있어서 (오늘 - 어제) / 어제 수익률을 구하려고한다.

    1. list : 한줄씩 연산
    result = []
    for i in range(1, len(prices)):
        result.addpen((prices[i] - prices[i-1]) / prices[i-1])
    
    ndarray : 배열 전체를 한번에
    result = ((arr[1:] - arr[:-1]) / arr[:-1])

    두번째 방식을 백터화라고 한다.
    반복문이 파이썬이 아니라 c로 구현된 내부 코드에서 동작을 하기때문에 훨씬 빠르다.
    arr[1:] - 인덱스 1요소부터 끝까지
    arr[:-1] - 처음부터 마지막요소 바로 앞까지
"""

sample = np.array([100,110,99,121])
print(f"ar[1:] : {sample[1:]}")
print(f"ar[1:] : {sample[:-1]}")
print(f" 수익률 : {(sample[1:] - sample[:-1]) / sample[:-1]}")
#   ([110  99 121] - [100 110  99]) / [100 110  99]