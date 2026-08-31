"""
실습데이터 불러오기

표 다섯 장을 읽어 돌려준다.
지금까지는 표가 한 장이었지만, 여기서는 나뉘어 저장된 여러 장을 직접 붙여야 한다.

  sectors.csv        10행    섹터 마스터        code(S01~S10) · name · companyCount
  companies.csv     120행    종목 마스터 (정제본)  결측 0, 오염 없음
  raw-companies.csv 122행    종목 마스터 (오염본) 
  financial.csv   1,440행    분기 재무          120종목 x 12분기
  prices.csv     90,000행    일별 시세          120종목 x 750거래일
"""

import os

import pandas as pd

DATA_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data")
ENCODING = "utf-8-sig"
USE_REMOTE = False
BASE = "https://khlab.oneground.ai.kr"


def path(name):
    """data/ 안의 파일 경로"""
    return os.path.join(DATA_DIR, name)


def _read(name):
    """data/ 의 CSV 한 장을 읽는다. 없으면 어디를 찾았는지 알려 주고 멈춘다."""
    p = path(name)
    if not os.path.exists(p):
        raise SystemExit(f"실습데이터를 찾을 수 없습니다: {p}")
    return pd.read_csv(p, encoding=ENCODING)


def load_prices():
    """
    일별 시세 90,000행.

    parse_dates=["date"] 를 주면 '2023-09-25' 가 글자가 아니라 날짜 타입으로 읽힌다.
    이게 없으면 .dt 접근자를 쓸 수 없고 기간 비교도 사전순이 되어 버린다.
    """
    return pd.read_csv(path("prices.csv"), encoding=ENCODING, parse_dates=["date"])


def load_companies(raw=False):
    """
    종목 마스터. raw=True 면 오염본(122행)을 돌려준다.
    """
    if raw:
        return pd.read_csv(
            path("raw-companies.csv"), encoding=ENCODING,
            dtype=str, keep_default_na=False,
        )
    return _read("companies.csv")


def load_sectors():
    """섹터 마스터 10행. code(S01...) · name(금융...) · companyCount 가 들어 있다."""
    return _read("sectors.csv")


def load_financial():
    """분기 재무 1,440행. 종목당 12분기(2023-Q3 ~ 2026-Q2)."""
    return _read("financial.csv")
