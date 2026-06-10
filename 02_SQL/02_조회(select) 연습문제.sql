use tdb;

-- 1. 이름이 '연'으로 끝나는 사원들의 사원명, 입사일 조회
select emp_name, hire_date
from employee
where emp_name like '%연';

-- 2. 전화번호 처음 3자리가 010이 아닌 사원들의 사원명, 전화번호 조회
select emp_name, phone
from employee
where phone not like '010%';

-- 3. 이름에 '하'가 포함되어있고 급여가 240만원 이상인 사원들의 사원명, 급여 조회
select emp_name, salary
from employee
where emp_name like '%하%' and salary >= 2400000;

-- 4. 부서(DEPARTMENT) 테이블에서 해외영업부서인 부서들의 부서코드, 부서명 조회
select dept_id, dept_title
from department
where dept_title like '해외영업%';

-- 5. 사수(MANAGER)가 없고 부서배치도 받지 않은 사원들의 사원명, 사번, 부서코드 조회
select emp_name, emp_id, dept_code
from employee
where manager_id is null and dept_code is null;

-- 6. 연봉(급여 * 12)이 3천만원 이상이고 보너스를 받지 않는 사원들의 사번, 사원명, 급여, 보너스 조회
select emp_id, emp_name, salary, bonus
from employee
where (salary * 12) >= 30000000 and bonus is null;

-- 7. 입사일이 '1995-01-01' 이상이고 부서배치를 받지 않은 사원들의 사번, 사원명, 입사일, 부서코드 조회
select emp_id, emp_name, hire_date, dept_code
from employee
where hire_date >= '1995-01-01' and dept_code is null;

-- 8. 급여가 200만원 이상 500만원 이하이면서 입사일이 '2001-01-01' 이상이고 보너스를 받지 않는 사원 조회
select * 
from employee
where salary between 2000000 and 5000000
  and hire_date >= '2001-01-01'
  and bonus is null;

-- 9. 보너스를 포함한 연봉((SALARY + (SALARY * BONUS)) * 12)이 NULL이 아니고 이름에 '하'가 포함된 사원 조회
select * from employee
where ((SALARY + (SALARY * BONUS)) * 12) is not null
and emp_name like '%하%';

-- 10. 사원명(이름)이 '이'로 시작하거나 '연'으로 끝나는 사원들의 사번, 사원명 조회
select emp_id, emp_name
from employee
where emp_name like '이%' or emp_name like '%연';

-- 11. 부서코드가 'D1', 'D2', 'D3' 중 하나이면서, 사수(MANAGER)가 배정되어 있는 사원들의 사원명, 직급코드, 부서코드, 관리자사번 조회
select emp_name, job_code, dept_code, manager_id
from employee
where dept_code in ('d1', 'd2', 'd3')
  and manager_id is not null;

-- 12. 급여가 250만 원 이상 350만 원 이하인 사원 중에서, 직급코드(JOB_CODE)가 'J3'가 아닌 사원들의 사원명, 급여, 직급코드 조회
select emp_name, salary, job_code
from employee
where salary between 2500000 and 3500000
  and job_code != 'j3';

-- 13. 2000년 1월 1일 이전에 입사한 사원들의 사원명, 입사일, 실수령 월급(기본급 + 보너스 금액) 조회 
-- (단, 보너스가 없는 사원은 0으로 계산할 것)
select emp_name, hire_date, salary + (salary * ifnull(bonus, 0)) as 실수령금
from employee
where hire_date < '2000-01-01';

-- 14. 전화번호 중간에 '666'이 포함되고, 부서코드가 'D9' 또는 'D6'인 사원들의 사원명, 전화번호, 부서코드 조회
select emp_name, phone, dept_code
from employee
where phone like '%666%'
  and dept_code in ('d9', 'd6');

-- 15. 이메일(EMAIL) 주소에 'kh.co.kr'이 포함된 사원들 중, 급여가 300만원 이상인 사원들의 사원명, 이메일, 급여를 조회
select emp_name, email, salary
from employee
where email like '%kh.co.kr%'
  and salary >= 3000000;