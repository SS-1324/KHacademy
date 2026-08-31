"""
Pandas 데이터 정제 실습문제
오염된 시세 데이터를 정제하는 파이프라인을 처음부터 끝까지 직접 만든다.

TODO 부분을 채워 완성하세요.
맨 아래 실행부와 validate() 는 그대로 두면 됩니다.

앞 문제의 결과를 뒤 문제가 이어받습니다. 반드시 순서대로 푸세요.
앞 함수가 미완성이면 뒤 문제는 실행되지 않습니다.

행을 지우는 함수는 PRACTICE 2 하나뿐입니다. 나머지는 행 수가 그대로여야 합니다.
어느 함수도 넘겨받은 원본을 바꾸지 않습니다. 항상 새 DataFrame 을 돌려주세요.

실행 : python practice.py
"""

import pandas as pd

from _data import load_raw

# 화면 표시 설정. 표가 줄바꿈되지 않게 넓힌다. 데이터가 아니라 보이는 방식만 바꾼다.
pd.set_option("display.width", 130)

# 숫자여야 하는 열
NUM_COLS = ["open", "high", "low", "close", "volume", "change", "changeRate"]
# 시가·고가·저가·종가
OHLC = ["open", "high", "low", "close"]


# =========================================================================
# PRACTICE 1. 타입 변환
#   읽어 온 표는 숫자여야 할 열이 문자열이고 날짜도 문자열이다. 제 타입으로 바꾼다.
#     · NUM_COLS 7개 열을 숫자로
#     · date 열을 날짜로
#     · code 열을 대문자로 바꾸고 앞뒤 공백을 없앤다
#     · 숫자로 바꾸지 못한 값("-" 같은 것)은 에러를 내지 말고 결측으로 둔다
#
#   [기대 결과]
#     행 수 92,721 그대로       date dtype datetime64
#     close 결측 1,102건        volume 결측 5,696건
#
#   [힌트] "1,250,000" 은 콤마 때문에 숫자로 안 바뀐다. 콤마를 먼저 지운다.
#            s.astype(str).str.replace(",", "", regex=False)
#          숫자 변환   : pd.to_numeric(s, errors="coerce")  -> 실패한 값이 NaN 이 된다
#          날짜 변환   : pd.to_datetime(s, format="mixed")  -> 행마다 형식을 따로 추론한다
#          문자열 정리 : s.str.upper() / s.str.strip()
#          원본을 건드리지 않으려면 맨 앞에서 df.copy() 부터 한다.
# =========================================================================
def cast_types(df: pd.DataFrame) -> pd.DataFrame:
    """모든 열을 제 타입으로 바꾼 새 DataFrame 을 반환한다. 행 수는 그대로다."""
    cp = df.copy()

    for col in NUM_COLS:
        cp[col] = pd.to_numeric(
            cp[col].astype(str).str.replace(",", "", regex=False), errors="coerce"
        )

    cp["date"] = pd.to_datetime(cp["date"], format="mixed", errors="coerce")
    cp["code"] = cp["code"].astype(str).str.upper().str.strip()

    return cp


# =========================================================================
# PRACTICE 2. 중복 제거
#   (code, date) 조합은 하나뿐이어야 한다. 같은 종목의 같은 날이 두 번 있으면
#   이후 집계가 전부 부풀려진다. 먼저 들어온 행을 원본으로 보고 남긴다.
#
#   [기대 결과]
#     92,721행 -> 90,000행      종목별 행 수가 전부 750 으로 같아진다
#
#   [힌트] df.drop_duplicates(subset=["기준열1", "기준열2"], keep="first")
#            반환은 중복 행이 빠진 '새 DataFrame' 이다. 원본은 그대로 남는다.
#            keep 은 "first" / "last" / False 중에 고른다.
#          행을 걸러내면 인덱스가 띄엄띄엄 남는다. reset_index(drop=True) 로 다시 매긴다.
#            drop=True 를 빼면 옛 인덱스가 'index' 라는 열로 남아 버린다.
#
#   ★ 이 작업은 반드시 타입 변환 '뒤에' 와야 한다.
#     날짜가 문자열인 동안에는 '2023-09-25' 와 '20230925' 가 서로 다른 값이라
#     형식만 다른 같은 날짜가 중복으로 걸러지지 않는다.
# =========================================================================
def drop_duplicated(df: pd.DataFrame) -> pd.DataFrame:
    """(code, date) 중복이 빠진 새 DataFrame 을 반환한다."""

    return df.drop_duplicates(subset=["code", "date"], keep="first").reset_index(drop=True)

