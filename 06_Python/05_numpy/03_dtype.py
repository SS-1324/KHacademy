"""
    dtype
    - 오류가 안났어도 잘 봐야할 것
"""

import numpy as np

#주요 데이터 타입
# 정수 - int64
# 실수 - float64
# 참/거짓 - bool
# 객체 - object(파이썬 객체)

sample = [
    [1,2,3],
    [1.0,2.0],
    [True, False],
    [1, 2.5],
    [1, "two"]
]

for s in sample:
    a = np.array(s)
    print(f"np.array({str(s)}) : dtype={a.dtype}")

#  타입이 섞이면 모두를 대표하는 타입으로 올라간다.
#  정수 + 실수 -> float64
#  숫자 + 문자 -> 문자(<U -> 유니코드 문자열)
#  이런과정을 upcastion이라고 한다.

# 정수 배열에는 np.nan를 넣을 수 없다.
int_arr = np.array([5200,51500,53200])
print(f"{int_arr} dtype = {int_arr.dtype}")

# int_arr[0] = np.nan #Not a Number
print(f"np.nan dtype = {type(np.nan).__name__}")
# nan는 실수형이다. 정수칸에 들어갈 수 없다.

int_arr2 = np.array([5200,np.nan,53200])
print(f"{int_arr2} dtype = {int_arr2.dtype}")

# astype은 버림
#지수 표기(5.20009e+04)를 나오지 않게 해줌.
np.set_printoptions(suppress=True)

f = np.array([52000.9, 51999.2, -3.7])
print(f"원본 : {f}")
print(f"astype(int) : {f.astype('int64')}")
print(f"반올림 후 astype(int) : {np.round(f).astype('int64')}")

# dtype이 object일 때

dirty = np.array([52000, 51500, "53,200"])
print(f" -> {dirty}")
print(f" -> {dirty.dtype}")

# dirty.mean()
# 숫자여야 할 열의 dtype이 object나 문자열이라면
# 그 열에 숫자가 아닌 값이 섞여있다.
# -> 데이터정제가 필요하다.

cleaned = np.array([str(x).replace(",","") for x in dirty]).astype("int64")
print(f" {cleaned} dtype = {cleaned.dtype} mean = {cleaned.mean()}")