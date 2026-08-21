"""
    목록파싱 - list[dict]
"""

import requests, re, csv, json
from bs4 import BeautifulSoup
from _config import BASE, TIMEOUT, HEADERS
from _parsers import get_text, get_number, parse_rate, parse_stocks


resp = requests.get(f"{BASE}/stocks", headers=HEADERS, timeout=TIMEOUT)
resp.raise_for_status() # 200이 아니면 예외를 발생
html = resp.text

soup = BeautifulSoup(html, "lxml")

row = soup.select_one("tr.stock-row")

# row.select_one("td.test") 해당하는 클래스가 없을 때
try:
    row.select_one("td.test").text()
except Exception as e:
    print(f" 에러 : {e}")

print(f" {get_text(row, 'td.test')}")

#컨테이너 단위로 순회하기
names = soup.select("td.col-name")
prices = soup.select("td.col-price")

#지금은 갯수가 같아서 zip으로 묶어도 딱 맞아 떨어짐
#하지만 갯수가 보장되는 것은 아니다. 만약에 이름과 가격의 수가 다르면 매칭이 섞일 수 있음.
print(f" 전체에서 따로 뽑으면 : 이름 {len(names)}, 가격 : {len(prices)}")


for row in soup.select("tr.stock-row"):
    names = get_text(row, "td.col-name")
    prices = get_number(row, "td.col-price")


# 텍스트 정제 - 정규표현식
cases = [
    ("  71,200 $ ", "가격"),
    ("+2.35%", "등락률(양수)"),
    ("-1.08%", "등락률(음수)"),
    ("12,340,567", "거래량"),
]

for text, label in cases:
    if "%" in text:
        value = parse_rate(text)
    else:
        digits = re.sub(r"[^\d]", "", text)
        value = int(digits) if digits else None

    print(f" {text!r:<16}{str(value):<16}{label}")


#stock데이터 추출 함수화
stocks = parse_stocks(html)

print(f"{'코드':<8}{'종목명':<14}{'섹터':<10}{'현재가':>12}{'등락률':>9}")

for s in stocks:
    print(f"{s['code']:<8}{s['name']:<14}{s['sector']:<10}{s['price']:>12}{s['rate']:>9}")


#파일로 저장
#json, 
# csv -> 상품명,가격,재고,          
#        맥북, 1500000, 10

def save_csv(data, path):
    if not data:
        return

    with open(path, "w", newline="", encoding="utf-8") as f:
        # dict를 그대로 한 행으로 씉다. 컬럼 순서는 fieldnames으로 정함
        writer = csv.DictWriter(f, fieldnames=data[0].keys())
        # csv파일 맨 첫줄에 컬럼 이름 씀
        writer.writeheader()
        # 딕셔너리 리스트 전체를 각 행으로 기록
        writer.writerows(data)

#경로를 따로 지정하지 않으면 지금 터미널 위치에 저장됨
save_csv(stocks, "stocks.csv")

def save_json(data, path):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

save_json(stocks, "stocks.json")