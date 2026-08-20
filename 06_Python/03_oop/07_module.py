"""
    모듈과 패키지
"""

#모듈 import

#모듈 전체를 가져온다
import utils

print(f"1200원 : {utils.clean_price(' 1,200원  ')}")

#필요한 것만 가져온다.
from utils import to_code, BASE_URL

print(f" to_code(5910) : {to_code(5910)}")
print(f" BASE_URL : {BASE_URL}")

#별칭사용
from utils import clean_price as cp
print(f"1200원 : {cp(' 1,200원  ')}")
