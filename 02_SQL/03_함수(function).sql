/*
	함수(Function)
    전달된 컬럼값을 읽어서 함수(기능)를 실행한 결과를 반환
    - 단일행함수 : N개의 값을 읽어서 N개의 결과값을 반환(행마다 실행 결과를 반환)
    - 그룹함수 : N개의 값을 읽어서 1개의 결과값을 반환(그룹별로 함수 실행 결과를 반환)
*/

-- ----------단일행 함수----------
/*
	문자처리함수
    CHAR_LENGTH(컬럼 | 문자열) : 해당 문자열의 글자수를 반환
    LENGTH(컬럼 | 문자열) : 해당 문자열의 바이트 수를 반환
    
    MYSQL(UTF-8 기준) 한글은 글자당 3바이트, 영어/숫자/특수문자는 1바이트
*/

SELECT CHAR_LENGTH('오라클'), LENGTH('오라클');
SELECT CHAR_LENGTH('ORACLE'), LENGTH('ORACLE');

SELECT EMP_NAME, CHAR_LENGTH(EMP_NAME), LENGTH(EMP_NAME)
FROM EMPLOYEE;

/*
	INSTR / LOCATE
    문자열로부터 특정 문자의 시작위치를 찾아서 반환
    INSTR(문자열, '찾고자하는 문자')
    LOCATE('찾고자하는 문자', 문자열, [시작위치])
*/
SELECT INSTR('AABAACAABBAA', 'B');
SELECT LOCATE('B', 'AABAACAABBAA');
SELECT LOCATE('B', 'AABAACAABBAA', 4);

SELECT EMAIL, LOCATE('@', EMAIL)
FROM EMPLOYEE;

/*
	SUBSTR | SUBSTRING
    문자열에서 특정 문자열을 주출해서 반환
    
    SUBSTRING(컬럼 | 문자열, 추출 시작위치, [추출글자수])
*/

SELECT SUBSTR('SHOWMETHEMONEY', 7);
SELECT substring('SHOWMETHEMONEY', 5, 2);
SELECT SUBSTR('SHOWMETHEMONEY', 1, 6);
SELECT substring('SHOWMETHEMONEY', -8, 3);

-- 사원들의 성별 판별
SELECT EMP_NAME, EMP_NO, substring(EMP_NO, 8, 1) AS 성별
FROM EMPLOYEE;

-- 사원들 중 여자 사원들만 이름과 주민등록번호를 조회
SELECT EMP_NAME, EMP_NO
FROM EMPLOYEE
WHERE substring(EMP_NO, 8, 1) IN ('2', '4');

-- 함수 중첩 사용 가능(이메일 아이디 부분만 추출)
SELECT EMP_NAME, EMAIL, substring(EMAIL, 1, INSTR(EMAIL, '@') - 1)
FROM EMPLOYEE;

/*
	LPAD/ RPAD
    문자열을 조회할 때 통일감있게 조회하고자 정렬하는 함수
    
    LPAD/RPAD(문자열, 최종 반환 길이, 빈공간을 대체할 문자)
*/
-- 이메일을 동일하게 길이 30으로 출력
SELECT EMP_NAME, LPAD(EMAIL, 30, '#')
FROM EMPLOYEE;

SELECT EMP_NAME, RPAD(EMAIL, 30, '#')
FROM EMPLOYEE;

-- 사원들의 사원명, 주민등록번호 블라인드 처리("123456-1XXXXXX")
SELECT EMP_NAME, RPAD(SUBSTRING(EMP_NO, 1, 8), 14, 'X')
FROM EMPLOYEE;

SELECT EMP_NAME, CONCAT(SUBSTRING(EMP_NO, 1, 8), 'XXXXXX')
FROM EMPLOYEE;


SELECT EMP_NAME, LPAD(EMAIL, 30, ''), length('')
FROM EMPLOYEE;

-- LPAD|RPAD -> 대체문자를 ''로 설정시 더한 길이가 목표길이가 되지못해 에러 발생
SELECT EMP_NAME, LPAD(EMAIL, 30, ''), length(concat(EMAIL,'',''))
FROM EMPLOYEE
where LPAD(EMAIL, 30, '') is null;

/*
	LTRIM / RTRIM / TRIM
    문자열에서 특정 문자를 제거한 나머지를 반환
    
    LTRIM / RTRIM -> 공백만 제거가능
*/
SELECT LTRIM('    K  H    ');
SELECT RTRIM('    K  H    ');

