-- ============================================================
-- charging_log 테이블 스키마
--
-- 설계 기준
--   - 금액(fee)·충전량(energy_kwh)에 FLOAT 을 쓰지 않는다 -> DECIMAL / BIGINT
--   - 날짜·시각을 문자열로 저장하지 않는다 -> DATETIME
--   - ID(log_id, station_id, user_id)는 계산하지 않는 값이므로 VARCHAR
--   - log_id 에 UNIQUE 제약 -> 재실행 안전성(UPSERT)의 출발점
-- ============================================================

CREATE TABLE IF NOT EXISTS charging_log (
    id             BIGINT        AUTO_INCREMENT PRIMARY KEY,
    log_id         VARCHAR(20)   NOT NULL,
    station_id     VARCHAR(20)   NOT NULL,
    user_id        VARCHAR(20)   NOT NULL,
    start_time     DATETIME      NOT NULL,
    end_time       DATETIME      NOT NULL,
    energy_kwh     DECIMAL(10,2) NOT NULL,   -- 충전량(kWh). FLOAT 금지.
    fee            BIGINT        NOT NULL,   -- 요금(원). 소수점 없는 화폐 단위.
    payment_method VARCHAR(20)   NOT NULL,
    updated_at     DATETIME      DEFAULT CURRENT_TIMESTAMP
                                  ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_log_id (log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
