package com.kh.demo.member.mapper;

import org.apache.ibatis.annotations.Mapper;
/*
* MyBatis 매퍼 인터페이스
*
* 해당 인터페이스는 구현체가 따로 없다.
* @Mapper 어노테이션을 붙이면 MyBatis-Spring이 애플케이션 시작 시점에 인터페이스를 확인해서
* 구현체를 자동으로 스프링빈에 등록해준다.
* */

@Mapper
public interface MemberMapper {
}
