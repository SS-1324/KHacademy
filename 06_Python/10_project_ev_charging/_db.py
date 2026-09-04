"""
DB 접속 공통 모듈. 접속 정보는 .env 에서만 읽는다.
"""

import os

import pymysql
from dotenv import load_dotenv
from sqlalchemy import create_engine

load_dotenv()

HOST = os.getenv("DB_HOST", "127.0.0.1")
PORT = int(os.getenv("DB_PORT", 3306))
USER = os.getenv("DB_USER")
PASSWORD = os.getenv("DB_PASSWORD")
NAME = os.getenv("DB_NAME")


def connect(autocommit=False):
    return pymysql.connect(
        host=HOST, port=PORT, user=USER, password=PASSWORD, database=NAME,
        charset="utf8mb4",
        autocommit=autocommit,
        cursorclass=pymysql.cursors.DictCursor,
    )


def get_engine():
    url = f"mysql+pymysql://{USER}:{PASSWORD}@{HOST}:{PORT}/{NAME}?charset=utf8mb4"
    return create_engine(url, pool_pre_ping=True)
