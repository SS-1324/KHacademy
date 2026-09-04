"""
전체 흐름. 이 파일만 읽어도 무슨 일이 일어나는지 알 수 있어야 한다.

    Extract ->        Transform         -> Validate ->     Load
    (원본 3장 로드)   (정제·결합·계산)              (DB 적재, UPSERT)
"""

import sys
import time

from . import extract, transform, load
from .logger import setup


def run():
    logger = setup()
    t0 = time.perf_counter()

    logger.info("=" * 58)
    logger.info(" GX-Charge 파이프라인 시작")
    logger.info("=" * 58)

    # ── Extract ────────────────────────────────────────────
    logger.info("[Extract]")
    raw = extract.from_csv(logger)

    # ── Transform ──────────────────────────────────────────
    logger.info("[Transform]")
    t = time.perf_counter()
    df = transform.clean_all(raw, logger)
    logger.info(f"  완료  {len(df):,}행  ({time.perf_counter() - t:.1f}초)")

    # ── Validate ───────────────────────────────────────────
    logger.info("[Validate]")
    try:
        transform.validate(df, logger)
    except ValueError as e:
        logger.error(f"  검증 실패로 적재를 중단합니다: {e}")
        return False

    # ── Load ───────────────────────────────────────────────
    logger.info("[Load]")
    try:
        ins, upd, elapsed = load.to_db(df, logger)
    except Exception as e:
        logger.error(f"  적재 실패: {type(e).__name__}: {e}")
        return False

    logger.info(f"  입력 {len(df):>8,}행")
    logger.info(f"  신규 {ins:>8,}행")
    logger.info(f"  갱신 {upd:>8,}행")
    logger.info(f"  소요 {elapsed:>8.2f}초")

    logger.info("[Verify]")
    ok = load.verify(df, logger)

    logger.info("=" * 58)
    logger.info(f" {'완료' if ok else '검증 실패'}  총 {time.perf_counter() - t0:.1f}초")
    logger.info("=" * 58)
    return ok


if __name__ == "__main__":
    sys.exit(0 if run() else 1)
