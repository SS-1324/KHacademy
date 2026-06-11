/*
	정렬 : ORDER BY
    select문 구조상 가장 마지막에 작성되는 구문으로
    조회된 데이터를 특정 기준에 맞춰 순서대로 정렬할 때 사용.
    
    select 컬럼, 컬럼...
    from 테이블명
    where 조건
    order by 정렬기준(컬럼명 | 별칭 | 순번) [ASC | DESC]
    
    ASC : 오름차순
    DESC : 내림차순
    
    NULL : MYSQL(가장 작은 값), ORACLE(가장 큰 값)
*/

select emp_name, salary, hire_date
from employee
order by salary; -- 기본값은 오름차순(asc)

select emp_name, salary, hire_date
from employee
order by salary desc;

select emp_name, salary, hire_date
from employee
order by emp_name desc;

select emp_name, salary, hire_date
from employee
order by hire_date desc;

-- 다중정렬(기준이 여러개)
-- 첫번째 기준 동일할 경우 두번째 기준으로 정렬 ...
select emp_name, salary, hire_date, dept_code
from employee
order by dept_code asc, emp_name desc;


-- 별칭이나 순번으로도 정렬이 가능
-- order by가 가장 마지막에 실행되므로 별칭 사용이 가능.
select emp_name, (salary * 12) as "연봉"
from employee
order by 연봉 desc;

select emp_name, (salary * 12) as "연봉"
from employee
order by 2 desc;

-- null값 위치 변경
-- 오라클은 nulls first/ nulls last 같은 문법이 따로 존재함.

-- 기본적으로 null은 가장 작은값으로 가정함.
select emp_name, bonus
from employee
order by bonus asc;

-- 오름차순 정렬은 하되, null값을 뒤쪽에 배치하고 싶다면
-- bonus is null 조건이 참이면 1, 거짓이면 0
-- 오름차순정렬시 0(데이터 있음), 1(데이터 없음)
select emp_name, bonus
from employee
order by bonus is null asc, bonus;
