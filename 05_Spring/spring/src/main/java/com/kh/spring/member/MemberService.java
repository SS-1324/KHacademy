package com.kh.spring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

/*
    @Service : @Component의 기능을 포함하고, "비즈니스 로직 계층"일음 나타내는 어노테이션

 */

@Service
public class MemberService {

    // @Autowired : spring에게 자동으로 여기에 필요한 객체를 만들어서 넣어줘.
    // DI(의존성 주입)
    @Autowired
    private MemberDAO memberDAO;

    public List<MemberDTO> getMemberList(){
        return memberDAO.findAll();
    }

    public void insertMember(MemberDTO dto){
        memberDAO.insert(dto);
    }

    public void deleteMember(int id){
        memberDAO.delete(id);
    }
}
