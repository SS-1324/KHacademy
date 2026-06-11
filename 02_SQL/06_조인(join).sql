/*
	join : 조인
	두 개 이상의 테이블에서 데이터를 조회하고자 할 때 사용되는 구문.
    조회 결과는 하나의 결과(view - result set)로 반환된다.
    
    관계형 데이터베이스는 데이터 중복을 막기위해 최소한의 데이터를 
    각각의 테이블에 나누어 저장한다.
    따라서 무작정 다 조회해 오는 것이 아니라, 각 테이블간의 연결고리(PK/FK)를 통해서 데이터를 매칭시켜 조회.
*/

-- 사원의 사번, 이름, 부서명을 조회
-- 사번, 이름 => employee, 부서명 => department
select emp_id, emp_name, dept_code from employee;
select dept_id, dept_title from department;

select emp_id, emp_name, dept_title
from employee
join department on (dept_code = dept_id);

/*
	1. inner join
    연결시키는 컬럼의 값이 일치하는 행들만 조회
    (기준 컬럼값이 null이거나 양쪽테이블에 일치하는 값이 없으면 결과에서 제외)
*/
select *
from employee;

select count(*)
from employee
join department on (dept_code = dept_id);

-- 예전 오라클 방식 : from절에 합칠 테이블 나열 + where 조건 제시
select emp_id, emp_name, dept_title
from employee, department
where dept_code = dept_id;

-- ansi구문 : join절에 합칠 테이블을 기술 + on/using으로 조건제시
select emp_id, emp_name, dept_title
from employee
join department on dept_code = dept_id;

-- 1. 연결할 두 컬럼명이 다른 경우 : join ~ on 사용
select emp_id, emp_name, dept_title
from employee
join department on (dept_code = dept_id);

-- 2. 연결할 두 컬럼명이 같은 경우 : join ~ using 사용
select emp_id, emp_name, job_code, job_name
from employee
join job using (job_code)
where job_name = '대리';

-- ----------------실습------------------------
-- 1. 부서가 인사관리부인 사원들의 사번, 이름, 보너스 조회
select emp_id, emp_name, bonus
from employee
join department on(dept_code = dept_id)
where dept_title = '인사관리부';

-- 2. 부서와 지역테이블을 연결해서 전체 부서의 부서코드, 부서명, 지역코드, 지역명 조회
select dept_id, dept_title, location_id, local_name
from department
join location on (location_id = local_code);

-- 3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회
select emp_id, emp_name, bonus, dept_title
from employee
join department on (dept_code = dept_id)
where bonus is not null;