# =========================================================================
# PRACTICE 3. 이상치 표시
#   말이 안 되는 값을 찾아 NaN 으로 '표시' 한다. 행은 지우지 않는다.
#   지우면 그날의 시가·고가·저가까지 함께 사라진다. 표시해 두고 다음 문제에서 채운다.
#
#   찾아야 할 것 세 가지
#     ① 통계 : 종목별 종가의 IQR 을 구해 Q1 - 1.5*IQR 보다 작거나 Q3 + 1.5*IQR 보다 큰 값
#     ② 논리 : 종가가 그날의 고가보다 높거나 저가보다 낮은 행
#     ③ 논리 : 거래량이 음수인 행  (0 이 아니라 NaN 으로)
#
#   [기대 결과]
#     행 수 90,000 그대로   종가 결측 3,607건   거래량 결측 5,960건   거래량 음수 0건
#
#   [힌트] 먼저 df.sort_values(["code", "date"]).reset_index(drop=True) 로 줄을 세운다.
#          사분위수  : q1, q3 = s.quantile([0.25, 0.75])
#          종목별로  : 한 종목의 Series 를 받아 '같은 길이의 bool Series' 를 돌려주는
#                      함수를 만들고 df.groupby("code")["close"].transform(그_함수) 에 넘긴다.
#                      길이가 같아야 그대로 마스크로 쓸 수 있다.
#          표시하기  : df.loc[마스크, "close"] = pd.NA
#                      pd.NA 를 넣으면 열 dtype 이 흔들릴 수 있다.
#                      마지막에 pd.to_numeric(..., errors="coerce") 로 한 번 정리한다.
#
#   ★ 전체 기준 IQR 로 잡으면 안 된다. 1,000원짜리 종목과 50만원짜리 종목이 섞여 있어
#     비싼 종목이 통째로 이상치가 된다.
# =========================================================================
def mark_outliers(df: pd.DataFrame) -> pd.DataFrame:
    """이상치 자리를 NaN 으로 바꾼 새 DataFrame 을 반환한다. 행 수는 그대로다."""
    df = df.sort_values(["code", "date"]).reset_index(drop=True)

    def is_outlier(s):
        """
            한 종목의 종가목록을 받아서 같은 길이의 bool mask를 돌려준다.
            각 자리의 True/False로 이상치인가 아닌가의 값을 리턴
        """
        q1, q3 = s.quantile([0.25,0.75])
        iqr = q3 - q1
        lo, hi = q1 - 1.5 * iqr, q3 + 1.5 * iqr
        return (s < lo) | (s > hi)

    # df.groupby("code")["close"].transform(함수)
    # 원본과 길이가 같은 시리즈가 반환. 각 행에 그 행이 속한 그룹의 함수 결과가 채워짐.
    #  코드별로 IQR을 활용한 이상치 mask생성
    sate = df.groupby("code")["close"].transform(is_outlier)

    #논리적으로 종가의 이상치를 판단한 mask생성
    logic = (df["close"] > df["high"]) | (df["close"] < df["low"])

    #이상치 판별 mask 둘중 하나라도 충족하면 NaN를 대입
    df.loc[sate | logic, "close"] = pd.NA
    df["close"] = pd.to_numeric(df["close"], errors="coerce")

    #거래량이상치 NaN로 변환
    df.loc[df["volume"] < 0, "volume"] = pd.NA
    return df


