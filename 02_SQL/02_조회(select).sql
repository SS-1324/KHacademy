/*
	테이블
    - 데이터베이스에서 데이터를 저장하는 기본 개념
    - 행(Row)과 열(Column)로 구성된 데이터 집합
      row : 각각의 데이터
      column : 테이블에 저장되는 속성
      
	=> 테이블은 여러 컬럼으로 구성되고, 각 컬럼은 테이블이 표현하는 데이터의 세부적인 속성을 나타냄.
    
    <select>
    select 컬럼명1, 컬럼명2...
    from 테이블명
    [where 조건]
    [order by 정렬기준]
*/

-- job테이블의 모든 컬럼 조회
select * from job;

-- job테이블의 직급 이름만 조회
select job_name from job;

-- department 테이블의 모든 정보 조회
select * from department;

-- employee 테이블의 직원명, 이메일, 전화번호, 고용일 조회
select emp_name, email, phone, hire_date 
from employee;

-- ifnull(컬럼명, 대체값) : 컬럼의 값이 null이면 대체값으로 치환
-- employee 테이블의 이름, 연봉, 총수령액(보너스 포함)
-- 보너스 : 연봉 * 보너스
select emp_name, 
	   (salary * 12),
	   (salary + (salary * ifnull(bonus, 0))) * 12
from employee;

/*
	<컬럼별칭>
    컬럼에 별칭을 부여하면 결과 뷰를 깔끔하게 표현할 수 있음.
    [표현식]
    컬럼명 별칭 / 컬럼명 as 별칭 / 컬럼명 "별칭" / 컬럼명 as "별칭"
*/

select emp_name, 
	   (salary * 12) as 연봉,
	   (salary + (salary * ifnull(bonus, 0))) * 12 "총수령액"
from employee;

-- 사원명, 입사일, 근무일수를 근로자테이블에서 조회
-- now() : mysql의 실행시점의 시간을 표현해주는 함수
-- datediff(종료일, 시작일) : 날짜간의 일수 차이
select emp_name, hire_date, datediff(now(), hire_date)
from employee;

-- 현재날짜 조회
select now(); -- 년-월-일 시:분:초
select curdate(); -- 년-월-일

/*
	<리터럴>
    직접 값을 나타내는 단위, 임의로 지정한 고정 문자열이나 숫자값
*/
select emp_id, emp_name, salary, '원' as 단위
from employee;

/*
	<연결함수 : concat>
    여러 컬럼값과 리터럴 문자열을 하나로 묶어주는 함수
*/
select concat(emp_name, '님 급여 : ', salary, '원 ') as "급여안내"
from employee;

/*
	<distinct>
    중복제거 : 컬럼에 표시된 데이터들을 중복없이 한번씩만 조회하고자할 때 사용
*/
select * from employee;

-- employee테이블의 사용중인 job_code의 종류
select distinct job_code from employee;

-- employee테이블의 사용중인 dept_code의 종류
select distinct dept_code from employee;

-- distinct 키워드는 select절 맨 앞에 딱 한번만 선언할 수 있음. 
-- select distinct dept_code, distinct job_code from employee;

-- distinct는 항상 row데이터 기준으로 중복을 제거한다.
-- dept_code와 job_code가 완전히 일치하는 로우만 중복으로 인정하고 제거한다.
select distinct dept_code, job_code 
from employee;
