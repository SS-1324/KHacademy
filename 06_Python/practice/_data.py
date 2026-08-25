"""
실습데이터 불러오기
"""
from pathlib import Path

import numpy as np

# __file__는 지금 이 파일(_data.py)의 경로
CSV_PATH = Path(__file__).with_name("prices.csv")
N_DAYS = 750 #거래일수

_COLUMNS = {
    "code": 0,
    "date": 1,
    "open": 2,
    "high": 3,
    "low": 4,
    "close": 5,
    "volume": 6,
    "change": 7,
    "changeRate": 8,
}

#한 번 읽은 열을 담아두는 곳
_cache = {}

def _read(col, dtype):
    """
        csv에서 한열만 읽어 1차원배열로 리턴
    """
    key = (col, str(dtype))

    if key not in _cache:
        if not CSV_PATH.exists():
            raise FileNotFoundError(f"{CSV_PATH}파일을 찾을 수 없습니다.")

        _cache[key] = np.loadtxt(
            CSV_PATH,
            delimiter=",",              #구분자(,)
            skiprows=1,                 #첫줄을 건너뜀(헤더),
            usecols=_COLUMNS[col],       #필요한 열 하나만 읽는 것
            dtype=dtype,
            encoding="utf-8-sig",                 
        )

        # CSV는 맨 앞에 BOM이라는 눈에 안보이는 표식이 붙음.
        # 그냥 읽으면 첫 열 이름이 'code'가 아니라 앞에 표식이 붙어서 나옴.
        # utf-8-sig의 인코딩을 사용하면 표식을 제거하고 읽는다.

    # 원본을 그대로 주면, 받아간 쪽에서 값을 변경했을 때 값이 변경.
    return _cache[key].copy()
        

def load_flat():
    # 종가만 1차원 배열로 뽑아서 리턴
    return _read("close", "int64")

def load_one_stock(idx=0):
    """
        한족목의 종가만 1차원으로,
        csv자체가 종목 하나가 750줄, 그다음종목 750줄~
        idx번째 종목 idx*750
    """
    flat = (load_flat())
    start = idx * N_DAYS
    return flat[start : start + N_DAYS]

def load_dates():
    """
        거래일 배열(750개) ->  2026-08-05 -> 날짜형식으로 가져오기"

        .astype("datetime64[D]")은 문자열을 날짜로 변경한다.
        문자열로두면 계산이 어렵다. 날짜타입으로 변환시 '며칠 차이냐?' 같은 계산이 가능하다.
        [D] -> 'Day단위로 다루겠다.'
    """

    dates = _read("date", str)
    return dates[:N_DAYS].astype("datetime64[D]")

def load_codes():
    """종목코드 배열(120개) 0001~0120"""
    codes = _read("code", str)
    return codes[::N_DAYS]

def load_matrix():
    """
        120, 750 형태의 종가 행렬 반환, 행=종목, 열=날짜
    """
    close = load_flat()
    return close.reshape(120, 750)


def load_column(name):
    # 열 데이터를 (120,750)행렬도 반환

    if name in ("code", "date"):
        raise KeyError(f"기존 함수를 사용해라.")
    if name not in _COLUMNS:
        raise KeyError(f"찾을 수 없는 열이다.")

    dtype = "float64" if name == "changeRate" else "int64"
    
    return _read(name, dtype).reshape(120, 750)


_NAN_IDX = np.array([37,88,142,199,245,301,358,412,470,537,618,703])
_OUTLIER_IDX = np.array([61,214,389,556,671])
_OUTLIER_SCALE = np.array([6.2, 5.4, 7.8, 5.9, 7.1]) #정상값대비 곱할 값
def load_dirty():
    """
        결측, 이상치용 데이터
         
        첫 종목의 750일 종가에
        -> 결측 12개
        -> 이상치 5개
    """

    arr = load_one_stock(0).astype("float64")
    arr[_NAN_IDX] = np.nan
    arr[_OUTLIER_IDX] = arr[_OUTLIER_IDX] * _OUTLIER_SCALE

    return arr, np.sort(_NAN_IDX), np.sort(_OUTLIER_IDX)