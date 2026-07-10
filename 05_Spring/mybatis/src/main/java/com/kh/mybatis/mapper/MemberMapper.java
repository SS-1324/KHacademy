package com.kh.mybatis.mapper;

import com.kh.mybatis.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
    MemberDAO를 대체하는 MyBatis의 Mapper

    @Mapper : 이 인터페이스는 MyBatis의 Mapper로써 spring이 애플케이션 시작시 인터페이스의 구현체를 자동으로 만들어서 Been으로 등록
* */

@Mapper
public interface MemberMapper {

    //전체 회원 조회(sql문이 저장된 xml에 id값과 mapper의 함수명이 동일)
    List<MemberDTO> findAll();

    //회원 등록
    int insert(MemberDTO dto);

    //회원 삭제
    int remove(int id);
}
