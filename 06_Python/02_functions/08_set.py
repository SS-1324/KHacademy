"""
    set
    - 중복제거
    - 집합연산
"""

#생성방법

nums = {1,2,3,3,3,4,2}
print(f" nums = {1,2,3,3,3,4,2} -> {nums}")

#빈 자료구서 생성시에는 set()사용, {}는 딕셔너리!
empty_set = set()
empty_dict = {}

#값을 잠시 set자료구조에 넣어다가 꺼내면 -> 중복제거
nums = [1,2,3,3,3,4,2]
print(f"원본 : {nums}")
print(f"중복제거 : {set(nums)}")
print(f"중복제거 : {list(set(nums))}")

print()
#집합연산
a = {1,2,3,4}
b = {3,4,5,6}
print(f"a | b 합집합 = {a | b}")
print(f"a & b 교집합 = {a & b}")
print(f"a - b 차집합 = {a - b}")

#값을 추가하거나 삭제
s = {1, 2}
print(f" s: {s}")

s.add(3)
print(f" s: {s}")

s.update([4,5])
print(f" s: {s}")

s.discard(1)
print(f" s: {s}")

s.discard(99)
print(f" s: {s}")