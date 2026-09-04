-- ============================================================
-- EV-Charge 실습용 DB / 계정 생성 스크립트
-- root 권한으로 딱 한 번만 실행하면 됩니다.
--
-- 실행 방법 (터미널에서, 본인이 직접):
--   "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p < setup_db.sql
--   (비밀번호 입력 프롬프트가 뜨면 root 비밀번호 입력)
--
-- 이미 같은 이름의 DB/계정이 있다면 DROP 문의 주석을 풀고 먼저 정리하세요.
-- ============================================================

-- DROP DATABASE IF EXISTS ev_charge;
-- DROP USER IF EXISTS 'ev_app'@'%';

CREATE DATABASE IF NOT EXISTS ev_charge
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 이 프로젝트 전용 앱 계정. 로컬 개발용이라 호스트를 '%'로 열어 둔다.
CREATE USER IF NOT EXISTS 'ev_app'@'%' IDENTIFIED BY 'fKE2UJVhgAfX2FOzPKjUgEMu';

GRANT ALL PRIVILEGES ON ev_charge.* TO 'ev_app'@'%';

FLUSH PRIVILEGES;

-- 확인용
SELECT User, Host FROM mysql.user WHERE User = 'ev_app';
SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME
FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = 'ev_charge';
