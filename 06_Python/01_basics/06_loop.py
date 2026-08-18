"""
    반복문
    - for
    - range / enumerate / zip
    - while
    - break / continue / for-else
"""


print("=" * 60)
print("for문은 항상 for-each")
print("=" * 60)

# java의 for (String m : messages) ->  python의 for
members = ["최지원", "김지원", "이지원"]

for m in members:
    print(f" {m}님 안녕하세요.")

print()
for ch in "Python":
    print(ch, end=" ")
print()

print("=" * 60)
print("range(시작, 끝-1, 증감)")
print("=" * 60)

print(f"range(5)    ->  {list(range(5))}")
print(f"range(1, 6)    ->  {list(range(1, 6))}")
print(f"range(0, 10)    ->  {list(range(0, 10, 2))}")
print(f"range(0, 10)    ->  {list(range(5, 0, -1))}")

print("=" * 60)
print("enumerate() - 번호랑 값 함께")
print("=" * 60)

for i in range(len(members)):
    print(f" {i}번 : {members[i]}")

print()

for i, name in enumerate(members):
    print(f" {i}번 : {name}")

print()

# start지정으로 i값을 몇부터 시작할지 정할 수 있음
for i, name in enumerate(members, start=1):
    print(f" {i}번 : {name}")

print("=" * 60)
print("zip() - 여러 리스트를 동시에")
print("=" * 60)

names = ["삼성전자", "sk하이닉스", "카카오"]
today = [230000, 1600000, 200000]
yesterday = [220000, 1500000, 40000]

for name, now, prev in zip(names, today, yesterday):
    diff = now - prev
    print(f"{name:<10} : {now:<8}원({diff})") 

for i, (name, now) in enumerate(zip(names, today), start=1):
    print(f"{i} : {name}({now})")

print("=" * 60)
print("while")
print("=" * 60)

count = 0
while count < 5:
    print(f" count : {count}")
    count += 1 #탈출에 관련된 증감식

# 탈출을 위한 break
n = 1
while True:
    if n > 3:
        break
    print(f"n = {n}")
    n += 1

print("=" * 60)
print("break / continue / for-else")
print("=" * 60)

print("1~10중 홀수만 출력, 단 7을 넘으면 중단")
for i in range(1, 11):
    if i % 2 == 0: #짝수면 넘김
        continue
    if i > 7: #7넘으면 중단
        break
    print(f"{i}")

# for-else : break없이 끝까지 반복을 했을 때만 else 실행
for m in members:
    if m == "김지원":
        print("찾았습니다")
        break
else:
    print("찾는 회원이 없습니다")

for m in members:
    if m == "박지원":
        print("찾았습니다")
        break
else:
    print("찾는 회원이 없습니다")