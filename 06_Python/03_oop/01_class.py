"""
 클래스와 객체
 - class / __init__ / self
 - 인스턴스 변수 vs 클래스변수
"""

# 클래스의 기본 형태

class Account:   
    def __init__(self, owner, balance=0):
        #java의 생성자의 해당, 필드는 여기서 self.~~~ = ... 식으로 만듬
        self.owner = owner
        self.balance = balance

    def deposit(self, amount):
        self.balance += amount
        return self.balance

acc = Account("최지원", 10000)
print(f"acc.owner = {acc.owner}")
print(f"acc.deposit = {acc.deposit(5000)}")
print(f"acc.balance = {acc.balance}")

# self
# acc.deposit(5000)는 내부적으로 Account.deposit(acc, 5000)으로 실행됨
# 객체 자신이 첫 인자로 자동으로 전달되므로 메서드 정의부에 self를 반드시 넣어줘야한다.

res = Account.deposit(acc, 1000)
print(f" 직접호출 : {res}")

# 파이썬은 오버로딩이 없다

def greet(name=None):
    if name is None:
        return "안녕하세요"
    return f"{name}님 안녕하세요"

print(f"greet() -> {greet()}")
print(f"greet() -> {greet('최지원')}")


# 인스턴스 변수 vs 클래스 변수
class Member:
    bank_name = "KH은행"       #클래스 변수 : 모든 인스턴스가 공유 -> java의 static
    count = 0

    def __init__(self, owner):
        self.owner = owner     #인스턴스 변수
        Member.count += 1      #클래스변수는 클래스명으로 접근

m1 = Member("최지원")
m2 = Member("박지원")

print(f"Member.count : {m1.owner}")
print(f"Member.count : {m2.owner}")
print(f"m1.bank_name : {m1.bank_name}")
print(f"m2.bank_name : {m2.bank_name}")

m1.bank_name = "우리은행" # 인스턴스에 같은 이름으로 대입하면 새로운 인스턴스 변수가 생김
Member.bank_name = "농협은행"
print(f"m1.bank_name : {m1.bank_name}")
print(f"m2.bank_name : {m2.bank_name}")
print(f"Member.bank_name : {Member.bank_name}")  #클래스변수가 바뀐건 아님