# =========================================================================
# PRACTICE 4. 결측 보간
#   앞 문제에서 NaN 으로 표시해 둔 자리를 메운다.
#     · OHLC 네 열은 보간으로 채운다
#     · volume 은 채우지 않고 NaN 으로 남긴다
#
#   [기대 결과]
#     종가 결측 3,607건 -> 0건      거래량 결측은 5,960건 그대로
#
#   [힌트] 보간     : s.interpolate() -> 앞뒤 값 사이를 직선으로 이어 빈칸을 추정한다.
#                     앞뒤에 값이 '있어야' 채워진다. 맨 앞과 맨 뒤는 남는다.
#          마무리   : s.ffill() 은 바로 앞 값으로, s.bfill() 은 바로 뒤 값으로 채운다.
#                     이어 붙여 쓰면 맨 앞은 bfill 이, 맨 뒤는 ffill 이 맡는다.
#          종목별로 : df.groupby("code")[col].transform(lambda s: ...)
#                     interpolate 는 "mean" 처럼 이름으로 부를 수 없어서 lambda 로 감싼다.
#
#   ★★ 종목별로 하지 않으면 A 종목의 마지막 값과 B 종목의 첫 값을 직선으로 이어 버린다.
#      전혀 다른 회사의 주가가 섞여 들어가는데, 값이 그럴듯해서 눈치채기 어렵다.
#
#   ★ 거래량을 0으로 채우면 안 된다. '거래가 없던 날' 과 '모르는 날' 은 전혀 다르다.
#     모르면 NaN 으로 두는 편이 낫다.
# =========================================================================
def fill_missing(df: pd.DataFrame) -> pd.DataFrame:
    """OHLC 결측을 종목별로 채운 새 DataFrame 을 반환한다."""
    df = df.sort_values(["code", "date"]).reset_index(drop=True)

    for col in OHLC:
        df[col] = df.groupby("code")[col].transform(
            lambda s: s.interpolate().ffill().bfill()
        )

    
    return df


# =========================================================================
# PRACTICE 5. OHLC 정합성 보정
#   실행부가 PRACTICE 4 직후에 '저가 <= 종가 <= 고가' 를 깬 행이 몇 건인지 출력한다.
#   0이 아닐 것이다. 왜 그런지 먼저 생각해 보고, 그 행들을 고쳐라.
#
#   행을 지우지 말 것. 종가를 규칙 안으로 밀어 넣는다.
#
#   [기대 결과]
#     규칙을 깬 행 1,429건 -> 0건       행 수는 90,000 그대로
#
#   [힌트] s.clip(lower=아래한계, upper=위한계)
#            반환 : 원본과 길이가 같은 Series.
#                   lower 보다 작으면 lower 로, upper 보다 크면 upper 로 끌어당긴다.
#            한계에 '숫자' 대신 '열' 을 넘기면 행마다 각자의 기준이 적용된다.
# =========================================================================
def enforce_ohlc(df: pd.DataFrame) -> pd.DataFrame:
    """종가를 그날의 저가~고가 범위 안으로 넣은 새 DataFrame 을 반환한다."""
    df = df.copy()

    df["close"] = df["close"].clip(lower=df["low"], upper=df["high"])
    
    return df


# =========================================================================
# PRACTICE 6. 등락 재계산
#   종가를 고쳤으니 등락액·등락률도 다시 계산해야 한다.
#   안 하면 '고쳐진 종가' 와 '옛날 등락률' 이 한 행에 섞여 앞뒤가 안 맞는 표가 된다.
#
#     change     = 오늘 종가 - 전일 종가                        (소수점 0자리로 반올림)
#     changeRate = (오늘 종가 - 전일 종가) / 전일 종가 * 100    (소수점 2자리로 반올림)
#
#   [기대 결과]
#     change 결측 120건 (종목마다 첫날은 '전일' 이 없다)
#     changeRate 가 상식적인 범위 안으로 들어온다
#
#   [힌트] s.shift() -> 원본과 길이가 같은 Series. i번째 자리에 s[i-1] 이 온다.
#                       '전일 종가' 를 같은 행에 나란히 놓는 도구다.
#          반올림    : s.round(0) / s.round(2)
#
#   ★ 종목별로 해야 한다. groupby 없이 shift 하면 종목이 바뀌는 첫 행에서
#     앞 종목의 마지막 종가를 전일 값으로 끌어온다.
# =========================================================================
def recompute_change(df: pd.DataFrame) -> pd.DataFrame:
    """change 와 changeRate 를 다시 계산한 새 DataFrame 을 반환한다."""
    df = df.sort_values(["code", "date"]).reset_index(drop=True)

    #groupby가 없다면 종목이 바뀌는 첫 행에 종목의 마지막 종가를 끌어온다.
    #첫날은 전 날의 종가가 없기때문에 NaN로 결측이 된다.
    #전일종가
    prev = df.groupby("code")["close"].shift()

    df["change"] = (df["close"] - prev).round(0) 
    df["changeRate"] = ((df["close"] - prev) / prev * 100).round(0)
    return df


