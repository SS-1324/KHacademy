"""
    BeautifulSoup
    - HTML 및 XML문서에서 원하는 데이터를 쉽게 추출할 수 있도록 해주는 스크래핑 라이브러리

    1. requests로 요청 후 문자열(html, xml)을 응답받음
    2. bs4의 find, select를 활용해서 특정 텍스트를 추출
"""

import requests
from bs4 import BeautifulSoup
from _config import BASE, TIMEOUT, HEADERS

resp = requests.get(f"{BASE}/stocks", headers=HEADERS, timeout=TIMEOUT)
resp.raise_for_status() # 200이 아니면 예외를 발생
html = resp.text
print(f"{BASE}/stocks  []{resp.status_code}] {len(html):,}자")


#문자열 -> 태그구조
#requests가 전달한 값은 그냥 긴 문자열이다.

#bs4은 이 문자열을 DOM트리처럼 다룰 수 있게 만들어 줌.
soup = BeautifulSoup(html, "lxml")
print(f"\n soup.title.text   :   {soup.title.text if soup.title else '(없음)'}")

# js -> document.querySelector와 같은 역할을 bs이 한다.

#select : css선택자를 그대로 씀, 선택자로 전부 가져옴, 못찾으면 []
#select_one : 선택자로 1개만 가져옴. 못찾으면 None
rows_select = soup.select("tr.stock-row")
print(f" tr.stock-row 갯수  :  {len(rows_select)}")

#텍스트 꺼내서 사용하기
first = soup.select_one("tr.stock-row")
price_tag = first.select_one("td.col-price")

#!r를 붙여주면 따옴표를 붙여서 가져온다
print(f" .text      :   {price_tag.text!r}")
print(f" .get_text()      :   {price_tag.get_text()!r}")
print(f" .get_text(strip=True)      :   {price_tag.get_text(strip=True)!r}")

#속성을 꺼내야 한다 - get()

link = first.select_one("td.col-name a")

#속성값은 없을 수도 있기때문에 get()사용 권장
print(f" a['href'] :    {link['href']}")
print(f" a.get('href') :    {link.get('href')}") 
print(f" a.get('href') :    {link.get('href', '없음')}")

#첫번째 행 전체 꺼내보기
for sel in ["td.col-code", "td.col-name a", "td.col-sector",
            "td.col-price", "td.col-change", "td.col-volume"]:
    tag = first.select_one(sel)
    value = tag.get_text(strip=True) if tag else "(없음)"
    print(f"{sel:<20} {value}")



