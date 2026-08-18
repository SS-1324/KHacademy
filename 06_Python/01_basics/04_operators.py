"""
    연산자
    - 산술연산자
    - 비교논리연산자
    - 복합대입연산자
"""

print("=" * 60)
print("산술 연산자")
print("=" * 60)

print(f"7 + 3 = {7 + 3}")
print(f"7 - 3 = {7 - 3}")
print(f"7 * 3 = {7 * 3}")
print(f"7 / 3 = {7 / 3}") # java는 2(몫), python은 실수
print(f"7 // 3 = {7 // 3}") # java에서의 /와 같이 몫을 구함
print(f"7 % 3 = {7 % 3}")
print(f"7 ** 3 = {7 ** 3}") #거듭제곰

print()
print(f"type(6 / 3) = {type(6 / 3)}") # 실제로 값이 딱 나눠떨어지더라도 타입은 float
print(f"type(6 / 3) = {6 / 3}")

print(f"type(-7 / 3) = {-7 / 3}")
print(f"type(-7 // 3) = {-7 // 3}") # java는 버림이지만 python은 내림

print("=" * 60)
print("비교와 논리연산자")
print("=" * 60)

a, b = 10, 20
print(f"a, b = {a} {b}")
print(f"a == b = {a == b}")
print(f"a != b = {a != b}")
print(f"a < b = {a < b}")
print(f"a >= b = {a >= b}")
print()

# java에서는 &(and) ||(or) -> python에서는 and, or로 작성
# !연산은 not으로 작성
print(f"True and False : {True and False}")
print(f"True or False : {True or False}")
print(f"not False : {not False}")

print("=" * 60)
print("연쇄 비교 가능")
print("=" * 60)

score = 85

# java : if(score >= 80 && score < 90)
if 80 <= score < 90:
    print(f"점수({score})가 80이상 90미만입니다. -> B학점")

print(f"1 < 2 < 3 : {1 < 2 < 3}")

print("=" * 60)
print("in(멤버십)과 is(식별)")
print("=" * 60)

members = ["최지원", "김지원", "이지원"]
print(f"members = {members}")
print(f"'최지원' in members : {'최지원' in members}")
print(f"'박지원' not in members : {'박지원' not in members}")
print(f"'ll' in 'Hellow' : {'llw' in 'Hellow'}")

# ==은 값을 비교함, is를 통한 비교는 같은 객체인지를 비교함
x = [1,2,3]
y = [1,2,3]
z = x

print()
print(f"x = {x}, y = {y}, z = {z}")
print(f"x == y : {x == y}") # ==은 배열의 값을 비교
print(f"x is y : {x is y}") # is는 객체의 주소를 비교
print(f"x is z : {x is z}")

# None 비교시에는 반드시 is를 사용함.
data = None
print(f"data is None : {data is None}")
print(f"data is not None : {data is not None}")

print("=" * 60)
print("복합 대입 연산자")
print("=" * 60)

x = 10
print(f"x = {x}")

x += 5 # x = x + 5
print(f"x = {x}")

x -= 5 # x = x - 5
print(f"x = {x}")

x *= 2 # x = x * 2
print(f"x = {x}")