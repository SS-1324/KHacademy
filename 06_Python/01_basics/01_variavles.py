"""
    변수와 자료형
    - 기본 자료형 5가지
    - 다중 할당과 값 교환
    - 타입 힌트와 상수 표기
"""

print("=" * 60)
print("변수 선언")
print("=" * 60)

#타입을 적지 않는다 - 동적 타이핑
name = "최지원"
age = 25
height = 175.8
is_student = True
data = None # java에서의 null

print(name, age, height, is_student, data)

print("=" * 60)
print("기본 자료형 5가지")
print("=" * 60)

print(f"{name} : {type(name)}")
print(f"{age} : {type(age)}")
print(f"{height} : {type(height)}")
print(f"{is_student} : {type(is_student)}")
print(f"{data} : {type(data)}")

print("=" * 60)

value = 25
print(f"{value} : {type(value)}")
value = "스물다섯"
print(f"{value} : {type(value)}")

# 편리하지만 위험한 면이 있다.
# 보편적으로 한 변수에는 한 가지 타입만 담기로 한다.

print("=" * 60)
print("다중 할당과 값 교환")
print("=" * 60)

x, y, z = 1, 2, 3
print(f"x,y,z -> {x} {y} {z}")

a = b = c = 0
print(f"a = b = c = -> {a} {b} {c}")

# java에서는 값 교환시에 tmp같은 중간변수가 필요함.
x, y = y, x
print(f"x,y -> {x} {y}")

print("=" * 60)
print("타입 힌트")
print("=" * 60)

user_name: str = "최지원"
print(f"user_name : {user_name}")

# 힌트를 어겨도 오류가 나지 않는다.
wrong: int = "문자열입니다"
print(f"wrong : {wrong}")

print("=" * 60)
print("상수")
print("=" * 60)

# python에는 final이 없음. 대문자로 변수명을 입력해서 약속만 함.
MAX_AGE = 100

print(f"MAX_AGE : {MAX_AGE}")
