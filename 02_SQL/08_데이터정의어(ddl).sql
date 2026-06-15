/*
	DDL(Data Definition Language) : 데이터 정의어
    SQL명령어 중에서 데이터를 저장할 객체의 구조를 정의하거나 변경하는 명령어.
    즉, 실제 데이터 값이 아닌 테이블 구조의 규칙 자체를 정의하는 명령어.
    
    객체 : 테이블(table), 뷰(view), 인덱스(index), 프로시저(procedure), 트리거(trigger) 등...
    DDL명령어 : create(생성), alter(수정), drop(삭제), truncate(초기화)
*/

/*
	1. 테이블 생성 : create table
    - 행과 열로구성된 가장 기본적인 데이터베이스 구조
    - 모든 데이터는 테이블을 통해 저장되고 관리됨.
    
    create talbe 테이블명(
		컬럼명 자료형(크기) [제약조건],
        컬럼명 자료형(크기) [제약조건],
        컬럼명 자료형(크기) [제약조건]
        ...
    )
    
    자료형(DBMS마다 명칭이 조금씩 다름)
    - 정수 : int
    - 실수 : decimal
    - 고정길이 문자 : char(글자수) : 최대 255, 고정된 크기
    - 가변길이 문자 : varchar(글자수) : 가변적인 변경이 가능한 크기
    - 긴문자열 : text, longtext
    - 날짜/시간 : date(년-월-일), datetime(년-월-일 시:분:초), timestamp(년-월-일 시:분:초, UTC기반)
*/

use ddltdb;

-- 회원의 데이터를 담기위한 테이블 member 생성
/*
	회원저장하는 테이블
    - 회원번호, 아이디, 비밀번호, 이름, 성별, 전화번호, 이메일, 회원가입일
*/
create table member(
	member_no int comment '회원번호',
    member_id varchar(30) comment '회원아이디',
    member_pwd varchar(30) comment '회원비밀번호',
    member_name varchar(21) comment '회원이름',
    gender char(3) comment '성별(남/여)',
    phone varchar(13) comment '전화번호',
    email varchar(50) comment '이메일',
    create_at datetime comment '회원가입일',
    update_at datetime comment '회원수정일'
) comment = '회원 기본 정보 테이블';

-- 생성된 테이블 구조를 확인하는 명령어
show full columns from member;

-- 테스트데이터를 삽입
insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );

select * from member;

-- 제약조건이 없어서 중요 컬럼에 null값이 들어가거나 식별자 컬럼에 중복값이 들어가는 등
-- 데이터의 무결성을 보장하지 못하고 있다.
insert into 
member values(
			1, 
			'user01', 
			'pass01',
			null, 
			null, 
			'010-1111-2222', 
			null, 
			null, 
			null
        );
        
select * from member;

-- -----------------------------------------
/*
	제약조건 : constranints
    - 원하는 데이터값만 유지하기위해 특정 컬럼에 설정하는 규칙.
    - 데이터 무결성 보장을 목적으로 함.(정확성, 일관성, 신뢰성 유지)
    
    종류 : not null, unique, check, primary key, foreign key
*/

/*
	not null 제약조건
    해당 컬럼에 반드시 값이 존재해야한다. null 허용을 하지 않겠다.
*/
drop table if exists member;

create table member(
	member_no int not null comment '회원번호',
    member_id varchar(30) not null comment '회원아이디',
    member_pwd varchar(30) not null comment '회원비밀번호',
    member_name varchar(21) not null comment '회원이름',
    gender char(3) comment '성별(남/여)',
    phone varchar(13) comment '전화번호',
    email varchar(50) comment '이메일',
    create_at datetime comment '회원가입일',
    update_at datetime comment '회원수정일'
) comment = '회원 기본 정보 테이블';

insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );

-- 오류 발생 : member_name은 not null이므로 에러가 나면서 데이터 추가가 차단됨!
insert into 
member values(
			1, 
			'user01', 
			'pass01',
			null, 
			null, 
			'010-1111-2222', 
			null, 
			null, 
			null
        );

