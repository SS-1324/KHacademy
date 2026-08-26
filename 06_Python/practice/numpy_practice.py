"""
Numpy 실습문제

TODO 부분을 채워 완성하세요.
맨 아래 실행부는 그대로 두면 됩니다. 함수만 완성하면 결과가 출력됩니다.
반복문(for / while)은 한 번도 쓰지 않고 풀 수 있습니다.
"""

import numpy as np

from _data import (load_column, load_dates, load_dirty, load_flat,
                   load_matrix, load_codes, load_one_stock)

np.set_printoptions(suppress=True)


# =========================================================================
# PRACTICE 1. 벡터화
#   종가 배열에서 일간 수익률 (오늘 - 어제) / 어제 를 구한다.
#     반복문을 쓰지 않고 배열 연산만으로 구한다.
#     결과 길이는 입력보다 하나 짧다. 첫날은 '어제' 가 없기 때문이다.
#
#   [출력 예시]
#     daily_returns(np.array([100, 110, 99])) -> [ 0.1  -0.1]
#
#   [힌트] arr[1:] 이 '오늘', arr[:-1] 이 '어제' 다.
#          길이가 같은 두 배열을 그냥 빼고 나누면 된다.
# =========================================================================
def daily_returns(prices: np.ndarray) -> np.ndarray:
    """일간 수익률 (n-1,) 배열을 반환한다."""

    return (prices[1:] - prices[:-1]) / prices[:-1]


# =========================================================================
# PRACTICE 2. astype 의 버림
#   실수 금액 배열을 '반올림한' int64 배열로 바꾼다.
#     astype 만 쓰면 소수점 아래가 잘려서 금액이 새어나간다.
#
#   [출력 예시]
#     to_won(np.array([52000.9, -3.7]))
#       astype 만 -> [52000  -3]
#       정답      -> [52001  -4]
#
#   [힌트] 반올림을 먼저 하고 타입을 바꾼다. 순서가 중요하다.
# =========================================================================
def to_won(values: np.ndarray) -> np.ndarray:
    """실수 배열을 반올림한 int64 배열로 반환한다."""
    return np.round(values).astype("int64")


# =========================================================================
# PRACTICE 3. 뷰와 복사
#   마지막 n일에서 그 구간의 최솟값을 뺀 배열을 반환한다.
#     원본 prices 는 절대 바뀌면 안 된다.
#
#   [출력 예시]
#     normalize_tail(np.array([10, 20, 30, 40, 50]), 3) -> [ 0 10 20]
#     호출 후 원본은 그대로 [10 20 30 40 50]
#
#   [힌트] 슬라이싱 결과는 원본을 가리키는 창이다. 그 자리를 고치면 원본이 바뀐다.
#          잘라낸 뒤 한 단계를 더 거치면 원본과 끊어진다.
# =========================================================================
def normalize_tail(prices: np.ndarray, n: int = 5) -> np.ndarray:
    """마지막 n일을 최솟값 기준으로 옮긴 (n,) 배열을 반환한다. 원본은 보존된다."""
    recent = prices[-n:].copy()

    recent -= recent.min()

    return recent


# =========================================================================
# PRACTICE 4. 집계와 argmax / argmin
#   최고가·최저가와 '그날의 날짜' 를 함께 반환한다.
#     prices 와 dates 는 길이와 순서가 같다.
#
#   [출력 예시]
#     peak_and_trough(prices, dates)
#       -> (25899, numpy.datetime64('2023-10-16'), 8885, numpy.datetime64('2026-05-21'))
#
#   [힌트] max 는 '값', argmax 는 '그 값이 있는 위치' 다.
#          위치를 얻으면 길이가 같은 다른 배열에서 같은 자리를 꺼낼 수 있다.
# =========================================================================
def peak_and_trough(prices: np.ndarray, dates: np.ndarray) -> tuple:
    """(최고가, 최고가 날짜, 최저가, 최저가 날짜) 튜플을 반환한다."""
    max_idx = prices.argmax()
    min_idx = prices.argmin()

    return prices[max_idx], dates[max_idx], prices[min_idx], dates[min_idx]