-- TRIM([LEADING | TRAILING | BOTH] 제거할문자열 FROM 원본문자열)
SELECT TRIM('    K  H    '); -- 기본적으로 양쪽의 공백제거
SELECT TRIM(BOTH '#' FROM '######K###H#####');
SELECT TRIM(LEADING '#' FROM '######K###H#####');
SELECT TRIM(TRAILING '#' FROM '######K###H#####');

/*
	LOWER / UPPER
    LOWER : 모든 문자열을 소문자로 변경
    UPPER : 모든 문자열을 대문자로 변경
*/

SELECT LOWER("Hello");
SELECT UPPER("Hello");

/*
	CONCAT / CONCAT_WS
    CONCAT : 2개이상의 문자열을 하나로 합해서 반환
    CONCAT_WS : 지정한 구분자를 포함해서 합해서 반환
*/
SELECT CONCAT('자바', 'SQL', 'WEB');
SELECT CONCAT_WS('-','자바', 'SQL', 'WEB');
SELECT CONCAT_WS('/', EMP_NAME, JOB_CODE, DEPT_CODE) FROM EMPLOYEE;

/*
	REPLACE
	특정 문자열에서 특정 부분을 치환
    
    REPLACE(문자열, 타겟문자, 치환할문자)
*/
SELECT EMAIL, REPLACE(EMAIL, 'kh.co.kr', 'gmail.com') as email
FROM EMPLOYEE;

-- -------------숫자처리함수---------------------------
-- ABS : 절대값
SELECT ABS(-10), ABS(-20.5);

-- MOD : 나머지 값
SELECT MOD(10, 4);
SELECT MOD(11.8, 3);

-- ROUND(숫자, 위치값) : 반올림(위치값이 양수면 수수점뒤, 음수면 소수점 앞 정수부)
SELECT ROUND(123.456); --
SELECT ROUND(123.456, 1); -- 소수점 1번째자리까지는 표시
SELECT ROUND(123.456, -1); -- 소수점 앞 정수부에서 반올림 하겠다
SELECT ROUND(163.456, -2);

-- CEIL : 올림 / FLOOR : 버림
-- 따로 위치값 설정없이 무조건 소수점기준
SELECT CEIL(123.456);
SELECT FLOOR(123.456);

-- TRUNCATE(숫자, 위치값)
-- 버림처리함수(자릿수 지정 필수)
SELECT TRUNCATE(123.456, 0);
SELECT TRUNCATE(123.456, 1);
SELECT TRUNCATE(123.456, -1);

-- --------------- 날짜 처리 함수 ---------------------
-- NOW() / sysdate() / curdate() : 현재 날짜및 시간 반환
select now(), sysdate(), curdate();
select now(), sysdate(); -- 년 월 일 시 분 초
select curdate(); -- 년 월 일

-- datediff(기준일, 뺄날짜) : 두 날짜 사이의 일 수차이
select emp_name, hire_date, datediff(now(), hire_date) as 근무일수
from employee;

-- TIMESTAMPDIFF(단위, 뺄날짜, 기준일) : 두 날짜 사이의 지정된 단위(년, 월..) 차이 반환
select emp_name, hire_date, timestampdiff(month,hire_date, now()) as 근무월수
from employee;

select emp_name, hire_date, timestampdiff(year,hire_date, now()) as 근무년수
from employee;

select emp_name, hire_date, timestampdiff(day,hire_date, now()) as 근무일수
from employee;

-- DATE_ADD / DATE_SUB : 특정 날짜에 값을 더하거나 빼는 함수
-- DATE_ADD(기준날짜, INTERVAL 값 단위)
SELECT DATE_ADD(NOW(), INTERVAL 6 MONTH); -- 6개월 더하기
SELECT DATE_SUB(NOW(), INTERVAL 6 MONTH); -- 6개월 더하기

-- 사원명, 수습기간 종료일
select emp_name, DATE_ADD(hire_date, INTERVAL 3 MONTH) AS 수습종료일
from employee;

-- LAST_DAY(날짜) : 해당월의 마지막 날짜 반환
SELECT LAST_DAY(NOW());

/*
	YEAR / MONTH / DAY
    특정 날짜의 년.월.일 값을 추출
*/

select emp_name, hire_date, 
		year(hire_date), month(hire_date), day(hire_date)
from employee;

-- ------------------형변환 함수------------------------------
-- FORMAT(숫자, 소수점_자리수) : 숫자에 3자리마다 콤마를 찍어 문자열로 반환
SELECT FORMAT(2000000, 0);

-- DATE_FORMAT(날짜데이터, 포멧형식)
-- 날짜타입을 원하는 형태의 문자열로 변환
-- %Y(4자리 연도), %Y(2자리 연도), %m(2자리월), %d(일), %H(시간24), %h(시간12)
-- %i(분), %s(초) 
SELECT DATE_FORMAT(NOW(), '%Y/%m/%d');
SELECT DATE_FORMAT(NOW(), '%Y년%m월%d일 %H:%i:%s');

