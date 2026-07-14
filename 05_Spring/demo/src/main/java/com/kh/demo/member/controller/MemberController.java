package com.kh.demo.member.controller;

import com.kh.demo.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
*   @Controller - 이 클래스는 요청을 받아서 화면(view)를 반환하는 mvc의 컨트롤다. + @Component
*   내부의 메서드가 String을 반환하면 spring.mvc.view.prefix=/WEB-INF/views/ + 반환값 + spring.mvc.view.suffix=.jsp 으로 조합해서
*   해당 jsp파일 찾아 랜더링한다. (return "member/login" -> /WEB-INF/views/member/login.jsp)
*
*   회원관련 화면이동, 폼처리를 전부 해당 컨트롤러가 담당.
* */

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String joinForm(){return "member/join";}

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto,
                       @RequestParam(required = false) MultipartFile profile){
        System.out.println(memberDto);
        System.out.println(profile);
        return null;
    }
}
