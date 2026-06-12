use tdb;

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

select emp_id, emp_name, dept_title, dept_code, dept_id
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
where dept_code = dept_id 
  and bonus is not null;

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
join department on (dept_code = dept_id);
-- inner join department on (dept_code = dept_id);

-- ----------------------------------------------------------
/*
	2. outer join
    두 테이블 간의 join시 일치하지 않는 행도 포함시켜서 조회할 때 사용.
    (예 : 부서배치를 아직 받지않은 신입사원도 목록에 포함해서 보고 싶을 때)
    
    반드시 left / right를 지정해서 어떤 테이블이 기준이 될지를 정해줘야함.
*/

-- inner join시 부서배치를 받지않은 dept_code가 null인 사원 2명이 누락됨.
select emp_name, dept_title, salary
from employee
join department on (dept_code = dept_id);

-- left [outer] join : 왼쪽(from절) 테이블의 모든 데이터를 무조건 가져온다.
select emp_name, dept_title, salary
from employee
left join department on (dept_code = dept_id);
-- 부서가없는 사원도 조회

-- right [outer] join : 오른쪽(join절) 테이블의 모든 데이터를 무조건 가져온다.
select emp_name, dept_title, salary
from employee
right join department on (dept_code = dept_id);
-- 소속된 사원이 없는 부서도 조회

-- full join : 양쪽 테이블의 모든 행을 살림
-- mysql에서는 full join이라는 문법을 지원하지 않는다.
-- 따라서 left join결과와 right join결과를 union연산자를 사용해서 합치는 방식을 사용함
select emp_name, dept_title, salary
from employee
left join department on (dept_code = dept_id)
union
select emp_name, dept_title, salary
from employee
right join department on (dept_code = dept_id);

/*
	집합연산자
    여러개의 쿼리를 하나의 결과로 합해서 보여주는 연산자
    UNION : 합집합 (두 쿼리결과를 합치되, 중복은 제거)
    UNION ALL : 합집합 (두 쿼리결과를 합치되, 중복도 모두 포함)
*/

-- --------------------------------------------------------------
/*
	3. 비등가 조인
    매칭시킬 컬럼에 대한 조건 작성시 '='를 사용하지 않는 조인.
    주로 수치적 범위를 기반으로 조인할 때 사용.
*/

select emp_name, salary, sal_level
from employee
join sal_grade on (salary between min_sal and max_sal);

/*
	4. self join
    같은 테이블을 다시 한 번 조인하는 경우.
    - 주로 사원과 관리자, 부품과 상위부품등 계층형데이터를 다룰 때 사용.
    - 동일한 테이블을 2번 부르기 때문에 반드시 별칭을 지정해서 구분해야한다.
    * 테이블 별칭은 대문자 1개
*/
-- 전체 사원의 사번, 사원명, 부서코드, 사수의 사번, 사원명, 부서코드 조회
select E.emp_id, E.emp_name, E.dept_code,
       M.emp_id, M.emp_name, M.dept_code
from employee E
left join employee M on(M.emp_id = E.manager_id);

-- ---------------------------------------------------------------
/*
	5. multiple join
    2개 이상의 테이블을 순차적으로 계속 조인해 나감.
    연결고리가 있는 테이블부터 차근차근 이어가야 됨.
*/

-- 전체 사원의 사번, 사원명, 부서명, 직급명 조회
select emp_id, emp_name, dept_title, job_name
from employee
left join department on (dept_code = dept_id)
left join job using (job_code);

-- 사원의 사번, 사원명, 부서명, 직급명, 지역명, 국가명, 급여등급
select emp_id, emp_name, dept_title, job_name, local_name, national_name, sal_level
from employee
join department on (dept_code = dept_id)
join job using (job_code)
join location on (location_id = local_code)
join national using(national_code)
join sal_grade on (salary between min_sal and max_sal);

-- ----------------------------------------------------------------
/*
	6. 자연조인(natural join)
    두 테이블 간에 동일한 이름을 가진 컬럼을 데이터베이스가 알아서 찾아서 조인
    ON이나 USING을 쓰지 않아도 됨. 실제로 거의 사용하지 않음.
    
    단점 : 의도치않게 우연히 이름이 같은 다른컬럼까지 모두 조인조건으로 묶어버릴 수 있다.
*/

-- employee와 job 테이블에는 둘다 job_code라는 컬럼이 있음. 자동연결가능
select *
from employee
natural join job;