"""
    데이터 정제 전에 데이터가 정상인지 여부를 판단하는 방법
"""

import pandas as pd
from _load import RAW_PATH, ENCODING

#화면 표시 설정
pd.set_option("display.width", 130)
pd.set_option("display.max_columns", 20)

# 옵션없이 그대로 데이터 읽어오기
df = pd.read_csv(RAW_PATH, encoding=ENCODING)

print(f"{df.shape[0]}행 {df.shape[1]}열\n")
print(df.head(3).to_string(index=False))

print("\ndtypes:")
print(df.dtypes.to_string())


"""
    close, volume는 숫자여야하는데 문자열?
    read_csv는 열마다 타입을 추론하는데, 
    한 열에 숫자가 아닌 값이 '하나라도'존재하면 그 열 전체는 문자열이된다.
"""
print("\n 통계를 내보면")
print(df[["close", "high"]].describe().to_string())
# close는 값이 정상적으로 나오지 않는다. high는 정상적으로 값이 출력

# df.info()
# 행 수, 열이름, non-null 개수, dtype을 바로 출력

df.info()

# non-null 개수는 전체 행수와 비교해서 나온다.
# 전체 행수와 다르면 결측이 있다는거다.

# 결측률 
# df.isnull() -> df.isna()동일
# 원본과 모양이 같은 True/False mask표를 반환 결측인자리가 True

na = df.isnull().sum()
print(f"{'열':<14}{'결측 수':<14}{'비율':<14}")
for col in df.columns:
    if na[col] > 0:
        print(f"{col:<14} {na[col]:<14} {na[col]/len(df) * 100:>9.2f}%")

# pands에서 read_csv는 일부 문자열에 대해서는 알아서 결측(N/A)으로 변경한다.
# 파일에 N/A라고 적혀있던 값은 읽을 때 NaN가 된다. 다른 문자들은 전부 결측처리가 되지 않는다.

# read_csv(dtype=str, keep_default_na=False) -> 파일을 적힌 그대로 읽어옴
# dtype=str옵션은 모든열을 문자열로 가져오겠다.
# "N/A"와같이 자동으로 nan해줬던 값들을 바꾸지않고 그대로 글자로 가져옴.

literal = pd.read_csv(RAW_PATH, encoding=ENCODING, dtype=str, keep_default_na=False)

print(f"{'열':<14}{'N/A':>10}{'-':>10}{'빈칸':>10}")
for col in ["close", "volume"]:
    print(f"{col:<14}{(literal[col] == 'N/A').sum():>10}"
          f"{(literal[col] == '-').sum():>10} {(literal[col] == '').sum():>10}")

# 동일하게 데이터가 오염되었어도 N/A은 잡히고 -안잡힐 수 있다.
# 결측률은 타입 변환 후에 알 수 있다.
# 읽어올 때 이런 결측에 대한 데이터가 이미 있다면, na_values=["N/A", "-"]전달하면 전부 처리가 가능.

# value_counts()
# 어떤값이 몇번 나오는가?
# s.value_counts(dropna=False) -> 값을 인덱스로, 개수를 값으로 가진 Series. 
# dropna=False을 주면 NaN도 하나의 값으로 처리한다.
print()

# 종가는 값이 다 달라서 보통 value_counts가 큰 의미가 없다.
# 하지만 오염된 데이터를 찾을 때는 이런식으로 확인이 가능하다.
print(df["close"].value_counts(dropna=False).head(5).to_string())

#to_numeric(errors="coerce") : 숫자로 못 변경하는 값을 에러대신 NaN로 만든다.
for col in ["close", "volume"]:
    bad = pd.to_numeric(literal[col], errors="coerce").isna()
    kinds = literal.loc[bad, col].value_counts().head(3)
    print(f"{col:<10} {bad.sum():>6}건 -> {kinds.index.tolist()}")

#콤마가 든 값도 결측에 포함이 된다. -> ,제거시 변환이 가능한 데이터

#데이터 중복
# df.duplicated(subset=["기준열1", "기준열2"])
# 원본과 길이가 같은 True/Flase mask가 반환.
# 두번째로 나온 것부터 True처리를 한다, 첫 번째로 나온 데이터는 False이다.

dup = df.duplicated(subset=["code", "date"]).sum()
print(f"(code, date)중복건수 : {dup}")
print(f"중복 제거시 {len(df) - dup}행")

print("="*60)
#날짜 형식(데이터에는 3가지 존재)
# .str.len() : 각 갑의 글자수를 담은, 원본과 길이가 같은 series반환
# .str.contains() : 패턴이 들어있으면 True, 없으면 Flase인 mask를 하나 반환

# 2023-09-25, 2023.09.25 -> 10글자
# 20230925 -> 8글자
lens = df["date"].astype(str).str.len().value_counts()
dot = df["date"].astype(str).str.contains(r"\.", regex=True).sum()
print(f"2025-09-25형식 : {lens.get(10, 0) - dot}건")
print(f"2025.09.25형식 : {dot}건")
print(f"20250925형식 : {lens.get(8, 0)}건")

tmp = df.copy()
tmp["date"] = pd.to_datetime(tmp["date"], format="mixed")
dup_after = tmp.duplicated(subset=["code", "date"]).sum()
print(f" 날짜를 datetime으로 통일한 뒤 중복 : {dup_after}건")

# G0001, 2023-09-25
# G0001, 20230925 
# 그냥 중복검사 했을 때는 다른행으로 인식이 된다.
print(f" {dup_after - dup}건 ")


tmps = pd.DataFrame({
    "code":["G0001","G0001","G0002"],
    "date":["2023-09-25", "20230925", "2023-09-25"],
    "close":[24015,24015,27753],
})

print(tmps.to_string(index=False))
print(f"문자열 상태에서 중복 : {tmps.duplicated(subset=['code', 'date']).sum()}건")
tmps['date'] = pd.to_datetime(tmps['date'], format='mixed')
print(f"datetime 변환후 중복 : {tmps.duplicated(subset=['code', 'date']).sum()}건")

# 어떤 데이터가 문제가 있는지 진단 ->
# -> 타입 -> 중복 -> 결측 -> 이상치 -> 검증

# 데이터 정제 목표를 정하기.
print(f"{'항목':<24} {'현재':<14} {'목표':<14}")
print("="*60)
print(f"{'행수':<24} {len(df):<14} {'90,000':<14}")
print(f"{'종목 수':<24} {df['code'].nunique():<14} {'120':<14}")
print(f"{'종목별 행 수(최소~최대)':<18}"
      f"{str(df.groupby('code').size().min()) + '~' + str(df.groupby('code').size().max()):<14} {'750':<14}")