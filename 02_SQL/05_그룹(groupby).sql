/*
	GROUP BY
    그룹 기준을 제시할 수 있는 구문
    여러개의 개별 데이터(로우, 행)들을 하나의 그룹으로 묶어서 처리하는 목적
*/

-- 전체 사원의 급여 총합
select sum(salary)
from employee;

-- 각 부서별 급여 총합
select dept_code, sum(salary)
from employee
group by dept_code;

-- 각 부서별 소속 사원 수
select dept_code, count(*)
from employee
group by dept_code;

-- 그룹별 정렬
select dept_code, count(*), sum(salary) -- 3
from employee -- 1
group by dept_code -- 2
order by dept_code; -- 4

-- group by절에서도 함수식 사용 가능(그룹을 나누는 기준을 명확하게 제시)
-- 성별별 사원 수 조회
select if(substring(emp_no, 8, 1) in ('1', '3'), '남', '여'), count(*)
from employee
group by if(substring(emp_no, 8, 1) in ('1', '3'), '남', '여');


-- group by 절에 여러 컬럼 나열 가능
-- 여러 컬럼 나열시 컬럼의 조합자체가 그룹의 기준이 된다.
select dept_code, job_code, count(*)
from employee
group by dept_code, job_code
order by dept_code;


/*
	having
    그룹에 대한 조건을 제시할 때
*/
-- 각 부서별 평균 급여(부서코드, 평균급여)
select dept_code, avg(salary)
from employee
group by dept_code;

-- 부서별 평균급여가 300만원 이상인 부서만 조회
-- 부서별 평균급여가 아니라, 급여가 300만원 이상인 사람만 먼저 필터링 한 뒤에 그룹화를 시킨다.
select dept_code, avg(salary)
from employee
where salary >= 3000000
group by dept_code;

-- 먼저 그룹화를 시킨뒤에 havig절을 통해 그룹에 대한 조건처리를 진행
select dept_code, avg(salary)
from employee -- 1
group by dept_code -- 2
having avg(salary) >= 3000000; -- 3


-- 직급별 총 급여 합이 1000만원 이상이 직급만 조회
select job_code, sum(salary)
from employee
group by job_code
having sum(salary) >= 10000000;

-- 부서별로 보너스를 받는 사원이 0명인 부서의 부서코드 조회
select dept_code, count(bonus)
from employee
group by dept_code
having count(bonus) = 0;