# =========================================================================
# PRACTICE 7. 파이프라인으로 엮기
#   위 여섯 함수를 하나의 진입점으로 묶는다. 바깥에서는 이 함수 하나만 부르면 된다.
#
#   순서를 스스로 정하고, 왜 그 순서여야 하는지 생각해 볼 것.
#   순서가 틀리면 validate() 가 잡아낸다.
#
#   [기대 결과]
#     validate() 9개 항목 전부 통과
#
#   [힌트] 각 함수가 '새 DataFrame' 을 돌려주므로 df = 함수(df) 로 이어 붙인다.
#          verbose 가 True 면 단계마다 log(단계이름, df, 메모) 를 불러 행 수를 남긴다.
#          로그는 결과가 이상할 때 어느 단계에서 틀어졌는지 찾는 유일한 방법이다.
# =========================================================================
def clean_prices(df: pd.DataFrame, verbose: bool = True) -> pd.DataFrame:
    """오염된 시세 데이터를 정제한다. 이 함수 하나가 진입점이다."""
    print("=====================시작==========================")

    # 1. 타입변환 - 타입이 맞지 않으면 비교나 계산을 할 수 없음.
    df = cast_types(df)
    if verbose:
        print(f"타입변환", df)

    # 2. 중복제거 - 중복을 먼저 제거해야 통계기반의 정제가 편리하다.
    df = drop_duplicated(df)
    if verbose:
        print(f"중복제거", df)

    # 3. 이상치 표시 - 결측을 잡아내는 단계
    df = mark_outliers(df)
    if verbose:
        print(f"이상치 표시", df)

    # 4. 결측치 처리 - 결측보간 또는 대체값 적용.
    df = fill_missing(df)
    if verbose:
        print(f"결측치 처리", df)
    

    # 5. 결측치 처리로 생긴 이상치 재검증
    df = enforce_ohlc(df)
    if verbose:
        print(f"ohlc 재검증", df)

    # 6. 결측치 처리 후 연관값 재연산
    df = recompute_change(df)
    if verbose:
        print(f"등락 재계산", df)

    return df


# =========================================================================
# PRACTICE 8. (서술형) 두 번 돌리면 왜 결과가 달라지는가
#   실행부가 clean_prices 를 두 번 통과시킨 결과를 비교해 보여준다.
#   행 수와 모양은 같은데 값이 달라진다.
#
#   왜 그런지, 그리고 실무에서는 이것을 어떻게 해결하는지 아래 문자열에 적으세요.
#
#   [힌트] 이상치의 기준을 무엇으로부터 계산했는지 되짚어 볼 것.
#          한 번 정제하고 나면 그 '무엇' 이 어떻게 달라지는가.
# =========================================================================
ANSWER = """
    이상치 판정 기준을 IQR로 사용했을 때, IQR은 지금 시점의 데이터를 기준으로 분포를 계산.
    정제할 때마다 극단값이 사라져 분포가 좁아진다. 
    처음 정상이던 값이 두번째 실행에서 이상치에 걸린다.
    그 값이 NaN가 되었다가 보간으로 다른 값으로 채워지기 때문에, 값이 달라진다.

    이상치 기준을 매번 다시 계산하지 않고 처음 한번만 계산해서 저장한 뒤, 이후로는 저장된 기준을 그대로 적용.
"""


# =========================================================================
#  실행부  ―  아래는 수정하지 않아도 됩니다
# =========================================================================
def log(step_name, df, note=""):
    print(f"  [{step_name:<14}] {len(df):>7,}행  {note}")