-- ----------------------------------------------------------
/*
	unique 제약조건
    해당 컬럼에 중복된 값이 들어가지 못하게 제한.
	단, null은 데이터가 없는 상태이므로 unique제약조건이 있어도 여러개 넣을 수 있다.
    
    [제약조건명 부여 문법]
    - 컬럼 레벨 방식 : 컬럼명 타입 제약조건
    - 테이블 레벨 방식 : 컬럼정의를 끝낸 후, 제약조건(컬럼명)
*/
drop table if exists member;

create table member(
	member_no int not null unique comment '회원번호',
    member_id varchar(30) not null unique comment '회원아이디',
    member_pwd varchar(30) not null comment '회원비밀번호',
    member_name varchar(21) not null comment '회원이름',
    gender char(3) comment '성별(남/여)',
    phone varchar(13) comment '전화번호',
    email varchar(50) comment '이메일',
    create_at datetime comment '회원가입일',
    update_at datetime comment '회원수정일',
    unique(email)
) comment = '회원 기본 정보 테이블';

insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );

-- 오류발생 : member_no가 기존데이터와 중복되므로 unique제약조건 위발 에러 발생.
insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );
        
-- --------------------------------------------------
/*
	check 제약조건
    컬럼에 들어올 수 있는 값의 번위나 조건을 직접 지정한다.
    
	* 예전 mysql에서는 check제약조건을 무시함.
    -> enum타입사용으로 대체
    
    enum : 미리 정해둔 문자열 값중 오직 하나만 넣을 수 있는 데이터타입
    간단하는 장점이 있지만, 컬럼 타입의 수정이 어렵다는 단점이 있다.
*/
drop table if exists member;
create table member(
	member_no int not null unique comment '회원번호',
    member_id varchar(30) not null unique comment '회원아이디',
    member_pwd varchar(30) not null comment '회원비밀번호',
    member_name varchar(21) not null comment '회원이름',
    -- gender enum('남','여') comment '성별(남/여)',
    gender char(3) check (gender in ('남', '여')) comment '성별(남/여)',
    phone varchar(13) comment '전화번호',
    email varchar(50) comment '이메일',
    create_at datetime comment '회원가입일',
    update_at datetime comment '회원수정일',
    unique(email)
    -- check (gender in ('남', '여'))
) comment = '회원 기본 정보 테이블';

insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );

-- gender에 check제약조건을 위반 오류 발생('남', '여'만 추가가능)
insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'무', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );

-- ------------------------------------------
/*
	primary key(PK) : 기본키 제약조건
    테이블에서 각 행을 식별하기위해 지정하는 컬럼.
    여러개의 식별가능한 컬럼(후보키) 중 딱 하나만 지정해서 부여.
    특징 : not null + unique
    
    mysql에서는 키본키 유일성을 편하게 유지하기위해서 기본키 설정시
    자동으로 값을 1씩 증가하게 만들어주는 옵션을 사용한다. -> (auto_increment)
    -> 종속형 시퀀스
*/

drop table if exists member;
create table member(
	-- auto_increment 설정 시 데이터가 추가될때마다 이컬럼을 생략해도 자동으로 번호를 부여한다.
	member_no int auto_increment primary key comment '회원번호',
    member_id varchar(30) not null unique comment '회원아이디',
    member_pwd varchar(30) not null comment '회원비밀번호',
    member_name varchar(21) not null comment '회원이름',
    -- gender enum('남','여') comment '성별(남/여)',
    gender char(3) check (gender in ('남', '여')) comment '성별(남/여)',
    phone varchar(13) comment '전화번호',
    email varchar(50) comment '이메일',
    create_at datetime comment '회원가입일',
    update_at datetime comment '회원수정일',
    unique(email)
    -- check (gender in ('남', '여'))
) comment = '회원 기본 정보 테이블';

