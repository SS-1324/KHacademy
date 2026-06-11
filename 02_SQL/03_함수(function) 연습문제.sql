use tdb;

-- 문제 1. 
-- 사원들의 사원명, 사원명의 글자 수, 이메일의 바이트(Byte) 수를 조회하시오.
select emp_name, char_length(emp_name), length(email)
from employee;

-- 문제 2. 
-- 사원들의 사원명과 주민등록번호의 생년월일(앞 6자리)만 추출하여 '생년월일'이라는 별칭으로 조회하시오.
select emp_name, substring(emp_no, 1, 6) as "생년월일"
from employee;

-- 문제 3. 
-- CONCAT_WS 함수를 사용하여 사원들의 사원명과 이메일을 "이름 - 이메일" 형식으로 결합하여 조회하시오.
select concat_ws('-', emp_name, email)
from employee;

-- 문제 4. 
-- 사원들의 사원명과 1일 급여(SALARY / 30)를 조회하되, 소수점 첫째 자리에서 버림(TRUNCATE)하여 정수로 표기하시오.
select emp_name, TRUNCATE(salary/30, 0)
from employee;

-- 문제 5. 
-- 입사년도가 2000년 이상인 사원들의 사원명, 입사일, 정직원 전환일(입사일 기준 6개월 뒤)을 조회하시오.
select emp_name, hire_date,
	   date_add(hire_date, interval 6 month)
from employee
where year(hire_date) >= 2000;

-- 문제 6. 
-- 사원들의 사원명, 급여, 입사일을 조회하시오. 
-- 단, 급여는 3자리마다 콤마를 찍고, 입사일은 'YYYY년 MM월 DD일' 형식의 문자열로 변환하시오.
select emp_name, format(salary, 0), date_format(hire_date, '%Y년 %m월 %d일')
from employee;

-- 문제 7. 
-- 사원들의 사원명과 관리자사번(MANAGER_ID)을 조회하되, 관리자가 없는 경우 '관리자 없음'으로 출력하시오.
select emp_name, ifnull(manager_id, '관리자 없음')
from employee;

-- 문제 8. 
-- 사원들의 사원명, 보너스를 조회하고 보너스율이 0.2 이상이면 '우수', 
-- 그 미만이거나 NULL이면 '일반'으로 '보너스평가'를 조회하시오.
select emp_name, bonus,
		if(bonus >= 0.2, '우수', '일반')
from employee;

-- 문제 9.
-- 사원명, 입사일, '근무형태'를 조회하시오. 
-- '근무형태'는 입사년도가 2000년 이전이면 '창립멤버', 2000년~2010년이면 '핵심멤버', 2011년 이후면 '신규멤버'로 분류하시오.
select emp_name, hire_date,
       case
		 when year(hire_date) < 2000 then '창립멤버'
         when year(hire_date) <= 2010 then '핵심멤버'
         else '신규멤버'
	   end as "근무형태"
from employee;

-- 문제 10. 
-- 전체 사원 중 보너스를 받는 사원의 수(COUNT)와, 이들에게 지급되는 총 보너스 금액 합계(SUM(SALARY * BONUS))를 조회하시오.
select count(bonus), sum(salary * bonus)
from employee;
