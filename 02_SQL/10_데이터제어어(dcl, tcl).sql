/*
	DCL : 데이터 제어어
    데이터베이스 계정에 시스템 권한 또는 객체 접근권한을 부여(grant)하거나 회수(revoke)하는 구문
    
    - 시스템권한 : DB생성, 사용자 생성등 관리자 차원의 권한
    - 객체 접근 권한 : 특정테이블, 뷰등에 대한 select, insert, update, delete권한
*/

-- 1. 사용자 생성
-- localhost = dbms와 같은 컴퓨터에서만 접근가능
-- % = 모든 컴퓨터에서 접근 가능, 특정IP(ex 192.xxx.xx.xx) = 특정 ip컴퓨터에서만 접근가능 
create user 'user01'@'localhost' identified by 'test01';

-- 2. 권한부여
-- grant 권한종류 on db명.테이블명 to 계정명@접속위치;

-- 3. 권한회수(revoke)
-- revoke 권한종류 on db명.테이블명 from 계정명@접속위치;

-- -----------------------------------------------------
/*
	TCL : 트랜잭션 제어어
    트랜잭션은 하나 이상의 DML(insert, update, delete) 문을 묶어서
    하나의 논리적인 작업 단위로 처리하는 개념.
    
    commit : 트랜잭션 내용을 db에 반영하겠다.
    rollback : 트랜잭션 내용을 전부 취소하겠다.
    savepoint : 트랜잭션도중 시점을 저장하겠다. -> 해당시점으로 rollback가능
     - rollback to : 지정한 savepoint까지 돌아가겠다.
*/

-- mysql은 기본적으로 DML실행 즉시 commit이 되는 autocommit이 켜저있다.

-- autocommit 상태확인
select @@autocommit;

-- autocommit 수동전환
set autocommit = 0;
set autocommit = 1;

-- 직접 트랜잭션 사용시에는 autocommit은 그냥 켜두고
-- start transaction 활용 -> 지금부터 트랜잭션을 만들어서 직접 사용하겠다.
start transaction;

drop table if exists emp_01;
create table emp_01 as select emp_id, emp_name, dept_code from employee;

select * from emp_01;

-- 트랜잭션 시작
start transaction;

-- 200번 201번 사원 삭제
delete from emp_01
where emp_id in (200,201);

-- 지워진 것 처럼 보이지만 아직 DB반영은 안된상태
select * from emp_01;

-- 트랜잭션에 있는 작업 전부 취소
rollback;

select * from emp_01; -- 200, 201번 데이터가 다시 살아남.


-- 트랜잭션 시작
start transaction;

delete from emp_01
where emp_id in (200,201);

commit; -- 트랜잭션에 있는 모든 작업을 픽스
rollback; -- 이미 커밋으로 트랜잭션이 비워저 있다.
select * from emp_01;


-- savepoint
start transaction;

-- 214,216,217 사원제거 
delete from emp_01
where emp_id in (214,216,217);

savepoint sp;

insert into emp_01 values(801, '김민규', 'D1');
insert into emp_01 values(802, '정민규', 'D2');
select * from emp_01;

-- 신입사원 추가 잘못됬다 돌리자
rollback to sp;

-- insert작업 두개는 취소되고 
-- sp이전에 실행한 214,216,217제거는 유지됨.
select * from emp_01;

commit;


-- -----------------------------------------------
/*
	트랜잭션 진행 중에 create, alter, drop, truncate같은 DDL문을 실행하면
    현재까지 진행중인 트랜잭션 작업을 즉시 commit(강제로 픽스)
    
    -> DML작업중에 절대 DDL작업하지마라!
*/

start transaction;

insert into emp_01 values(999, '테스트', 'D1');

-- create가 실행되는 순간 이전에 작업한 insert는 강제로 commit
create table test(tid int); 

rollback;
select * from emp_01;

/*
	트랜잭션의 ACID 속성
    트랜잭션이 데이터베이스의 일관성과 신뢰성을 보장하기위해 지켜야하는 4가지
    
    A(Atomicity, 원자성) : 트랜잭션내의 작업은 모두 성공(commit), 모두 취소(rollback)되어야한다.
    C(Consistency, 일관성) : 트랜잭션 성공 후의 데이터베이스는 항상 유효한 상태를 유지해야한다. -> 제약조건을 위반하면 안된다.
    I(isolation, 독립성) : 여러 트랜잭션이 동시에 실행될 때, 서로의 중간 작업 상태를 엿보거나 간섭할 수 없다.
    D(durability, 지속성) : 한번 커밋되면 이 결과는 시스템이 다운되거나 정전이 발생해도 영구적으로 보존되어야한다.
*/