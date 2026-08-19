"""
    딕셔너리(dict)
    - key-value 구조
    - 추가 / 수정/ 삭제 / 순회
"""

# api응답(json) -> 곧 딕셔너리 구조다

#생성과 조회방법
user = {
    "name" : "최지원",
    "age" : 25,
    'skills' : ["java", "sql", "python"],
}

print(f" user = {user}")
print(f" user['name'] = {user['name']}")
print(f" user['skills'] = {user['skills']}")

# [key]로 가져오거나 get(key)로 가져오기 가능
print(f" user.get('name') = {user.get('name')}")
print(f" user.get('skills') = {user.get('skills')}")
print(f" user.get('phone') = {user.get('phone', '없음')}") #get은 기본값 설정가능

# print(f" user['phone'] = {user['phone']}") []로 가져올시 값이 없으면 None가 아니라 에러발생
print()

# 추가 / 수정 / 삭제
user = { "name" : "최지원","age" : 25,}

user["email"] = "jiwon@naver.com"
print(f" user = {user}")

user["age"] = 40
print(f" user = {user}")

del user["age"]
print(f" user = {user}")

print()
#전체탐색
user = {
    "name" : "최지원",
    "age" : 25,
    'skills' : ["java", "sql", "python"],
}

for key in user:
    print(f"{key} : {user[key]}")

for key, value in user.items():
    print(f"{key} : {value}")

print()
print(f"keys() : {list(user.keys())}")
print(f"values() : {list(user.values())}")
print(f"items() : {list(user.items())}") # (key, value)형태의 튜플목록