# =========================================================================
# PRACTICE 5. reshape 와 2차원 인덱싱
#   ① '한 종목이 n_days 줄' 순서로 늘어선 1차원 배열을 2차원 표로 세운다.
#      종목 수는 직접 세지 않는다.
#   ② 모든 종목의 마지막 날 종가만 1차원으로 꺼낸다.
#
#   [출력 예시]
#     to_matrix(flat, 750)        (90000,) -> (120, 750)
#     last_day_prices(matrix)     (120, 750) -> (120,)
#
#   [힌트] reshape 에서 -1 은 '이 자리는 전체 개수를 보고 알아서 계산하라' 는 뜻이다.
#          2차원은 [행, 열] 이고 콜론(:) 이 '전부' 다.
# =========================================================================
def to_matrix(flat: np.ndarray, n_days: int) -> np.ndarray:
    """1차원 배열을 (종목 수, n_days) 2차원 배열로 반환한다."""
    return flat.reshape(-1, n_days)
    


def last_day_prices(matrix: np.ndarray) -> np.ndarray:
    """모든 종목의 마지막 날 종가를 (종목 수,) 로 반환한다."""
    return matrix[:,-1]
    


# =========================================================================
# PRACTICE 6. axis 와 keepdims
#   ① 종목별 평균가        (120, 750) -> (120,)
#   ② 날짜별 평균가        (120, 750) -> (750,)
#   ③ 각 종목에서 자기 평균을 뺀 배열   (120, 750) -> (120, 750)
#      결과의 종목별 평균은 0 이 되어야 한다.
#
#   [힌트] axis 는 '사라지는 축' 이다. 원하는 결과의 개수를 먼저 정하면 축이 정해진다.
#          ③ 에서 평균을 그냥 빼면 형태가 맞지 않아 ValueError 가 난다.
#          줄인 축을 크기 1로 남겨 두는 옵션이 있다.
# =========================================================================
def mean_by_stock(matrix: np.ndarray) -> np.ndarray:
    """종목별 평균가 (종목 수,) 를 반환한다."""
    return matrix.mean(axis=1)


def mean_by_date(matrix: np.ndarray) -> np.ndarray:
    """날짜별 평균가 (날짜 수,) 를 반환한다."""
    return matrix.mean(axis=0)


def center_by_stock(matrix: np.ndarray) -> np.ndarray:
    """각 종목에서 자기 평균을 뺀 (종목 수, 날짜 수) 배열을 반환한다."""
    means = matrix.mean(axis=1, keepdims=True) # (120, 1)
    return matrix - means


# =========================================================================
# PRACTICE 7. 브로드캐스팅
#   ① 종목별 z-score : (값 - 그 종목 평균) / 그 종목 표준편차
#      결과는 종목마다 평균 0, 표준편차 1 이 된다.
#   ② 기준화 지수 : 각 종목의 첫날 가격을 100 으로 맞춘다.
#      모든 행의 첫 값이 100.0 이 되어야 한다.
#
#   [힌트] (n, 1) 과 (n, m) 은 뒤에서부터 비교할 때 1 vs m 이라 통과한다.
#          matrix[:, 0] 은 (n,) 라 형태가 맞지 않는다.
#          대괄호를 한 겹 더 씌우면 축이 사라지지 않는다.
# =========================================================================
def zscore_by_stock(matrix: np.ndarray) -> np.ndarray:
    """
        종목별 z-score 배열을 반환한다.
        z-score : 어떤 데이터가 평균으로부터 얼마나 떨어져있는가?
    """
    means = matrix.mean(axis=1, keepdims=True)
    stds = matrix.std(axis=1, keepdims=True)

    return (matrix - means) / stds


def rebase_to_100(matrix: np.ndarray) -> np.ndarray:
    """
        각 종목의 첫날을 100 으로 맞춘 지수 배열을 반환한다.
        matrix[:, 0] -> (n,) 1차원배열
        matrix[:, 0:1] -> (n, 1) 2차월배열
        matrix[:, [0]] -> (n, 1) 2차월배열

        기준화 지수 :평균을 기존으로 얼마나 잘했는지
    """
    return matrix / matrix[:, 0] * 100
    