insert into 
member values(
			1, 
			'user01', 
			'pass01',
			'홍길동', 
			'남', 
			'010-1111-2222', 
			'aaa@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );
        
insert into 
member values(
			null, 
			'user02', 
			'pass01',
			'홍길순', 
			'남', 
			'010-1111-3333', 
			'bbb@naver.com', 
			'2026-06-15 00:00:00', 
			'2026-06-15 00:00:00'
        );
        
-- 복합키 : 두개이상의 컬럼을 합해서 하나의 primary key로 묶는 것
-- 무조건 테이블레벨 방식으로만 선언이 가능

-- 좋아요 테이블
create table tb_like(
	member_no int, -- 누가
    product_no int, -- 어떤 상품을
    like_date datetime, -- 언제
    primary key(member_no, product_no) -- 두 컬럼의 조합을 식별자로 사용
);

insert into tb_like values(1, 2, now()); -- 1번 사용자가 2번상품을 좋아요누름
insert into tb_like values(1, 3, now()); -- 1번 사용자가 3번상품을 좋아요누름
insert into tb_like values(2, 2, now()); -- 2번 사용자가 2번상품을 좋아요누름
-- 1번 사용자가 2번상품을 좋아요 누른 데이터는 이미 존재하므로 중복에러가 발생
insert into tb_like values(2, 2, now()); 

-- -----------------------------------
/*
	foreign key : 외래키 제약조건
    다른 테이블의 기본키(PK) 또는 식별키(unique)를 참조하여 관계를 맺는 제약조건.
    - 외래키로 지정된 컬럼에는 부모테이블에 실제 존재하는 값만 들어올 수 있다.(데이터 불일치 방지)
    
    컬럼 타입 foreign key references 참조할테이블명(참조할 컬럼)
    단, 참조할 컬럼 생략시 참조테이블의 PK가 자동으로 참조.
    
    * 부모 테이블 : 참조를 당하는 기준 테이블 (예 : department)
    * 자식 테이블 : 외래키를 들고 부모를 참조하는 테이블 (예 : employee)
*/

-- mem_grade : 부모테이블예시 (참조 당하는 테이블)
create table mem_grade(
	grade_no int primary key,
    grade_name varchar(30) not null
);

insert into mem_grade 
values(10, '일반회원'), (20, '우수회원원'),(30, '특별회원'),(40, 'VIP회원');

-- mem : 자식테이블예시(참조를 하는 테이블)
create table mem(
	mem_no int primary key,
    mem_id varchar(30) not null unique,
    mem_pwd varchar(30) not null,
    grade_no int,
    -- 테이블레벨방식
    foreign key(grade_no) references mem_grade(grade_no)
);

insert into mem values(1, 'user01', 'pass01', 10);
-- 부모테이블(mem_grade)에 50이라는 grade_code가 없으므로 외래키 제약조건 위반 에러 발생.
insert into mem values(2, 'user02', 'pass02', 50);

-- 부모테이블의 10번등급 삭제시 참조하고있는 값 떄문에 삭제불가 에러가 발생.
delete from mem_grade where grade_no = 10;

/* 
	외래키 삭제 옵션
    부모 테이블 데이터가 삭제될 때, 이를 참조하고있는 자식테이블의 데이터를 어떻게 처리할것인가?
    - on delete restrict (기본값) : 삭제 제한. 참조중인 값이 있다면 삭제 불가.
    - on delete set null : 부모 데이터 삭제시, 자식 테이블의 외래키 값은 null로 변경.
    - on delete cascade : 부모 데이터 삭제시, 자식 테이블의 데이터도 함께 삭제.
    
    
*/
drop table if exists mem;

create table mem(
	mem_no int primary key,
    mem_id varchar(30) not null unique,
    mem_pwd varchar(30) not null,
    grade_no int,
    -- 부모 삭제시 자식의 등급칸을 자동으로 null로 비우겠다.
    foreign key(grade_no) references mem_grade(grade_no) on delete set null
);

insert into mem values(1, 'user01', 'pass01', 10);
-- 정상적으로 제거되고 참조하던 user01의 등급no는 null이 됨.
delete from mem_grade where grade_no = 10;

-- ----------------------------------------------------
/*
	default : 기본값 설정
    제약조건은 아니고 값을 명시하지 않았을 때 자동으로 채워질 값을 지정할 수 있음.
*/

drop table if exists mem;

create table mem(
	mem_no int auto_increment primary key,
    mem_id varchar(30) not null unique,
    mem_pwd varchar(30) not null,
    grade_no int,
    hobby varchar(20) default '코딩',
    -- current_timestamp(ansi 구문)
    create_at datetime default current_timestamp,
    -- 부모 삭제시 자식의 등급칸을 자동으로 null로 비우겠다.
    foreign key(grade_no) references mem_grade(grade_no) on delete set null
);

insert into mem values(1, 'user01', 'pass01', 20, default, default);
insert into mem(mem_id, mem_pwd) values ('user02', 'pass02');

-- -----------------------------------------
/*
	테이블 복제
    기존 테이블의 구조와 데이터를 한번에 카피해서 새 테이블을 만드는 방법.
    테이블의 컬럼명, 타입, 데이터만 복제가 가능하고 제약조건은 반영x
*/

-- 구조 + 테이버 복사
create table mem_copy 
as (select * from mem);

-- 의도적으로 조건을 불일치하여 구조만 복사도 가능함.
select * from mem_copy;

-- 테이블의 구조만 복사
create table mem_copy2
as (select * from mem where 1=0);

-- ------------------------------------------------
/*
	alter talbe : 테이블 구조 변경
    이미 생성된 테이블의 컬럼이나 제약조건을 추가, 수정, 삭제하는 구문
    
    - 컬럼변경 : add(추가), modify(수정), rename column(이름변경), drop(삭제)
    - 제약조건변경 : add(추가), drop(삭제)
    * not null은 제약조건이 나닌 컬럼의 속성으로 취급
*/
use ddltdb;

create table employee_copy as select * from tdb.employee;
create table department_copy as select * from tdb.department;

select * from employee_copy;

-- --------------------------------------
-- 컬럼 추가/수정/삭제/이름변경

-- 컬럼추가
-- 새로운 컬럼은 기본적으로 가장 마지막에 추가됨
alter table employee_copy add age int;
alter table employee_copy add address varchar(30) default '미입력';
select * from employee_copy;

-- 컬럼수정
alter table employee_copy modify age varchar(10);
-- 기존 데이터가 들어있는 경우, 크기를 줄이면 데이터손상이나 에러가 발생할 수 있음.
alter table employee_copy modify email varchar(10);

-- 컬럼이름변경
alter table employee_copy rename column phone to phone_number;

-- 컬럼삭제
alter table employee_copy drop column age;
-- 컬럼삭제시 데이터를 복구할 수 없으므로 조심
alter table employee_copy drop column email;

-- ---------------------------------------------------
/*
	제약조건 추가/수정/삭제
    제약조건 종류에 따라서 삭제하는 키워드가 구분되어있음.
    
    [CONSTRAINT 제약조건명] 제약조건
	모든 제약조건에는 제약조건명 입력가능, 입력 안할시 자동으로 생성됨.
*/

-- 제약조건 추가 add
alter table employee_copy add primary key(emp_id);
alter table department_copy add primary key(dept_id);

-- PK이거나 unique설정이 없는 값은 FK로 사용불가
alter table employee_copy 
add constraint fk_copy_dept foreign key(dept_code) references department_copy(dept_id);
alter table employee_copy add unique(emp_no);

-- not null 제약조건 추가및 수정(modify)
-- 컬럼자체를 수정하는 개념
alter table employee_copy modify emp_name varchar(20) not null;
alter table employee_copy modify emp_name varchar(20) null;

-- 제약조건 삭제 : drop
alter table employee_copy drop primary key;
alter table employee_copy drop foreign key employee_copy_ibfk_1;
-- mysql은 unique제약조건을 인덱스로 헝태로 관리하므로 삭제시에는 drop index키워드 사용
alter table employee_copy drop index EMP_NO;

-- 테이블의 대한 정보 확인
show create table employee_copy;

-- --------------------------------------
-- 테이블명 수정
alter table employee_copy rename to emp_copy;