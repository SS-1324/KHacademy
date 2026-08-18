"""
    문자열 다루기
    - 주요메서드(split, join, replace, strip)
    - 인덱싱과 슬라이싱
"""

print("=" * 60)
print("인덱싱, 슬라이싱")
print("=" * 60)

str = "Python Programming"
print(f"str = '{str}'")
print(f"문자열의 인덱스는 0부터 시작 : 0123456789...")
print()
print(f"str[0] = {str[0]}")
print(f"str[0] = {str[-1]}")

#슬라이싱 str[초기인덱스:끝인덱스+1:건너뛸갯수]
print(f"str[0] = {str[0:6]}") # 0이상 6미만
print(f"str[0] = {str[:6]}") # 시작값 생략시 0부터 시작
print(f"str[0] = {str[7:]}") 
print(f"str[0] = {str[::2]}") # 2칸씩 건너뛰기 
print(f"str[0] = {str[::-1]}") # -1입력시 역순 출력

print("=" * 60)
print("문자열 주요 메서드")
print("=" * 60)

str = "  Hello, Python World  "
print(f"원본    : [{str}]")
print(f"strip()    : [{str.strip()}]") #좌우공백제거
print(f"upper()    : [{str.upper()}]") #대문자 변환
print(f"replace()    : [{str.replace('Python', 'Java')}]") #치환
print(f"split(,)    : [{str.split(',')}]") # 특정 문자로 자르기
print(f"'-'.join([...])     : [{'-'.join(['2026','08','18'])}]") # 문자열로 합치기
print()
print(f"str.count('l')  : {str.count('l')}") #찾아서 갯수 반환
print(f"str.find('Python')  : {str.find('Python')}") #찾아서 인덱스 반환, 없으면 -1 

# 문자열메서드는 원본을 변경하지 않는다.
str.split(',')
print(str)
str2 = str.split(',')
print(str2)

#여러줄 문자열 사용
str3 = """
    문자열 다루기
    - 주요메서드(split, join, replace, strip)
    - 인덱싱과 슬라이싱
"""

print(str3)