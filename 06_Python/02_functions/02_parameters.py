"""
    파이썬 함수의 매개변수
"""

print("=" * 60)
print("기본 매개변수")
print("=" * 60)

def connect(host, port=8080, charset="utf8"):
    print(f" 접속완료 : {host}:{port}({charset})")

connect("localhost")
connect("localhost", 3306)
connect("localhost", 3306, "euckr")

#기본값을 많이 사용하는 매개변수일 수록 뒤쪽으로 배치한다.

print("=" * 60)
print("키워드 인자")
print("=" * 60)

#순서와 무관하게 이름으로 전달
connect(port=3306, host="127.0.0.1")

print("=" * 60)
print("*args - 인자를 튜플(순서기반)로")
print("=" * 60)

def total(*nums):
    print(f" 반은 값 : {nums} ({type(nums)})")
    return sum(nums)

print(f" total(1,2,3)  = {total(1,2,3)}")
print(f" total(1,2,3)  = {total()}")

print("=" * 60)
print("**args - 인자를 딕셔너리(키-값)로")
print("=" * 60)

def create_user(**info):
    print(f"받은 값 : {info} ({type(info)})")
    for key, value in info.items():
        print(f" {key} : {value}")

create_user(name="최지원", age=30, city="경기도")


def log(level, *messages, **options):
    print(f"level : {level}")
    print(f"messages : {messages}")
    print(f"options : {options}")

log("INFO", "서버 시작", "포트 8080...", timestamp=True, color="green")