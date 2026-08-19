"""
    파이썬 함수 기본
    - def로 함수 정의
    - 반환값과 None
    - 다중 반환
"""

print("=" * 60)
print("함수 정의 방법")
print("=" * 60)

def greet(name):
    return f"{name}님 안녕하세요."

print(greet("김수민"))
print(greet("박지호"))

#반환값이 없는 함수 -> None
def show(msg):
    print(f"메시지 : {msg}")
    # return 없음

result = show("hi")
print(f"반환값 : {result}")

#다중반환 - 반환값이 여러개 일 때
def clac(a, b):
    return a + b, a - b, a * b

result = clac(10, 5)
print(f"결과 : {result}")

#언패킹해서 받기
add, sub, mul = clac(10, 5)
print(f"결과 : {add} {sub} {mul}")

print("=" * 60)
print("docstring - 이함수가 어떤 역할을 하는지 설명문구를 작성하는 법")
print("=" * 60)

def calc_tax(price: int, rate: float = 0.1) -> int:
    """
    부가세를 포함한 최종 금액을 반환하는 함수
    """
    return int(price * (1 + rate))

print(f" calc(10000) = {calc_tax(10000):,}")
help(calc_tax)

print("=" * 60)
print("함수는 정의 후에 호출해야한다.")
print("=" * 60)

#파이썬은 위에서 아래로 한줄씩 실행된다.
#정의 전에는 호출이 불가하다(호이스팅 없음)

#  print(f"{later()}")
def later():
    return "나중에 정의한 함수"
print(f"{later()}")