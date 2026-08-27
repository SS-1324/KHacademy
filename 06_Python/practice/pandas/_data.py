"""
실습데이터 불러오기

data/raw-prices.csv 를 아무 옵션 없이 읽어서 돌려준다. 정제되기 전 상태 그대로다.
숫자여야 할 열이 문자열이고, 날짜 형식이 섞여 있고, 중복·이상치·결측이 그대로 남아 있다.

  code        종목코드 (G0001 ~ G0120)
  date        거래일   ★ 형식이 3종으로 섞여 있다
  open        시가
  high        고가
  low         저가
  close       종가
  volume      거래량
  change      전일 대비 등락액
  changeRate  전일 대비 등락률(%)

원본은 92,721행이고, 정제가 끝나면 120종목 x 750거래일 = 90,000행이 되어야 한다.
"""

import os

import pandas as pd

# __file__ 은 '지금 이 파일(_data.py)의 경로' 다.
#   os.path.abspath : 상대경로를 절대경로로 펴 준다
#   os.path.dirname : 파일명을 떼고 폴더 경로만 남긴다
# 이 파일 기준으로 잡아 두면 어느 폴더에서 실행하든 data 를 찾아낸다.
# "data" 라고만 적으면 실행 위치가 바뀔 때마다 파일을 놓친다.
DATA_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data")

# 파일 맨 앞에 BOM 이라는 눈에 보이지 않는 표식이 붙어 있다.
# 그냥 읽으면 첫 열 이름이 'code' 가 아니게 되어 KeyError 가 나는 일이 있다.
ENCODING = "utf-8-sig"


def raw_path():
    """정제 전 원본의 경로"""
    return os.path.join(DATA_DIR, "raw-prices.csv")


def load_raw():
    """정제 전 원본을 DataFrame 으로 돌려준다. (92,721행 9열)"""
    path = raw_path()
    if not os.path.exists(path):
        raise SystemExit(f"실습데이터를 찾을 수 없습니다: {path}")
    return pd.read_csv(path, encoding=ENCODING)
