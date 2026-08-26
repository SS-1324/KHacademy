""" 
    결측과 이상치
    - np.nan의 성질
    - arr == np.nan로 찾을 수 있는가?
    - nan를 무시하는 함
"""
import numpy as np

from _data import load_dirty

np.set_printoptions(suppress=True)

# 첫 종목의 750일 종가(결측치 12개와 이상치 5개)
arr, nan_idx, outlier_idx = load_dirty()

#np.nan -> 자신과 비교해도 같지않음.
print(f" np.nan == np.nan -> {np.nan == np.nan}")
print(f" np.nan != np.nan -> {np.nan != np.nan}")
print(f" np.nan > 1 -> {np.nan > 1}")
print(f" np.nan < 1 -> {np.nan < 1}")
print(f" np.nan + 1 -> {np.nan + 1}")

#nan뜻 자체가 Not a Number니까
#모르는 값이랑 모르는 값을 비교하거나 모르는값에 1을 더하거나 했을 때 결과를 알 수 없다.

# == 로는 절대 결측치를 찾을 수 없다.
wrong = (arr == np.nan)
print(f"arr == np.nan : {wrong.sum()}")

# nan인 자리만 True인 마스크를 반환
right = np.isnan(arr)
print(f"총 개수 : {len(arr)}, 결측 : {right.sum()}")

# np.where(마스크)[0]은 True인 자리의 번호를 돌려준다.
print(f"결측이 있는 위치 : {np.where(right)[0]}")


#nan 하나라도 들어있으면 전체의 연산이 nan이 된다.
for name, f1, f2 in [
    ("mean", np.mean, np.nanmean),
    ("sum", np.sum, np.nansum),
    ("std", np.std, np.nanstd),
    ("max", np.max, np.nanmax),
    ("min", np.min, np.nanmin),
]:
    v1 = f1(arr)
    v2 = f2(arr)
    v1s = "nan" if np.isnan(v1) else f"{v1}"
    print(f"{name} {v1s} {v2}")

# 왼쪽 열이 전부 nan, 모든 연산에 nan가 섞여있다면 결과는 nan
# np.isnan(arr).sum()부터 확인해서 nan가 있는지, 몇개인지 보면된다.


# 결측치 해결방법
# 1. 결측치 무시하고 연산 np.nanmean 처럼 전용함수 사용.
# 2. 제거 : arr[~np.isnan(arr)]
# 3. 채우기 : 특정 값으로 채움(평균, 0, 바로앞의 값)...