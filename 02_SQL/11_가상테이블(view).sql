/*
	VIEW : select 결과로만든 가상테이블
    자주사용하는 복잡한 select문을 하나의 가상테이블처럼 저장해놓은 객체
    실제 데이터를 디스크에 물리적으로 저장하지 않고, 뷰를 조회할 때마다 select문이 실행됨.
    복잡한 join쿼리를 매번 작성할 필요 없이 재사용할 수 있어서 가독성과 생산성이 증가.
*/

use tdb;
-- 한국, 러시아, 일본등 각 국가별 근무 사원을 조회할 때
select emp_id, emp_name, dept_title, salary, national_name
  from employee
  join department on (dept_code = dept_id)
  join location on (location_id = local_code)
  join national using(national_code)
 where national_name = '한국';

select emp_id, emp_name, dept_title, salary, national_name
  from employee
  join department on (dept_code = dept_id)
  join location on (location_id = local_code)
  join national using(national_code)
 where national_name = '러시아';

select emp_id, emp_name, dept_title, salary, national_name
  from employee
  join department on (dept_code = dept_id)
  join location on (location_id = local_code)
  join national using(national_code)
 where national_name = '일본';

-- 매번 이렇게 긴 join쿼리를 작성해야한다면
-- view를 생성해서 효율적으로 관리할 수 있음.

/*
	VIEW 생성및 수정방법
    
    create [or replace]view 뷰명
    as 서브쿼리(select문)
*/

create or replace view vw_employee as
select emp_id, emp_name, dept_title, salary, national_name
  from employee
  join department on (dept_code = dept_id)
  join location on (location_id = local_code)
  join national using(national_code);
  
-- 생성된 뷰는 테이블과 동일하게 사용 가능

select * from vw_employee
where national_name = '중국';

/*
	연산식이나 함수를 포함한 view 생성
*/
create or replace view vw_emp_job as
select emp_id, emp_name, job_name,
       if(substring(emp_no, 8, 1) in ('1','3'), '남','여') as gender,
       year(now()) - year(hire_date) as service_year
from employee
join job using(job_code);

select * from vw_emp_job;

-- 뷰삭제
drop view vw_emp_job;

/*
	뷰를 통한 DML이 가능하다.
    -> 뷰를 조작하면, 뷰가 참조하있는 실제 원본테이블의 데이터가 변경됨.
    -> 대체적으로 view를 통한 데이터조작은 가능하나 권장하지않음.
*/

/*
	view 생성 옵션
    create [or replace] view 뷰명 as 서브쿼리
    [with check option]
*/

-- with check option
-- 뷰를 생성할 때 쓴 where조건에 위배되는 데이터의 삽입/수정을 차단한다.
create or replace view vw_emp as
select * from employee where salary >= 3000000;

select * from vw_emp;

-- 원본테이블이 수정되면서 이 뷰에서는 더이상 해당 데이터를 가져올 수 없다.
update vw_emp set salary = 2000000 where emp_id = '200';

select * from vw_emp;
select * from employee;

update employee set salary = 8000000 where emp_id = '200';

create or replace view vw_emp as
select * from employee where salary >= 3000000
with check option;

-- 뷰의 조건을 벗어나는 값으로 데이터가 수정되면 db가 강제로 막아준다.
update vw_emp set salary = 2000000 where emp_id = '200';

/*
	인라인뷰
    from절에 사용하는 서브쿼리를 말함.
    create view로 생성하는 뷰는 아니고 쿼리가 실행되는 순간 서브쿼리의 결과를
    마치 가상테이블처럼 from절에 사용할 수 있다.
*/
select V.emp_name, V.salary
from (
	select emp_id, emp_name, salary
    from employee
) as V; -- 인라인뷰는 별칭이 필수

select emp_id, emp_name, salary
    from employee;

-- 부서별 평균급여보다 많이 받는 사원 찾기
select E.emp_name, E.dept_code, E.salary, V.avg_sal
from employee E
join (select dept_code, avg(salary) as avg_sal
	  from employee
	  group by dept_code) as V on (E.dept_code = V.dept_code)
where E.salary > V.avg_sal;

-- 문제 1
-- EMPLOYEE 테이블에서 사번(EMP_ID), 직원명(EMP_NAME), 직급코드(JOB_CODE)만 조회하는 인라인 뷰를 만들고,
-- 그 가상 테이블에서 직급코드가 'J5' 또는 'J6'인 사원만 조회하시오.
select *
from (select emp_id, emp_name, job_code
		from employee) as V
where V.job_code in ('J5', 'J6');
        
select emp_id, emp_name, job_code
from employee;

-- 문제 2.
-- 각 부서별(DEPT_CODE)로 가장 높은 급여(MAX_SAL)를 구하는 인라인 뷰를 작성하고, 
-- 이를 EMPLOYEE 테이블과 조인하여 '자신이 속한 부서에서 가장 높은 급여를 받는 사원'의
-- 직원명(EMP_NAME), 부서코드(DEPT_CODE), 급여(SALARY)를 조회하시오.
select E.emp_name, E.dept_code, E.salary
from employee E
join (select dept_code, max(salary) as max_sal
		from employee
		group by dept_code) as G_MAX
  on (E.dept_code = G_MAX.dept_code and E.salary = G_MAX.max_sal);

select dept_code, max(salary)
from employee
group by dept_code;

-- 문제 3
-- 입사일(HIRE_DATE)이 가장 빠른(오래된) '최고참 사원 3명'의 직원명과 입사일을 조회하시오.
select emp_name, hire_date
from employee
order by hire_date asc
limit 3;