def validate(df, verbose=True):
    """정제가 끝났다는 것을 숫자로 확인한다."""
    checks = [
        ("행 수 90,000", len(df) == 90_000),
        ("종목 수 120", df["code"].nunique() == 120),
        ("종목별 750행", df.groupby("code").size().nunique() == 1),
        ("날짜가 datetime", pd.api.types.is_datetime64_any_dtype(df["date"])),
        ("code+date 중복 0", df.duplicated(subset=["code", "date"]).sum() == 0),
        ("주말 없음", bool((df["date"].dt.dayofweek < 5).all())),
        ("OHLC 정합성", bool(((df["low"] <= df["close"]) & (df["close"] <= df["high"])).all())),
        ("거래량 음수 0", bool((df["volume"].dropna() >= 0).all())),
        ("종가 결측 0", df["close"].isna().sum() == 0),
    ]

    if verbose:
        print(f"\n  {'검증 항목':<24}{'결과'}")
        print("  " + "-" * 36)
        for name, ok in checks:
            print(f"  {name:<24}{'통과' if ok else '실패'}")

    failed = [n for n, ok in checks if not ok]
    if failed and verbose:
        print(f"\n  ⚠ 실패 : {failed}")
    return not failed


def section(title):
    print("\n" + "=" * 68)
    print(f" {title}")
    print("=" * 68)


def step(fn, prev, *extra):
    """앞 문제가 미완성이면 건너뛰고, 에러가 나도 실행이 멈추지 않게 감싼다."""
    if prev is None:
        print("  [건너뜀] 앞 문제를 먼저 완성하세요.")
        return None
    try:
        out = fn(prev, *extra)
    except Exception as e:
        print(f"  [미완성] {type(e).__name__}: {e}")
        return None
    if out is None:
        print("  [미완성] 함수가 아직 값을 돌려주지 않습니다.")
        return None
    return out