-- STR_TO_DATE(문자열, 문자열포멧형식) : 문자열을 날짜 타입으로 변경
 SELECT STR_TO_DATE('20260101', '%Y%m%d');
 SELECT STR_TO_DATE('2026년01월01일 13시32분20초', '%Y년%m월%d일 %H시%i분%s초');


-- CAST(변환할값 AS 변경할데이터타입) : 표준형변환
-- UNSIGNED(양수), SIGNED(음수포함정수), CHAR(문자열), DATE(날짜), DATETIME(날짜+시간)..
SELECT CAST('1000' AS UNSIGNED);
SELECT CAST('2025-12-31' AS DATE);

-- --------------- NULL 처리 함수 ----------------------------------
-- IFNULL(컬럼, NULL대체값) : NULL일경우 대체값으로 치환
SELECT EMP_NAME, IFNULL(BONUS, 0)
FROM EMPLOYEE;

-- COALESCE() : 입력받은 인자들 중에서 처음으로 NULL이 아닌 값을 반환
SELECT COALESCE(NULL, NULL, '대체값', '두번째값');
SELECT EMP_NAME, COALESCE(BONUS, 0) FROM EMPLOYEE;

-- NULLIF(비교대상1, 비교대상2) : 두값이 일치 NULL, 불일치 비교대상1 반환
SELECT NULLIF('123', '123');
SELECT NULLIF('123', '456');

-- ---------- 조건분기함수 -----------------------
-- IF(조건, 참일때결과, 거짓일때결과)
SELECT EMP_NAME, BONUS, IF(BONUS IS NOT NULL, 'O', 'X') AS ISBONUS
FROM EMPLOYEE;

SELECT EMP_NAME, EMP_NO, 
       IF(substring(EMP_NO, 8, 1) IN ('1','3'), '남', '여')
FROM EMPLOYEE;

/*
	CASE문 : 복잡한 다중 조건처리시 사용
    
    CASE
	  WHEN 조건1 THEN 결과1
      WHEN 조건2 THEN 결과2
      ...
      ELSE 기본결과
	END
*/

-- 급여 수준에 따른 직급 분류
SELECT EMP_NAME, SALARY,
	CASE
     WHEN SALARY >= 5000000 THEN '시니어'
     WHEN SALARY >= 3500000 THEN '미들'
     ELSE '주니어'
	END AS '등급'
FROM EMPLOYEE;
    
-- 직급별 차등 급여인상
SELECT EMP_NAME, JOB_CODE, SALARY,
	CASE JOB_CODE
      WHEN 'J7' THEN SALARY * 1.1
      WHEN 'J6' THEN SALARY * 1.15
      ELSE SALARY * 1.2
	END AS '인상후'
FROM EMPLOYEE;

-- ------------------그룹함수----------------
-- 특정 그룹에 함수를 적용해서 하나의 결과를 반환

-- SUM(컬럼) : 총합계
SELECT SUM(SALARY) FROM EMPLOYEE;

-- 남자사원들의 총 급여
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE SUBSTRING(EMP_NO, 8, 1) IN ('1','3');

-- AVG(컬럼) : 평균
SELECT AVG(SALARY)
FROM EMPLOYEE;

-- MIN(컬럼) : 최소값
SELECT MIN(SALARY), MIN(EMP_NAME), MIN(HIRE_DATE)
FROM EMPLOYEE;

-- MAX(컬럼) : 최대값
SELECT MAX(SALARY), MAX(EMP_NAME), MAX(HIRE_DATE)
FROM EMPLOYEE;

-- COUNT(* | 컬럼 | DISTINCT 컬럼) : 행의 개수
-- COUNT(*) :  모든 행의 개수(NULL포함)
-- COUNT(컬럼) : 해당컬럼이 NULL이 아닌 데이터 개수
-- COUNT(DISTINCT 컬럼) : 고유한 컬럼값의 개수

-- 전체사원 수
SELECT COUNT(*)
FROM EMPLOYEE;

-- 보너스를 받는사람
SELECT COUNT(BONUS)
FROM EMPLOYEE;

-- 보너스를 안받는 사람
SELECT COUNT(*)
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- 부서에 종류 개수
SELECT COUNT(DISTINCT DEPT_CODE)
FROM EMPLOYEE;

-- SELECT문의 기본 실행 순서
/*
	SELECT 컬럼, 컬럼...		-- 3
      FROM 테이블명			-- 1
	 WHERE 조건식				-- 2
     ...
*/

