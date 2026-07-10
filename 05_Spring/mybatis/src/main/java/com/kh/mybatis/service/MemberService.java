package com.kh.mybatis.service;

import com.kh.mybatis.model.MemberDAO;
import com.kh.mybatis.model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public List<MemberDTO> getMemberList(){
        return memberDAO.findAll();
    }

    public int registerMember(MemberDTO dto){
        return memberDAO.insert(dto);
    }
}