# =========================================================================
# PRACTICE 8. 불리언 마스킹과 조건 결합
#   ① 종목마다 threshold 를 넘게 오른 날이 며칠인지 센다.  -> (종목 수,)
#   ② 'threshold 넘게 오르면서 동시에 거래량이 v_th 를 넘은' 칸이 몇 개인지 센다.
#      returns 와 volume 은 형태가 같다.
#
#   [힌트] 비교 연산은 True/False 배열을 만든다. True=1 이라 합계가 곧 개수다.
#          배열에는 and / or 를 쓸 수 없다. & | ~ 를 쓴다.
#          & 는 비교 연산자보다 우선순위가 높으므로 조건마다 괄호가 필요하다.
# =========================================================================
def surge_days_per_stock(returns: np.ndarray, threshold: float = 0.03) -> np.ndarray:
    """종목별 급등일 수 (종목 수,) 를 반환한다."""
    return (returns > threshold).sum(axis=1)

def count_surge_on_heavy_volume(returns: np.ndarray, volume: np.ndarray,
                                r_th: float = 0.03, v_th: int = 2_000_000) -> int:
    """두 조건을 모두 만족하는 칸의 개수를 반환한다."""
    both = (returns > r_th) & (volume > v_th)

    return both.sum()


# =========================================================================
# PRACTICE 9. np.where 와 '위치로 꺼내는' 패턴
#   ① 수익률을 '급등'(big 초과) / '급락'(-big 미만) / '보합'(그 외) 으로 분류한다.
#      입력과 같은 형태의 문자열 배열이 나온다.
#   ② 변동계수(표준편차 / 평균)가 max_cv 미만인 종목의 '이름' 을 반환한다.
#      matrix 와 names 는 종목 수가 같다.
#
#   [출력 예시]
#     label_returns(np.array([0.05, -0.01, -0.09]))
#       -> ['급등' '보합' '급락']
#     pick_stable(matrix, names) -> ['G0006' 'G0007' 'G0032' ...]
#
#   [힌트] np.where(조건, 참일_때, 거짓일_때).
#          '거짓일 때' 자리에 np.where 를 한 번 더 넣으면 세 갈래가 된다.
#          ② 는 종목마다 값 하나씩 나오게 통계를 낸 뒤, 그 마스크를 names 에 그대로 쓴다.
# =========================================================================
def label_returns(returns: np.ndarray, big: float = 0.03) -> np.ndarray:
    """수익률을 '급등'/'급락'/'보합' 문자열 배열로 반환한다."""
    return np.where(returns > big, "급등",
             np.where(returns < -big,  "급락", "보합"))
    


def pick_stable(matrix: np.ndarray, names: np.ndarray,
                max_cv: float = 0.15) -> np.ndarray:
    """변동계수가 max_cv 미만인 종목의 이름 배열을 반환한다."""
    cv = matrix.std(axis=1) / matrix.mean(axis=1)

    return names[cv < max_cv]
    


# =========================================================================
# PRACTICE 10. 결측과 이상치
#   ① 결측의 (개수, 비율, 위치 배열) 을 반환한다.
#   ② 결측을 '결측을 뺀 평균' 으로 채운 새 배열을 반환한다. 원본은 바꾸지 않는다.
#   ③ 평균에서 표준편차의 k 배보다 멀리 떨어진 값의 '위치' 를 반환한다.
#      데이터에 결측이 섞여 있다는 점에 주의한다.
#
#   [출력 예시]
#     nan_report(dirty)   -> (12, 0.016, array([ 37,  88, 142, ...]))
#     find_outliers(dirty) -> [ 61 214 389 556 671]
#
#   [힌트] arr == np.nan 으로는 결측을 하나도 찾지 못한다. np.isnan 을 쓴다.
#          True=1 이므로 마스크의 합은 개수, 평균은 비율이다.
#          np.where(마스크)[0] 이 True 인 자리의 번호다.
#          결측이 하나라도 있으면 mean / std 는 nan 이 된다. nan 계열 함수를 쓴다.
#          부호와 상관없이 '얼마나 떨어졌나' 만 보려면 절댓값을 쓴다.
# =========================================================================
def nan_report(arr: np.ndarray) -> tuple:
    """(결측 개수, 결측 비율, 결측 위치 배열) 을 반환한다."""
    mask = np.isnan(arr)
    return mask.sum(), mask.mean(), np.where(mask)[0]


def fill_with_mean(arr: np.ndarray) -> np.ndarray:
    """결측을 평균으로 채운 새 배열을 반환한다."""
    return np.where(np.isnan(arr), np.nanmean(arr), arr)


