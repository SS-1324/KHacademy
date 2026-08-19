"""
    리스트(list)
"""

#리스트 생성
members = ["최지원", "김지원", "이지원"]
mixed = [1, "hello", True, [1,2]] #타입이 달라도 한 list에 담을 수 있음
empty = []

print(f"members : {members}")
print(f"mixed : {mixed} {bool(mixed)}")
print(f"empty : {empty} {bool(empty)}")

print(f"members[0] : {members[0]}")
print(f"members[-1] : {members[-1]}")
print(f"members[-1] : {members[0:2]}")

#값을 추가할 때
items = ["A", "B"]

items.append("C")
print(f" 맨뒤에 추가 : {items}")

items.insert(0, "Z")
print(f" 지정위치 정해서 추가 : {items}")

items.extend(["D", "E"])
print(f" 맨뒤에 여러개 추가 : {items}")

#수정, 삭제할 때
print("=" * 60)
items = ["A", "B", "C", "D"]

items[0] = "Z"
print(f" 특정 인덱스에 값 변경 : {items}")

items.remove("B")
print(f" 값으로 삭제 : {items}")

popped = items.pop()
print(f" 맨뒤값 리턴후 삭제 : {items} : {popped}")

del items[0]
print(f" 인덱스로 삭제 : {items}")

#탐색과 정보
nums = [3,1,4,1,7,8,5]
print(f"nums에 4가 있나? : {4 in nums}")
print(f"nums에 특정 값 인덱스 조회 : {nums.index(4)}")
print(f"nums에 요소 갯수 : {nums.count(1)}")
print(f"nums 전체갯수 : {len(nums)}")

#정렬
nums.sort()
print(f" sort() : {nums}")

nums.sort(reverse=True)
print(f" sort(reverse=True) : {nums}")

nums.reverse() #순서 뒤짚기 (정렬x)
print(f" reverse() : {nums}")

words = ["banana", "Apple", "cherry"]
words.sort()
print(f" 문자열 정렬 : {words}")


# 2차원 리스트
martix = [
    [1,2,3],
    [4,5,6],
    [7,8,9],
]

print(f" martix[1][2] = {martix[1][2]}")
print()

for row in martix:
    for value in row:
      print(f"{value}", end="")  
    print()
