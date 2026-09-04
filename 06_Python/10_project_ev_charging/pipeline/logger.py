"""
로깅 설정. 화면과 파일에 동시에 남긴다.
"""

import logging
import os
from datetime import datetime

from .config import LOG_DIR


def setup(name="ev_pipeline", level=logging.INFO):
    os.makedirs(LOG_DIR, exist_ok=True)
    path = os.path.join(LOG_DIR, f"{datetime.now():%Y%m%d}.log")

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.handlers.clear()

    fmt = logging.Formatter("%(asctime)s [%(levelname)-7s] %(message)s", "%H:%M:%S")

    console = logging.StreamHandler()
    console.setFormatter(fmt)
    logger.addHandler(console)

    file = logging.FileHandler(path, encoding="utf-8")
    file.setFormatter(fmt)
    logger.addHandler(file)

    return logger
