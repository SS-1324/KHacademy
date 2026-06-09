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

/*
	<where 절>
    조회하고자하는 테이블로부터 특정 조건에 만족하는 행만 필터링할 때
    
    [표현법]
    select 컬럼명, 컬럼명 ... from 테이블명 where 조건;
    
    비교연산자
    >, <, >=, <= 대소비교
    = 동등비교
    !=, <> : 동등하지않다.
    
    *기본적으로 mysql은 데이터의 대소문자를 구분하지 않는다.
    명확한 구분을 원한다면 조회시 where절에 binary키워드를 부여하거나
    테이블 생성시 명시적으로 구분해주면 된다.
*/
-- 부서코드가 D9인 사원들의 모든 컬럼 조회
select * 
from employee
where binary DEPT_CODE = 'd9';

-- 부서코드가 D1인 사원들의 사원명, 급여, 부서코드 조회
select emp_name, salary, dept_code
from employee
where dept_code = 'd1';

-- 부서코드가 D1이 아닌 사원들의 사원명, 급여, 부서코드 조회
select emp_name, salary, dept_code
from employee
where dept_code != 'd1';

-- 월급이 400만원 이상인 사원들의 사원명, 급여, 부서코드 조회
select emp_name, salary, dept_code
from employee
where SALARY >= 4000000;

/*
	다중 조건을 설정하기 위해서는 AND, OR / BETWEEN AND
    AND : 양쪽이 모두 참인 경우 결과가 참
    OR : 한쪽이라도 참이면 결과는 참
    BETWEEN 하안값 AND 상한값 : 몇이상 몇이하의 범위 조건
*/

-- 급여가 350만원이상 600만원 이하인 모든 사원의 사원명, 사번, 급여 조회
SELECT EMP_NAME, EMP_ID, SALARY
FROM EMPLOYEE
-- WHERE SALARY >= 3500000 AND SALARY <= 6000000;
WHERE SALARY BETWEEN 3500000 AND 6000000;

-- NOT : 부정연산으로 조건을 부정하는데 사용함
-- 급여가 350만원이상 600만원 이하가 아닌 모든 사원의 사원명, 사번, 급여 조회
SELECT EMP_NAME, EMP_ID, SALARY
FROM EMPLOYEE
WHERE NOT SALARY BETWEEN 3500000 AND 6000000;

-- 입사일이 1990년 1월 1일 이상 2001년 1월 1일 이하인 사원 전체 조회
-- mysql의 표준 날짜 포맷 형식은 'YYYY-MM-DD', 'YYYY-MM-DD hh:mm:ss'
select *
from employee
where hire_date between '1990-01-01 00:00:00' and '2001-01-01 00:00:00';

-- NULL값 판별시 '='비교로는 동작하지 않는다 -> IS NULL, IS NOT NULL 구문을 사용해야함.
SELECT * FROM tdb.EMPLOYEE
WHERE BONUS IS NOT NULL;

/*
	<LIKE>
    비교하고자하는 컬럼값이 패턴 지시자(와일드카드)에 매칭되는 경우 추출
    1. '%' : 0글자 이상의 모든 문자 매칭
    2. '_' : 딱 1글자만 임의의 문자 매칭
*/
-- 전씨 성을 가진 사람들의 이름과 급여
select emp_name, salary
from employee
where emp_name like '전%';

select emp_name, salary
from employee
where emp_name like '%하';

-- 키워드검색 : 하
select emp_name, salary
from employee
where emp_name like '%하%';

select emp_name, salary
from employee
where emp_name like '전__';

select emp_name, salary
from employee
where emp_name like '_하_';

-- 전화번호 3번째 자리가 1인 사원들의 사번, 사원명, 전화번호 조회
select emp_id, emp_name, phone
from employee
where phone like '__1%';

-- 이메일 id중 '_'앞의 글자가 정확하게 3글자인 사원 조회
-- _또는 %를 일반문자로 취급할 수 없다. -> escape '문자' 문자를 활용해야 한다.
-- escape '문자' : '문자'뒤에오는 _또는%는 일반 문자로 취급하겠다.
select *
from employee
where email like '___/_%' escape '/';

/*
	<IN>
    제시한 값 목록 중에 일치하는 조건이 하나라도 있다면 참
*/
-- 부서코드가 D6, D8, D5중 하나인 부서원의 이름, 부서코드, 급여 조회
select emp_name, dept_code, salary
from employee
-- where dept_code = 'd6' or dept_code = 'd8' or dept_code = 'd5';
where dept_code in ('d6', 'd8', 'd5');
