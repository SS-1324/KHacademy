use tdb;

-- 문제 1.
-- DEPARTMENT 테이블에 부서코드 'D0', 부서명 '보안부', 지역코드 'L1' 데이터를 추가하시오.
insert into department(dept_id, dept_title, location_id)
values('D0','보안부', 'L1');

-- 문제 2.
-- EMPLOYEE 테이블에 
-- 사원번호 '300', 직원명 '김신입', 주민등록번호 '990101-1234567', 직급코드 'J7' 데이터만 추가하시오.
-- (나머지 컬럼은 생략하여 기본값이나 NULL이 들어가게 할 것)
insert into employee(emp_id, emp_name, emp_no, job_code)
values('300','김신입','990101-1234567','J7');

-- 문제 3.
-- EMPLOYEE 테이블에 사원번호 '301', 직원명 '이경력', 주민등록번호 '850101-2345678', 직급코드 'J5'를 추가하고, 
-- 입사일(HIRE_DATE)은 MySQL 날짜 함수를 사용하여 오늘 날짜가 들어가도록 명시하여 추가하시오.
insert into employee(emp_id, emp_name, emp_no, job_code, hire_date)
values('301','이경력','850101-2345678','J5', now());


-- 문제 4.
-- 퇴사한 직원(ENT_YN = 'Y')의 사번(EMP_ID), 직원명(EMP_NAME), 부서코드(DEPT_CODE)만 담을 수 있는 
-- 빈 테이블 'EMP_RETIRED'를 먼저 생성한 후, 
-- 서브쿼리를 이용해 EMPLOYEE 테이블에서 조회한 결과를 EMP_RETIRED 테이블에 모두 추가하시오.
create table emp_retired
as (select emp_id, emp_name, dept_code from employee where 1=2);

select * from emp_retired;

insert into emp_retired(emp_id, emp_name, dept_code)
(select emp_id, emp_name, dept_code
 from employee);

select * from emp_retired;

-- 문제 5.
-- EMPLOYEE 테이블에서 사원번호가 '200'인 사원의 급여(SALARY)를 8500000으로 수정하시오.
update employee
set salary = 8500000
where emp_id = '200';

select * from employee;

-- 문제 6.
-- EMPLOYEE 테이블에서 사원번호가 '214'인 사원의 부서코드(DEPT_CODE)를 'D2'로, 
-- 보너스율(BONUS)을 0.1로 동시에 수정하시오.
update employee
set DEPT_CODE = 'D2',
    BONUS = 0.1
where emp_id = '214';

select * from employee
where emp_id = '214';

-- 문제 7.
-- EMPLOYEE 테이블에서 부서코드가 'D5'인 모든 사원의 급여(SALARY)를 기존 급여에서 10% 인상하시오.
select * from employee where dept_code = 'd5';

set sql_safe_updates = 0;

update employee
set salary = salary * 1.1
where dept_code = 'D5';

select * from employee where dept_code = 'd5';

-- 문제 8.
-- EMPLOYEE 테이블과 JOB 테이블을 조인하여, 직급명(JOB_NAME)이 '차장'인 사원들의 보너스율(BONUS)을 0.2로 수정하시오.
update employee
join job using(job_code)
set bonus = 0.2
where job_name = '차장';

-- 문제 9.
-- EMPLOYEE 테이블에서 사원번호가 '300'인 사원을 삭제하시오.
delete from employee
where emp_id = '300';

select * from employee where emp_id = '300';


-- 문제 10.
-- EMPLOYEE 테이블에서 퇴직여부(ENT_YN)가 'Y'이거나 부서코드(DEPT_CODE)가 NULL인 사원들을 모두 삭제하시오.
-- (단, IN 연산자 또는 OR 연산자를 활용할 것)
delete from employee
where ent_yn = 'y' or dept_code is null;


-- 문제 11.
-- 기본키(PK)가 아닌 컬럼으로 데이터를 수정/삭제할 때 발생하는 에러를 막기 위해, 
-- MySQL의 안전 업데이트 모드를 일시적으로 해제하는 명령어를 작성하시오.
set sql_safe_updates = 0;

-- 문제 12.
-- 트랜잭션을 수동으로 시작한 뒤, EMPLOYEE 테이블의 모든 데이터를 삭제하시오.
-- 그 후 삭제한 데이터를 다시 원래대로 복원하시오.
start transaction;
delete from employee;

select * from employee;

rollback;

-- 문제 13.
-- 트랜잭션을 수동으로 시작한 뒤, JOB 테이블에 직급코드 'J8', 직급명 '인턴' 데이터를 추가하시오.
-- 해당 변경사항을 데이터베이스에 영구적으로 확정하시오.
start transaction;

insert into job(job_code, job_name)
values('J8','인턴');
commit;

-- 문제 14.
-- 트랜잭션을 시작하시오.
-- JOB 테이블에 직급코드 'J9', 직급명 '계약직'을 추가하시오.
-- 현재 지점에 'SP1'이라는 이름으로 SAVEPOINT를 만드시오.
-- JOB 테이블에 직급코드 'J10', 직급명 '임원'을 추가하시오.
-- 'SP1' 지점으로 롤백하시오.
-- 최종적으로 트랜잭션을 확정하시오.
alter table job modify job_code varchar(3) not null comment '직급코드';
alter table employee modify job_code varchar(3) not null comment '직급코드';

start transaction;
insert into job(job_code, job_name) values('J9', '계약직');
savepoint sp1;
insert into job(job_code, job_name) values('J10', '임원');
rollback to sp1;
commit;

-- 문제 15.
-- 트랜잭션을 시작하시오.
-- JOB 테이블에서 직급코드 'J1'의 직급명을 '회장'으로 수정하시오.
-- CREATE TABLE TEMP_TEST (ID INT); 구문을 실행하여 임시 테이블을 만드시오.
-- ROLLBACK을 실행하시오.
-- (ROLLBACK 실행 후 J1의 직급명이 어떻게 되었는지 주석으로 이유와 함께 설명하시오.)
start transaction;
update job set job_name = '회장' where job_code = 'J1';
create table temp_test(id int); -- DDL문이 실행되기 때문에 실행전에 트랜잭션이 있다면 commit후 실행됨.
rollback;