if __name__ == "__main__":

    raw = load_raw()
    print(f"원본 : {len(raw):,}행 {raw.shape[1]}열")
    print(f"  close dtype {raw['close'].dtype}  /  volume dtype {raw['volume'].dtype}")
    print("  숫자여야 할 열이 문자열이다. 이 상태로는 평균도 최댓값도 구할 수 없다.")

    section("PRACTICE 1. 타입 변환")
    d1 = step(cast_types, raw)
    if d1 is not None:
        print(f"  행 수       : {len(d1):>8,}")                    # 기대: 92,721
        print(f"  date dtype  : {d1['date'].dtype}")               # 기대: datetime64 (버전에 따라 [ns] 또는 [us])
        print(f"  close 결측  : {d1['close'].isna().sum():>8,}")   # 기대: 1,102
        print(f"  volume 결측 : {d1['volume'].isna().sum():>8,}")  # 기대: 5,696
        # 원본 raw 의 close 는 여전히 문자열이어야 한다. 숫자가 되어 있으면
        # 함수가 넘겨받은 원본을 그 자리에서 고쳤다는 뜻이다. (copy() 를 빠뜨린 경우)
        kept = not pd.api.types.is_numeric_dtype(raw["close"])
        print(f"  원본 보존   : {'통과' if kept else '실패 - 원본이 바뀌었다. copy() 를 확인할 것'}")

    section("PRACTICE 2. 중복 제거")
    d2 = step(drop_duplicated, d1)
    if d2 is not None:
        sizes = d2.groupby("code").size()
        print(f"  행 수        : {len(d2):>8,}")                   # 기대: 90,000
        print(f"  종목별 행 수 : 최소 {sizes.min()} / 최대 {sizes.max()}")   # 기대: 750 / 750
        print(f"  남은 중복    : {d2.duplicated(subset=['code', 'date']).sum():>8,}")  # 기대: 0

    section("PRACTICE 3. 이상치 표시")
    d3 = step(mark_outliers, d2)
    if d3 is not None:
        print(f"  행 수       : {len(d3):>8,}")                    # 기대: 90,000 (줄면 안 된다)
        print(f"  종가 결측   : {d3['close'].isna().sum():>8,}")   # 기대: 3,607
        print(f"  거래량 결측 : {d3['volume'].isna().sum():>8,}")  # 기대: 5,960 (음수 427건이 NaN 이 되었다)
        print(f"  거래량 음수 : {int((d3['volume'] < 0).sum()):>8,}")   # 기대: 0
        print(f"  종가 최댓값 : {d3['close'].max():>8,.0f}")       # 기대: 579,409

    section("PRACTICE 4. 결측 보간")
    d4 = step(fill_missing, d3)
    if d4 is not None:
        print(f"  종가 결측   : {d4['close'].isna().sum():>8,}")   # 기대: 0
        print(f"  거래량 결측 : {d4['volume'].isna().sum():>8,}")  # 기대: 5,960 (채우지 않는다)

    section("PRACTICE 5. OHLC 정합성 보정")
    if d4 is not None:
        broken = int(((d4["close"] < d4["low"]) | (d4["close"] > d4["high"])).sum())
        print(f"  보간 직후 '저가 <= 종가 <= 고가' 를 깬 행 : {broken:,}건")   # 기대: 1,429
        print("    보간은 앞뒤 값을 직선으로 잇는다. 그날의 고가·저가는 보지 않는다.")
        print("    통계로 채운 값이 도메인 규칙을 깨뜨리는 전형적인 사례다.")
    d5 = step(enforce_ohlc, d4)
    if d5 is not None:
        after = int(((d5["close"] < d5["low"]) | (d5["close"] > d5["high"])).sum())
        print(f"\n  보정 후 규칙을 깬 행 : {after:,}건")            # 기대: 0
        print(f"  행 수                : {len(d5):,}")             # 기대: 90,000

    section("PRACTICE 6. 등락 재계산")
    d6 = step(recompute_change, d5)
    if d6 is not None:
        print(f"  change 결측     : {d6['change'].isna().sum():>8,}")   # 기대: 120
        print(f"  changeRate 최대 : {d6['changeRate'].max():>8,.2f} %")
        print(f"  changeRate 최소 : {d6['changeRate'].min():>8,.2f} %")

    section("PRACTICE 7. 파이프라인으로 엮기")
    cleaned = step(clean_prices, raw)

    if cleaned is not None:
        ok = validate(cleaned)
        print(f"\n  전체 통과 : {ok}")
        print("\n  정제가 끝났다는 것을 '느낌' 이 아니라 '숫자' 로 확인한다.")
        print("  통과하지 못하면 다음 단계로 넘기지 않는다.")

        raw_close = pd.to_numeric(
            raw["close"].astype(str).str.replace(",", "", regex=False), errors="coerce"
        )
        print("\n  [정제 전후 비교]")
        print(f"    {'항목':<14}{'정제 전':>18}{'정제 후':>18}")
        print("    " + "-" * 50)
        print(f"    {'행 수':<13}{len(raw):>18,}{len(cleaned):>18,}")
        print(f"    {'종가 평균':<12}{raw_close.mean():>18,.0f}{cleaned['close'].mean():>18,.0f}")
        print(f"    {'종가 최댓값':<11}{raw_close.max():>18,.0f}{cleaned['close'].max():>18,.0f}")
        print(f"    {'종가 표준편차':<10}{raw_close.std():>18,.0f}{cleaned['close'].std():>18,.0f}")

    section("PRACTICE 8. 두 번 돌리면 왜 결과가 달라지는가")
    if cleaned is None:
        print("  [건너뜀] PRACTICE 7 을 먼저 완성하세요.")
    else:
        twice = step(clean_prices, cleaned.copy(), False)
        if twice is not None:
            a = cleaned.reset_index(drop=True)
            b = twice.reset_index(drop=True)
            # .equals() : 두 DataFrame 의 모양·값·dtype 이 모두 같은지 한 번에 비교한다.
            #   == 는 요소마다 비교한 표를 돌려주므로 전체 판정에는 쓸 수 없다.
            #   NaN 끼리도 "같다" 로 쳐 주는 것이 == 와 다른 점이다.
            diff = int((a["close"] != b["close"]).sum())
            print(f"  한 번 통과 : {a.shape}")
            print(f"  두 번 통과 : {b.shape}")
            print(f"\n  형태가 같은가 : {a.shape == b.shape}")
            print(f"  값이 같은가   : {a.equals(b)}")
            print(f"  종가가 달라진 행 : {diff:,}건 / {len(a):,}건 ({diff / len(a):.2%})")

            print("\n  [제출한 답]")
            print("  " + ANSWER.strip().replace("\n", "\n  "))
