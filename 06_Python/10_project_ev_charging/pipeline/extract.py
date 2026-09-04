"""
Extract - 원본을 가져와 그대로 보관한다. 여기서 정제하지 않는다.
"""

import pandas as pd

from .config import data_path, ENCODING


def from_csv(logger):
    """
    세 CSV 를 '적힌 그대로' 읽는다.
    dtype=str, keep_default_na=False 로 읽어야 원본의 오염 상태(빈칸, N/A, 대소문자...)를
    있는 그대로 볼 수 있다. read_csv 가 알아서 결측 처리를 해버리면 진단이 왜곡된다.
    """
    regions = pd.read_csv(data_path("regions.csv"), encoding=ENCODING)
    stations = pd.read_csv(data_path("raw-stations.csv"), encoding=ENCODING,
                            dtype=str, keep_default_na=False)
    logs = pd.read_csv(data_path("raw-charging-logs.csv"), encoding=ENCODING,
                        dtype=str, keep_default_na=False)

    logger.info(f"  regions            {len(regions):>8,}행")
    logger.info(f"  raw-stations       {len(stations):>8,}행")
    logger.info(f"  raw-charging-logs  {len(logs):>8,}행")

    return {"regions": regions, "stations": stations, "logs": logs}
