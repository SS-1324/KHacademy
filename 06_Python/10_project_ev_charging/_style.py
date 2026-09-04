"""
한글 폰트 설정과 그림 저장 경로.
"""

import platform
from pathlib import Path

import matplotlib.pyplot as plt
from matplotlib import font_manager

_CANDIDATES = {
    "Windows": ["Malgun Gothic"],
    "Darwin": ["AppleGothic"],
}
_FALLBACK = ["NanumGothic", "Noto Sans CJK KR", "Noto Sans CJK JP", "IPAGothic"]


def find_korean_font():
    installed = {f.name for f in font_manager.fontManager.ttflist}
    for name in _CANDIDATES.get(platform.system(), []) + _FALLBACK:
        if name in installed:
            return name
    return None


OUTPUT_DIR = Path(__file__).with_name("output")


def out(name):
    OUTPUT_DIR.mkdir(exist_ok=True)
    return str(OUTPUT_DIR / name)


def setup(theme=True, verbose=True):
    if theme:
        import seaborn as sns
        sns.set_theme(style="whitegrid")

    font = find_korean_font()
    if font:
        plt.rcParams["font.family"] = font
    elif verbose:
        print("[경고] 한글 폰트를 찾지 못했습니다. 제목이 네모로 표시됩니다.")

    plt.rcParams["axes.unicode_minus"] = False
    plt.rcParams["figure.dpi"] = 100
    plt.rcParams["savefig.bbox"] = "tight"

    if verbose:
        print(f"[폰트] {font or '(없음)'} / unicode_minus=False")

    return font
