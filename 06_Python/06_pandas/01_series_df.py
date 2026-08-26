"""
    Pandas의 Series/DataFrame
    Pandas는 기본적으로 Numpy에 이름표를 붙이고 sql처럼 조회할 수 있도록 만든 것
"""

import pandas as pd
from _load import RAW_PATH, ENCODING

#화면 표시 설정
pd.set_option("display.width", 130)
pd.set_option("display.max_columns", 20)

# Series - 인덱스가 붙은 1차원
s = pd.Series([52000, 51500, 53200], name="close")
print(f"{s}")
print(f" s.index -> {s.index}")
print(f" s.values -> {s.values}") # 이부분이 numpy ndarray

#왼쪽에 0,1,2가 인덱스(key)다. 값 옆에 항상 붙어다님.

print("=" * 60)
# pandas의 index
a = pd.Series([10,20,30], index=["x","y","z"])
b = pd.Series([1,2,3], index=["z","y","x"])

# 순서가 아니라 인덱스를 기준으로 맞춰서 더한다.
print(f"a : {a.to_dict}")
print(f"b : {b.to_dict}")
print(f"\na + b = \n{a + b}")

c = pd.Series([1,2], index=["x","w"])
# 값을 더할 동일한 인덱스가 없다면 NaN가 된다.
print(f"\na+c : {(a+c).to_dict()}")

# DataFrame : 딕셔너리를 활용한 2차원 테이블. Series를 열로 모든 2차원
print("="*50)
print("DataFrame")
print("="*50)
data = {
    "이름": ["최지원","이지원","김지원",],
    "나이": [20,30,50],
    "전공": ["컴공","수학","문학"],
    "보너스": [150, 60, None]
}

d = pd.DataFrame({
    "code": ["G0001","G0002","G0003"],
    "close": [52000,51500,53200],
    "volume": [100_000, 58_000, 200_000],
})

print(f"{d}")
print(f" d.index : {d.index}")
print(f" d.columns : {d.columns}")
print(f" d.shape : {d.shape}")

# 이름표가 두개다. 행에붙은 index, 열에붙은 columns.
# Series가 이름표 1개짜리라면 DataFrame은 2개짜리다.

col = d['close']
print(f"d['close'] : {type(col).__name__} name={col.name}")
print(f"col : \n{col}")
# 열 하나를 꺼내면 Series가 그대로 나옴다.

print("=" * 60)
# read_csv - csv파일을 읽어오는 함수

# 아무 옵션 없이 읽어오기
plain = pd.read_csv(RAW_PATH, encoding=ENCODING)
print(f"close dtype={plain['close'].dtype}")
print(f" date dtype={plain['date'].dtype}")

# read_csv는 열마다 타입을 추론하는데, 한 열에 숫자가 아닌 값이 하나라도 있으면 그 열은 전체를 문자열로 읽는다.
# 옵션을 주면 조금 더 명확하게 타입을 추론해서 가져올 수 있다.

df = pd.read_csv(RAW_PATH, 
                 encoding=ENCODING,
                 parse_dates=["date"], #date열은 datetime으로
                 na_values=["N/A", "-"], #이 문자열들은 결측으로 취급
                 thousands=",", #"1,000,000"형태를 숫자로 가져오겠다
                ) 
print(f"close dtype={df['close'].dtype}") # float64로 잘 변경됨
print(f" date dtype={df['date'].dtype}")  # 날짜는 그대로 str

# date열은 2023-09-25, 2023.09.25, 20230925 3가지 형태로 섞여있다.
# parse_dates는 열 전체가 같은 형식일 경우만 적용.
# 형식이 섞여있을 때는 pd.to_datetime(s, format="mixed")

df["date"] = pd.to_datetime(df["date"], format="mixed")
print(f" date dtype={df['date'].dtype}") 

"""
    encoding : utf-8 / utf-8-sig
    parse_dates : 날짜열을 datetime으로
    na_values  : 결측으로 취급한 문자열 지정
    thousands : 천단위 구분자 제거
    dtype: 열별 타입 강제 지정
    ...
"""

# read_csv를 통해서 가져온 데이터 확인방법

# 위쪽기준 n개의 데이터를 불러와서 표시해 준다.
print(df.head(3).to_string(index=False))

#가져온 데이터의 개수, 컬럼의 개수를 확인
print(f"df.shape : {df.shape}")

# 가져온 데이터의 컬럼별 정상로우 갯수, 각 컬럼별 타입등을 확인
df.info()

print("df.types : 컬럼별 데이터 타입")
print(df.dtypes.to_string())