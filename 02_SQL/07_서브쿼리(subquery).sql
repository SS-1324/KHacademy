/*
	SUBQUERY
    - 하나의 쿼리문 안에 포함된 또다른 select문
    - 메인 쿼리문을 위해 보조역할을 하는 쿼리
*/

-- 노옹철 사원과 같은 부서에 속한 사원을 조회
select emp_name, dept_code
from employee
where dept_code = '노옹철사원의부서코드';

-- 노옹철사원의 부서코드
select dept_code
from employee
where emp_name = '노옹철';

-- 메인쿼리 : 노옹철사원과 같은 부서에 속한 사원 조회
-- 서브쿼리 : 노옹철사원의 부서코드 조회
select emp_name, dept_code
from employee
where dept_code = (select dept_code
					from employee
					where emp_name = '노옹철');
                    
-- 전 직원의 평균급여보다 더 많은 급여를 받는 사원들의 사번, 이름, 직급코드, 급여조회
select emp_id, emp_name, job_code, salary
from employee
where salary > (select avg(salary) from employee);


select emp_id
from employee
/*
	서브쿼리 분류
    서브쿼리를 실행한 결과값이 몇행, 몇열로 나오느냐에 따라서 분류.
    
    1. 단일행 서브쿼리 : 서브쿼리의 조회 결과값이 오로지 1개(1행1열)
    2. 다중행 서브쿼리 : 서브쿼리의 조회 결과값이 여러 행일 때(N행 1열)
    3. 다중열 서브쿼리 : 서브쿼리의 조회 결과값이 여러 열일 때(1행 N열)
    4. 다중행 다중열 서브쿼리 : 서브쿼리의 조회 결과값이 여러 행,여러 열일 때(N행N열)
*/

/*
	단일행 서브쿼리
    - 쿼리결과값이 딱 1개
    - 일반적으로 비교연산자와 함께 사용(=, !=, >, < ....)
*/

-- 전 직원의 평균급여보다 급여를 더 적게받는 사원들의 사원명, 급여 조회
select emp_name, salary
from employee
where salary < (select avg(salary) from employee);

-- 사원들 중 최저월급을 받는 사원의 사번, 이름, 급여 조회
select emp_id, emp_name, salary
from employee
where salary = (select min(salary) from employee);

-- 부서별 급여합이 가장 큰 부서의 부서코드, 급여합
select dept_code, sum(salary)
from employee
group by dept_code
having sum(salary) = (select sum(salary)
						from employee
						group by dept_code
						order by sum(salary) desc
						limit 1);

-- '전지연'사원과 같은 부서 사람들의 사번, 사원명, 전화번호, 부서명 조회
-- 단, 전지연 본인은 제외
select emp_id, emp_name, phone, dept_title
from employee
join department on (dept_code = dept_id)
where dept_code = (select dept_code
					from employee
                    where emp_name = '전지연')
  and emp_name != '전지연';
  
  -- -------------------------------------------------
  
  /*
	다중행 서브쿼리
    서브쿼리의 결과값이 여러 행일경우(컬럼은 1)
    
    다중행 연산자
    in (여러값) : 여러개의 값 중 한개라도 일치하는가?
    > any (여러값) : 여러개의 값중 최소값보다 큰가?(하나라도 만족하면 참)
    < any (여러값) : 여러개의 값중 최댜값보다 작은가?(하나라도 만족하면 참)
    > all (여러값) : 여러개의 값 모두보다 큰가?(모두 다 만족)
    < all (여러값) : 여러개의 값 모두보다 작은가?(모두 다 만족)
  */
  
  -- 유재식 또는 윤은해 사원과 같은 직급인 사원들의 사번, 사원명, 직급코드, 급여 조회
select emp_id, emp_name, job_code, salary
  from employee
 where job_code in (select job_code
					from employee
					where emp_name in ('유재식', '윤은해'));
 
 -- 유재식 또는 윤은해 사원의 직급코드
select job_code
from employee
where emp_name in ('유재식', '윤은해');

-- 대리직급임에도 과장 직급의 최소급여보다 더 많이받는 사원들의 사번, 이름, 직급, 급여 조회
select salary
from employee
join job using (job_code)
where job_name = '과장';

select emp_id, emp_name, job_name, salary
from employee
join job using(job_code)
where job_name = '대리'
  and salary > any (select salary
					from employee
					join job using (job_code)
					where job_name = '과장');

/*
	다중열 서브쿼리
	- 결과값은 한 행이지만, 나열된 컬럼 수가 여러개일 경우
    - 튜플비교방식을 사용함 (컬럼1, 컬럼2) = (값1, 값2)
*/

-- 하이유 사원과 같은 부서코드, 같은 직급코드에 해당하는 사원들 조회
-- (사원명, 부서코드, 직급코드, 입사일)
select emp_name, dept_code, job_code, hire_date
from employee
where (dept_code, job_code) = (select dept_code, job_code
								 from employee
								where emp_name = '하이유');

-- 박나라 사원과 같은 직급코드, 같은 사수를 가지고있는 사원들의
-- 사번, 사원명, 직급코드, 사수사번 조회 (박나라 제외)
select emp_id, emp_name, job_code, manager_id
from employee
where (job_code, manager_id) = (select job_code, manager_id
								  from employee
								 where emp_name = '박나라')
  and emp_name != '박나라';
  
-- ------------------------------
/*
	다중행 다중열 서브쿼리
    - 서브쿼리의 조회 결과값이 여러행, 여러 컬럼일 경우
*/

-- 각 직급별 최소급여를 받는 사원 조회(사번, 사원명, 직급코드, 급여)
select emp_id, emp_name, job_code, salary
from employee
where (job_code, salary) in (select job_code, min(salary)
							   from employee
							 group by job_code);
						
-- 각 부서의 최고급여를 받는 사원들의 사번, 사원명, 부서코드, 급여
select emp_id, emp_name, dept_code, salary
from employee
where (dept_code, salary) in (select dept_code, max(salary)
								from employee
							   group by dept_code);

-- 각 부서별 급여 합계가 전체 급여 총합의 20%보다 많은 부서의 부서명, 부서별 급여합계 조회
select dept_title, sum(salary)
from employee
join department on (dept_code = dept_id)
group by dept_title
having sum(salary) > (select sum(salary) * 0.2
						from employee);
					

                    