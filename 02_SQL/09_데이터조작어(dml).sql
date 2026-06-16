/*
	DML : 데이터 조작어
    테이블에 데이터를 추가(insert), 수정(update), 삭제(delete)하는 구문.
    
    DDL과는 달리 DML은 실행 후 트랜잭션처리를 통해 완전히 저장하거나 취소할 수 있다.
*/

/*
	INSERT
    : 테이블에 새로운 행(row)을 추가하는 구문.
*/

use tdb;

-- 1. 모든 컬럼에 값 추가
-- insert into 테이블명 values(값1, 값2, 값3...)
-- 테이블에 정의된 컬럼의 수와 순서에 맞게 정화히 값을 나열해줘야 한다.
select * from employee;

insert into employee
values(900, '김수민', '880914-1234567', 'sd123@kh.co.kr',	 '01011114444',	'D7', 'J5',	4000000, 0.2, null, now(), null, 'N');

-- 2. 특정 컬럼에만 값 추가
-- insert into 테이블명(컬럼1, 컬럼2, 컬럼3) values(값1, 값2, 값3);
-- - 선택하지 않은 컬럼에는 기본적으로 null이 들어가고, default설정이 있다면 default값이 들어감.
-- - not null제약조건이 있는 경우 반드시 값을 넣어주어야 한다.

insert into employee(
	emp_id,
    emp_name,
    emp_no,
    job_code,
    hire_date
) values(
	901,
    '최수민',
    '800102-2345678',
    'J7',
    now()
);

-- 3. 서브쿼리를 이용한 insert
-- values에 직접 값을 적는 대신, select문을 통해 조회된 결과를 한번에 추가할 수 있음.
create table emp_01(
	emp_id int,
    emp_name varchar(20),
    dept_title varchar(35)
);

select * from emp_01;

insert into emp_01
(
	select emp_id, emp_name, dept_title
    from employee
    left join department on (dept_code = dept_id)
);

select emp_id, emp_name, dept_title
    from employee
    left join department on (dept_code = dept_id);

select * from emp_01;


-- ----------------------------------------
/*
	다중 테이블 삽입
    여러테이블에 한번에 데이터를 추가할 수 있는 방법
    mysql : 없음
    oracle : insert all문법을 통해서 가능
*/

/*
	UPDATE : 데이터 수정
    테이블에 기록된 기존 데이터를 수정하는 구문
    
    update 테이블명
    set 컬럼1 = 값1,
        컬럼2 = 값2...
	[where 조건]
    
    where절 생략시 테이블의 모든 데이터가 수정되는 대참사 발생!
*/
create table dept_table as select * from department;
alter table dept_table add primary key(dept_id);

create table emp_salary 
as select emp_id, emp_name, dept_code, salary, bonus from employee;

select * from dept_table;

-- 업데이트 안전모드 끄기 0 켜기 1
set sql_safe_updates = 0;

-- D9 부서의 부서명을 전략기획팀 변경
update dept_table
   set dept_title = '전략기획팀'
 where dept_id = 'D9';
 
-- 선동일 사원의 급여를 700만원, 보너스를 0.2로 변경
update emp_salary
   set salary = 7000000,
       bonus = 0.2
 where emp_name = '선동일';
 
-- 전 사원의 급여를 기존급여 + 10%인상된 급여로 일괄 수정
update emp_salary
   set salary = salary * 1.1;
   
-- ASIA지역에서 근무하는 사원들의 보너스값을 0.3으로 변경
update emp_salary E
  join department D on (E.dept_code = D.dept_id)
  join location L on (D.location_id = L.local_code)
   set E.bonus = 0.3,
       D.dept_title = '아시아본부'
 where L.local_name like 'ASIA%';
 
 
 -- --------------------------------------------
 /*
	delete : 데이터 삭제
	테이블에 기록된 데이터를 삭제하는 구문.
    
    delete from 테이블명
    [where 조건];
    
    where절을 생략시 테이블의 모든 행이 삭제 -> truncate table
*/

start transaction; -- 트랜잭션 시작
 
-- 전체행 삭제
delete from employee; 

rollback;

start transaction;

delete from employee
where emp_name = '김수민';

delete from employee
where emp_id = 901;

commit;