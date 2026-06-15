use tdb;

-- 문제 1.
-- '노옹철' 사원과 같은 부서(DEPT_CODE)에 속한 사원들의 사원명, 부서코드, 입사일을 조회하시오.
select emp_name, dept_code, hire_date
from employee
where dept_code = (select dept_code
					from employee
                    where emp_name = '노옹철')
  and emp_name != '노옹철';

-- 문제 2. 
-- 회사 전체 사원의 평균 급여보다 같거나 많은 급여를 받는 사원들의 사번, 사원명, 급여를 조회하시오.
select emp_id, emp_name, salary
  from employee
 where salary >= (select avg(salary) from employee);

select avg(salary) from employee;

-- 문제 3. 
-- 전 직원 중 가장 적은 급여를 받는 사원의 사원명, 직급코드, 급여를 조회하시오. 
select emp_name, job_code, salary
from employee
where salary = (select min(salary) from employee);

select min(salary) from employee;

-- 문제 4. 
-- 부서명(DEPT_TITLE)이 '총무부' 또는 '마케팅부'인 부서에 속한 사원들의 사원명, 부서코드, 급여를 조회하시오.
select emp_name, dept_code, salary
  from employee
 where dept_code in (select dept_id
					   from department
					  where dept_title in ('총무부', '마케팅부'));

select emp_name, dept_code, salary
  from employee
  join department on (dept_code = dept_id)
 where dept_title in ('총무부', '마케팅부');

select dept_id
from department
where dept_title in ('총무부', '마케팅부');

-- 문제 5. 
-- 직급이 '과장'인 사원들 중 어느 한 명의 급여보다라도 더 많이 받는 '대리' 사원의 사원명, 직급명, 급여를 조회하시오.
select emp_name, job_name, salary
from employee
join job using(job_code)
where job_name = '대리'
  and salary > any (select salary
					from employee
					join job using(job_code)
					where job_name = '과장');
                    
select salary
	from employee
	join job using(job_code)
	where job_name = '과장';
/*
and salary > (select min(salary)
			  from employee
			  join job using(job_code)
			 where job_name = '과장');
*/


-- 문제 6. 
-- 다른 사원의 관리자(MANAGER_ID)로 지정되지 않은 일반 평사원들의 사원명, 부서코드, 직급코드를 조회하시오.
select emp_name, dept_code, job_code
from employee
where emp_id not in (select manager_id
					   from employee
				      where manager_id is not null);
                  
select manager_id
from employee
where manager_id is not null;

-- 다른사원의 관리자로 지정되지 않은 사원
-- 누군가가 이사원을 관리자로 지정했는가?
-- 관리자 사번들을 조회후 -> 이 사원번호중 내 사원번호가 있는가?

-- 문제 7.
-- '유재식' 사원과 같은 부서코드, 같은 직급코드를 가지는 사원들의 사원명, 부서코드, 직급코드를 조회하시오.
-- (단, 유재식 사원 본인은 결과에서 제외할 것)
select emp_name, dept_code, job_code
  from employee
 where (dept_code, job_code) = (select dept_code, job_code
								  from employee
								 where emp_name = '유재식')
   and emp_name != '유재식';
   
select dept_code, job_code
from employee
where emp_name = '유재식';

-- 문제 8. 
-- 각 직급(JOB_CODE)별로 가장 높은 급여를 받는 사원들의 사원명, 직급코드, 급여를 조회하시오.
select emp_name, job_code, salary
 from employee 
 where (job_code, salary) in (select job_code, max(salary)
							    from employee
							   group by job_code);

select job_code, max(salary)
from employee
group by job_code;


-- 문제 9. 
-- 부서별 급여 합계가 '회사 전체 급여 총합의 20%'보다 큰 부서의 부서코드와 급여 합계를 조회하시오.
select dept_code, sum(salary)
from employee
group by dept_code
having sum(salary) > (select sum(salary) * 0.2 from employee);


-- 문제 10.
-- 부서별 평균 급여가 가장 높은 부서에 속한 사원들의 사원명, 부서코드, 급여를 조회하시오.
select emp_name, dept_code, salary
from employee
where dept_code = (select dept_code
					 from employee
                     group by dept_code
                     order by avg(salary) desc
                     limit 1);
                     
                     select dept_code
					 from employee
                     group by dept_code
                     order by avg(salary) desc
                     limit 1;