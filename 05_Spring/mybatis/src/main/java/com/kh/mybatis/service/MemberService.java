package com.kh.mybatis.service;

import com.kh.mybatis.mapper.MemberMapper;
import com.kh.mybatis.model.MemberDAO;
import com.kh.mybatis.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public List<MemberDTO> getMemberList(){

        return memberMapper.findAll();
    }

    public int registerMember(MemberDTO dto){
        return memberMapper.insert(dto);
    }

    public int removeMember(int id){
        return memberMapper.remove(id);
    }
}
