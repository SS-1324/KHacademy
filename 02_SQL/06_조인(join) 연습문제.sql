-- 1. 70년대 생(1970~1979) 중 여자이면서 전씨인 사원의 이름과 주민번호, 부서명, 직급 조회  
select emp_name, emp_no, dept_title, job_name
from employee
join department on (dept_code = dept_id)
join job using (job_code)
where substring(emp_no, 1, 2) between '70' and '79'
  and substring(emp_no, 8, 1) in ('2', '4')
  and emp_name like '전%';

-- 2. 이름에 ‘형’이 들어가는 사원의 사원 코드, 사원 명, 직급 조회 
select emp_id, emp_name, job_name
  from employee
  join job using (job_code)
 where emp_name like '%형%';
 
-- 3. 부서코드가 D5이거나 D6인 사원의 사원명, 직급명, 부서 코드, 부서 명 조회
select emp_name, job_name, dept_code, dept_title
from employee
join department on (dept_code = dept_id)
join job using (job_code)
where dept_code in ('d5', 'd6');

-- 4. 보너스를 받는 사원의 사원명, 부서명, 지역명 조회  
select emp_name, dept_title, local_name
from employee
join department on (dept_code = dept_id)
join location on (location_id = local_code)
where bonus is not null;

-- 5. 사원명, 직급명, 부서명, 지역명 조회 (부서 배치를 받지 않은 사원도 포함할 것)
select emp_name, dept_title, job_code, location_id
from employee
left join job using (job_code)
left join department on (dept_code = dept_id)
left join location on (location_id = local_code);

-- 6. 한국이나 일본에서 근무 중인 사원의 사원명, 부서명, 지역명, 국가명 조회  
select emp_name, dept_title, local_name, national_name
from employee
left join department on (dept_code = dept_id)
left join location on (location_id = local_code)
left join national using (national_code)
where national_name in ('한국', '일본');

-- 7. 한 명의 사원과 같은 부서에서 일하는 사원의 이름 조회
--    (예: 사원 A가 속한 부서에서 다른 사원 B의 이름을 조회. 단, 본인 이름은 제외) 
select A.emp_name as "사원명", B.emp_name as "동료", A.dept_code
from employee A
join employee B on (A.dept_code = B.dept_code)
where A.emp_id != B.emp_id
order by A.emp_id;

-- 8. 보너스가 없고 직급 코드가 J4이거나 J7인 사원의 이름, 직급 명, 급여 조회
select emp_name, job_name, salary
from employee
join job using(job_code)
where bonus is null
  and job_code in ('j4', 'j7');

-- 9. 부서명과 부서별 급여합계 조회  
select dept_title, sum(salary) as "급여합"
from employee
join department on (dept_code = dept_id)
group by dept_title;

-- 10. 부서별 급여 합계가 전체 급여 총합의 20%보다 많은 부서의 부서명, 부서별 급여 합계 조회
-- TODO : 서브쿼리 
select dept_title, sum(salary) as "급여합"
from employee
join department on (dept_code = dept_id)
group by dept_title
having sum(salary) > 14019248;

select sum(salary) * 0.2
from employee;

-- 11. 나이상 가장 막내인 사원의 사원코드, 사원명, 나이, 부서명, 직급명 조회  
--     (단, 나이는 만 나이로 계산하거나, 주민번호 앞자리를 기준으로 구하시오)
-- TODO : 서브쿼리
select emp_id, emp_name, 
	   substring(emp_no, 1, 2)
from employee
join department on (dept_code = dept_id);

-- 12. 해외영업부서(해외영업1부, 2부, 3부)에 근무하는 사원들의 사원명, 직급명, 부서명, 급여를 조회하시오.
select emp_name, job_name, dept_title, salary
from employee
join department on (dept_code = dept_id)
join job using(job_code)
where dept_title like '해외영업%';

-- 13. 비등가 조인을 활용하여 사원명, 부서명, 급여, 급여등급(SAL_LEVEL)을 조회하시오. 
--     (단, 부서 배치를 받지 않은 사원도 포함할 것)
select emp_name, dept_title, salary, sal_level
from employee
left join department on (dept_code = dept_id)
join sal_grade on (salary between min_sal and max_sal);

-- 14. 본인이 속한 부서의 평균급여보다 더 많은 급여를 받는 사원들의 사번, 사원명, 부서명, 급여를 조회하시오.
--     (단, 부서 배치를 받은 사원들만 대상으로 할 것)
-- TODO : 서브쿼리

-- 15. 부서 배치를 받지 않은 사원(DEPT_CODE가 NULL)의 사원명, 직급명, 급여를 조회하되, 
-- 직급명 오름차순으로 정렬하시오.
select emp_name, job_name, salary
from employee
join job using(job_code)
where dept_code is null
order by job_name asc; 