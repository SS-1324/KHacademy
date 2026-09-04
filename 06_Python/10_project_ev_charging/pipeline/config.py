"""설정 - 경로와 상수를 한 곳에 모은다."""

import os
import sys

# 이 파일은 pipeline/ 안에 있는데, 쓰려는 _db.py / _config.py 는 한 칸 위(10_project_ev_charging/)에 있다.
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

# noqa: E402 는 "import 가 파일 맨 위에 있지 않다" 는 린터 경고를 끄는 표시다.
from _db import connect, get_engine                       # noqa: E402
from _config import path as data_path, ENCODING           # noqa: E402

CHUNK_SIZE = 2_000

LOG_DIR = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "logs")

# 물리 상한 판정에 둘 측정오차 여유 (10%)
PHYSICAL_MARGIN = 1.1