def find_outliers(arr: np.ndarray, k: float = 3.0) -> np.ndarray:
    """평균에서 표준편차의 k 배 넘게 벗어난 값의 위치 배열을 반환한다."""
    mean = np.nanmean(arr)
    std = np.nanstd(arr)

    return np.where((arr - mean) > (k * std))[0]


# =========================================================================
#  실행부  ―  아래는 수정하지 않아도 됩니다
# =========================================================================
def section(title: str) -> None:
    print("\n" + "=" * 60)
    print(f" {title}")
    print("=" * 60)


def run(fn) -> None:
    """아직 완성하지 않은 문제가 있어도 실행이 멈추지 않게 감싼다."""
    try:
        fn()
    except Exception as e:
        print(f"  [미완성] {type(e).__name__}: {e}")


def need(*values) -> None:
    """TODO 가 남아 있으면(None 이 반환되면) 여기서 멈춘다."""
    if any(v is None for v in values):
        raise ValueError("아직 완성되지 않았습니다")


if __name__ == "__main__":
    dates = load_dates()                        # (750,)   거래일
    names = load_codes()                        # (120,)   종목 코드
    matrix = load_matrix()                      # (120, 750) 종가
    one = load_one_stock(0)                     # (750,)   첫 종목 종가

    section("PRACTICE 1. 벡터화")

    def p1():
        r = daily_returns(one)
        need(r)
        print(f"  입력 {one.shape} -> 결과 {r.shape}")
        # 기대: (750,) -> (749,)
        print(f"  평균 {r.mean() * 100:.4f}%  최대 {r.max() * 100:.2f}%  최소 {r.min() * 100:.2f}%")
        # 기대: 평균 -0.0839%  최대 20.01%  최소 -17.53%

    run(p1)

    section("PRACTICE 2. astype 의 버림")

    def p2():
        sample = np.array([52000.9, 51999.2, -3.7, 52000.5])
        won = to_won(sample)
        need(won)
        print(f"  원본            : {sample}")
        print(f"  astype 만       : {sample.astype('int64')}")
        # 기대: [52000 51999    -3 52000]
        print(f"  to_won          : {won}")
        # 기대: [52001 51999    -4 52000]   (52000.5 가 52000 인 것은 은행가 반올림이다)

    run(p2)

    section("PRACTICE 3. 뷰와 복사")

    def p3():
        head = one[:10].copy()
        before = head.copy()
        result = normalize_tail(head)
        need(result)
        print(f"  입력      : {head}")
        print(f"  결과      : {result}")
        # 기대: [1073  924  485    0 1078]
        print(f"  원본 보존 : {np.array_equal(head, before)}")
        # 기대: True

    run(p3)

    section("PRACTICE 4. 집계와 argmax / argmin")

    def p4():
        result = peak_and_trough(one, dates)
        need(result)
        hi, hi_day, lo, lo_day = result
        print(f"  최고가 : {hi:>9,}원  ({hi_day})")
        # 기대: 25,899원  (2023-10-16)
        print(f"  최저가 : {lo:>9,}원  ({lo_day})")
        # 기대:  8,885원  (2026-05-21)

    run(p4)

    section("PRACTICE 5. reshape 와 2차원 인덱싱")

    def p5():
        flat = load_flat()                      # (90000,) 한 줄로 늘어선 종가
        m = to_matrix(flat, 750)
        need(m)
        print(f"  to_matrix        : {flat.shape} -> {m.shape}")
        # 기대: (90000,) -> (120, 750)
        last = last_day_prices(m)
        need(last)
        print(f"  last_day_prices  : {last.shape}  앞 3개 {last[:3]}")
        # 기대: (120,)  앞 3개 [ 9963 21387  9151]

    run(p5)

    section("PRACTICE 6. axis 와 keepdims")

    def p6():
        by_stock, by_date = mean_by_stock(matrix), mean_by_date(matrix)
        need(by_stock, by_date)
        print(f"  mean_by_stock    : {by_stock.shape}")     # 기대: (120,)
        print(f"  mean_by_date     : {by_date.shape}")      # 기대: (750,)

        centered = center_by_stock(matrix)
        need(centered)
        print(f"  center_by_stock  : {centered.shape}")     # 기대: (120, 750)
        print(f"  종목별 평균이 0인가 : {np.round(centered.mean(axis=1)[:3], 6)}")
        # 기대: [-0. -0. -0.]   (실수 오차 때문에 -0. 로 찍히는 것은 정상이다)

    run(p6)

    section("PRACTICE 7. 브로드캐스팅")

    def p7():
        z = zscore_by_stock(matrix)
        need(z)
        print(f"  zscore  {z.shape}  평균 {np.round(z.mean(axis=1)[:3], 6)}"
              f"  표준편차 {np.round(z.std(axis=1)[:3], 6)}")
        # 기대: (120, 750)  평균 [-0.  0. -0.]  표준편차 [1. 1. 1.]

        idx = rebase_to_100(matrix)
        need(idx)
        print(f"  rebase  {idx.shape}  첫 열 {np.round(idx[:3, 0], 1)}"
              f"  마지막 열 {np.round(idx[:3, -1], 1)}")
        # 기대: (120, 750)  첫 열 [100. 100. 100.]  마지막 열 [41.5 77.1 45.4]

    run(p7)

    section("PRACTICE 8. 불리언 마스킹과 조건 결합")

    returns = np.diff(matrix, axis=1) / matrix[:, :-1]      # (120, 749) 일간 수익률
    volume = load_column("volume")[:, 1:]                   # (120, 749) 거래량

    def p8():
        days = surge_days_per_stock(returns)
        need(days)
        print(f"  종목별 급등일 수 : {days.shape}  앞 5개 {days[:5]}")
        # 기대: (120,)  앞 5개 [52 48 30 72 61]
        print(f"  가장 급등이 잦은 종목 : {names[days.argmax()]} ({days.max()}일)")
        # 기대: G0062 (90일)

        cnt = count_surge_on_heavy_volume(returns, volume)
        need(cnt)
        print(f"  급등 + 대량거래 동시 : {cnt:,}건")
        # 기대: 356건

    run(p8)

    section("PRACTICE 9. np.where 와 위치로 꺼내는 패턴")

    def p9():
        labels = label_returns(returns)
        need(labels)
        print(f"  수익률 : {np.round(returns[0][:6], 4)}")
        print(f"  분류   : {labels[0][:6]}")
        # 기대: ['보합' '보합' '보합' '보합' '급등' '보합']
        for tag in ("급등", "보합", "급락"):
            print(f"    {tag} {(labels == tag).sum():>7,}건")
        # 기대: 급등 6,050 / 보합 77,422 / 급락 6,408

        stable = pick_stable(matrix, names)
        need(stable)
        print(f"  변동계수 0.15 미만 종목 {len(stable)}개 : {stable[:5]} ...")
        # 기대: 15개 : ['G0006' 'G0007' 'G0032' 'G0044' 'G0051'] ...

    run(p9)

    section("PRACTICE 10. 결측과 이상치")

    def p10():
        dirty, nan_idx, outlier_idx = load_dirty()

        report = nan_report(dirty)
        need(report)
        cnt, ratio, where = report
        print(f"  결측 {cnt}개 / 비율 {ratio:.2%}")
        # 기대: 결측 12개 / 비율 1.60%
        print(f"    찾은 위치 : {where}")
        print(f"    실제 위치 : {nan_idx}")
        # 기대: 두 줄이 같아야 한다

        filled = fill_with_mean(dirty)
        need(filled)
        print(f"  평균 채움 후 남은 결측 : {np.isnan(filled).sum()}개")
        # 기대: 0개
        print(f"    원본 표준편차 {np.nanstd(dirty):,.0f} -> 채운 뒤 {filled.std():,.0f}")
        # 기대: 8,450 -> 8,382   (평균으로 채우면 표준편차가 줄어든다)

        found = find_outliers(dirty)
        need(found)
        print(f"  이상치 {len(found)}개")
        print(f"    찾은 위치 : {found}")
        print(f"    실제 위치 : {outlier_idx}")
        # 기대: [ 61 214 389 556 671] 로 두 줄이 같아야 한다
        print(f"    2σ 로 낮추면 : {len(find_outliers(dirty, 2.0))}개")
        # 기대: 5개  (이상치가 표준편차를 스스로 키워 기준선을 밀어 올린 결과다)

    run(p10)
