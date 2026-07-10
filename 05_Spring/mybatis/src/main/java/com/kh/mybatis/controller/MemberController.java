package com.kh.mybatis.controller;

import com.kh.mybatis.model.MemberDTO;
import com.kh.mybatis.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 회원 목록 조회
     * 요청 : GET /member/lsit
     * 파라미터 : x
     * 결과 : 회원목록 페이지 포워딩
     */
    @GetMapping("/list")
    public String list(Model model){
        List<MemberDTO> list = memberService.getMemberList();
        model.addAttribute("memberList", list);

        return "member/list";
    }

    /**
     * 회원 추가
     * 요청 : POST /member/insert
     * 파라미터 : name(String), email(String), age(int)
     * 응답 : 회원목록페이지로 리다이렉트
     */
    @PostMapping("/insert")
    public String insert(@ModelAttribute MemberDTO dto,
                         HttpSession session){
       int result = memberService.registerMember(dto);

       if(result > 0){
           session.setAttribute("message", "회원 추가 성공");
       } else {
           session.setAttribute("message", "회원 추가 실패");
       }
       return "redirect:/member/list";
    }

    //----------------- 뷰 이동을 위한 메서드 ------------
    @GetMapping("/insertForm")
    public String insertForm(){
        return "member/insertForm";
    }
}
