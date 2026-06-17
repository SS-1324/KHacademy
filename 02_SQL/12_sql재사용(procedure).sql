/*
	프로시저
    데이터베이스 내에 쿼리문을 마치 하나의 함수처럼 묶어서 저장해두고,
    필요할 때 호출해서 사용하는 DB객체
    
    1. 성능향상 : 여러개의 sql문을 네트워크로 매번 보내지않고, db에서 한번에 처리하기 때문에 빠르다.
    2. 재사용성 : 복잡한 로직을 한번 만들어두면 이름으로만 호출해서 사용할 수 있다.
    3. 보안성 : 테이블에 직접 접근하는 권한을 주지않고, 프로시저 실행 권한만 주면 데이터를 안전하게 관리할 수 있다.

	프로시저 사용시 구분자(;)를 먼저 변경해야한다.
    ;을 만나면 sql을 즉시 실행함. 프로시저 내부에는 여러개의 ;이 들어가야한다.
    그래서 프로시저를 만드는 동안에 세미콜론을 만나도 실행이되지 않게 하기 위해
    임시로 끝나는 구분자를 변경해줘야한다.
    -> delimiter 구분자
*/

-- 기본 프로시저

-- 1. 시작전 구분자 변경 ; -> //
delimiter //

-- 2. 프로시저 생성
create procedure pro_all_emp()
begin
	-- 내부에서 자유롭게 sql문을 사용
    select emp_id, emp_name, dept_code, salary
    from employee;
end //

-- 3. 생성 후 구분자 변경 // -> ;
delimiter ;

-- 저장된 프로시저 호출 방법
-- call 프로시저명()
call pro_all_emp();

drop procedure if exists pro_all_emp;

/*
	매개변수가 있는 프로시저
    외부에서 값을 전달받아 쿼리에 사용하는 방법
*/

delimiter //

create procedure pro_sal_raise(
	in p_dept_code varchar(3),
    in p_raise_rate decimal(3,2)
)
begin
	update employee
      set salary = salary * (1 + p_raise_rate)
	where dept_code = p_dept_code;
    
    select emp_name, dept_code, salary
    from employee
    where dept_code = p_dept_code;
end//

delimiter ;

select emp_name, dept_code, salary
    from employee;

-- D5부서의 사원들의 급여를 10%인상 
call pro_sal_raise('D5', 0.1);


/*
	반환값이 있는 프로시저
*/

delimiter //

create procedure pro_emp_count(
	in p_dept_code varchar(3),
    out p_emp_count int
)
begin
	select count(*)
    into p_emp_count
    from employee
    where dept_code = p_dept_code;
end//

delimiter ;

-- 반환값을 받아줄 변수는 @+변수명으로 넘겨준다
call pro_emp_count('D5', @res_count);

select @res_count as 'dept_emp_count';

/*
	제어문(if ~ then ~ else) 활용
*/

-- 사원의 사번을 전달받아
-- 보너스를 지급받는 사원인지 아닌지 확인후
-- 보너스를 받는 사람이면 ~~~사원의 보너스율은 ~입니다.
-- 보너스를 받지 않는 사람이면 ~~사원은 보너스 지급대상이 아닙니다.
delimiter //

create procedure pro_check_bouns(
	in p_emp_id varchar(3)
)
begin
	-- 프로시저 내부에서 사용할 지역변수는 declare로 선언가능
	declare v_bonus decimal(3,2);
    declare v_emp_name varchar(20);

	select emp_name, bonus
    into v_emp_name, v_bonus
    from employee
    where emp_id = p_emp_id;
    
    if v_bonus is null 
		then select concat(v_emp_name, '사원은 보너스 지급대상이 아닙니다.') as result;
    else
		select concat(v_emp_name, '사원의 보너스율은 ', v_bonus, '입니다') as result;
	end if;
    /*
		if 조건
			then 조건만족시 실행할 코드
		else
			불만족시 실행할 코드
		end if;
    */
end//

delimiter ;

call pro_check_bouns('200');
call pro_check_bouns('201');
call pro_check_bouns('209');

/*
	프로시저 반복문(while)
*/

create table if not exists test_loop_tb(
	id int auto_increment primary key,
    memo varchar(50)
);

-- 내가원하는 숫자를 입력하면 그 숫자만큼 더미데이터를 넣어주는 프로시저
delimiter //

create procedure pro_insert_dummy(
	in p_count int
)
begin
	-- 반복에 사용할 변수 생성
    declare v_count int default 1;
    
    -- 반복문을 통해서 p_count만큰 더미데이터를 추가
    while v_count <= p_count do
    
		-- 반복해서 실행할 코드
		insert into test_loop_tb(memo)
        values(concat('더미데이터 : ', v_count));
        
        -- 반복종료를 위한 증감식
        set v_count = v_count + 1;
    end while; -- 반복문 종료
    
    /*
		while 조건 do
			반복할 코드
		end while; 
    */
    
    select concat(p_count, '개의 더미데이터 추가 완료') as msg;
end//

delimiter ;

call pro_insert_dummy(100);
select * from test_loop_tb;

/*
	프로시저를 예외처리, 트랜잭션과 함께 사용하는 경우가 많음
    프로시저 도중 에러가 발생하면 전부 롤백하거나 커밋.
    
    예외처리
	declare 처리방식 handler for 예외조건
    begin
		-- 예외발생시 실행할 대체쿼리
    end;
    
    처리방식 : exit(종료), continue(에러가 발생한 다음줄부터 다시 시작)
    예외조건 : sqlexception(sql오류), sqlwarning(오류는아닌 경고수준), not found(결과값없음)
*/

delimiter //

create procedure pro_safe_transfer()
begin
	-- 만약 db에러(sqlexception)가 발생하면 특정 코드를 실행하고 종료.(exit)
    declare exit handler for sqlexception
    begin
		rollback;
        select '오류가 발생해서 모두 rollback함' as msg;
	end;
    
    -- 트랜잭션 시작
    start transaction;
    
    delete from employee where emp_id = '200';
    insert into department values('D999', '에러관리부', 'L1'); -- 에러발생
    
    commit;
    select '모든 작업 완료 후 commit' as msg;
end//

drop

delimiter ;

call pro_safe_transfer();

select * from employee where emp_id = '200';