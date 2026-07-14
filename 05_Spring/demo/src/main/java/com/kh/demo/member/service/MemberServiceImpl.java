package com.kh.demo.member.service;

import com.kh.demo.common.util.FileUploadUtil;
import com.kh.demo.common.util.SavedFile;
import com.kh.demo.member.dto.MemberDto;
import com.kh.demo.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Value("${file.upload-dir.profile}")
    private String profileUploadDir;

    @Autowired
    private MemberMapper memberMapper;

    //아이디가 중복인지?

    @Override
    public void join(MemberDto memberDto, MultipartFile profileImage) throws IOException {
        // 아이디 중복검사

        //비밀번호는 항상 암호화해서 저장.
        String encodePwd = passwordEncoder.encode(memberDto.getMemberPwd());
        memberDto.setMemberPwd(encodePwd);

        //프로필 이미지를 업로드 했다면 디스크에 저장 후, 경로를 dto에 채워준다.
        SavedFile saved = fileUploadUtil.save(profileImage, profileUploadDir, "/uploads/profile");
        if(saved != null){
            memberDto.setProfile(saved.getPath());
        }

        memberMapper.insertMember(memberDto);
    }
}
