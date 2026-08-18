"""
    입출력
     - print()
"""

print("=" * 60)
print("기본 출력")
print("=" * 60)

# 문자열은 따옴표('', "")로 감싸서 출력
print("hello, python!!")
print('안녕 파이썬')

#숫자는 따옴표 없이
print(100)
print(3.14)
print(10 + 20)

print("=" * 60)
print("여러값을 동시에 출력 (,)로 구분")
print("=" * 60)

print("최지원", 30, "학생")
print("java", "sql", "python")

#구분자를 변경하고 싶을 때 = sep옵션 부여
print("2026", "08", "18", sep="-")
print("최지원", 30, sep="님의 나이는 ")

print("=" * 60)
print("끝 문자 변경 (end 옵션)")
print("=" * 60)

#end: print출력 후 마지막에 출력할 문자 지정 옵션
print("첫번째 줄입니다.", end=" ") #개행대신 공백 한칸을 마지막에 넣겠다.
print("두번째 줄입니다.")
print("1+1=",end="")
print(2)

print("=" * 60)
print("이스케이프 문자")
print("=" * 60)

#역슬래시를 사용해서 이스케이프 문자를 작성할 수 있음
print("이번줄을 작성하고\n한줄 개행하고 싶다.")
print("이번줄을 작성하고\t한탭 띄어쓰고 싶다.")

print("강사가 말했다 \"파이썬 재미있죠?\" ")

print("=" * 60)
print("문자 포매팅")
print("=" * 60)

name = "최지원"
age = 25
height = 175.5

#java의 printf와 비슷한 방식
print("이름 : %s, 나이 : %d, 키 : %.1f" % (name, age, height))

#.format() 메서드 사용 방식
print("이름 : {}, 나이: {}, 키: {}".format(name, age, height))

#f-string 방식을 많이 사용 - 간단한 연산도 함께 사용
print(f"내년에는 나이가 {age + 1}세가 됩니다")
print(f"이름 : {name}, 나이: {age}, 키: {height}")

#f-string 정렬기능도 가능하다
#{변수:옵션}
print(f"[{name:<10}]") # <10 : 10칸을 확보하고 왼쪽 정렬
print(f"[{name:>10}]") # >10 : 10칸을 확보하고 오른쪽 정렬
print(f"[{name:^10}]") # ^10 : 10칸을 확보하고 중앙 정렬

print("=" * 60)
print("input : 입력은 항상 문자열")
print("=" * 60)

age_text = input("나이를 입력하세요 : ")

print(f"입력값 : {age_text}")
print(f"입력값 : {type(age_text)}")

#입력받은 값으로 계산하려면 형변환 필요
age = int(age_text)
print(f"내년 나이 : {age + 1}")

# 1) 이름과 나이를 입력받아 한줄로 소개문자을 출력
name = input("이름을 입력하세요 : ")
age = input("나이를 입력하세요 : ")
print(f"안녕하세요. 제 이름은 {name}이고, 나이는 {age}입니다.")

# 2) 숫자 두개를 입력받아, 합,차,몫,나머지 출력
num1 = int(input("숫자1 : "))
num2 = int(input("숫자2 : "))
print("=" * 60)
print(f"합 : {num1 + num2}")
print(f"차 : {num1 - num2}")
print(f"몫 : {num1 // num2}") #파이썬에서 몫 구할 때는 // 사용
print(f"나머지 : {num1 % num2}")