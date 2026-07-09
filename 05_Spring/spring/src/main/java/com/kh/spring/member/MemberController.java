package com.kh.spring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

/*
   @Controller :  @Component + Conroller계층의 기능이 추가된 어노테이션
                  이 클래스의 메서드가 반환하는 문자열은 View의 이름으로 해석됨.

   @RequestMapping("/member") : 클래스 레벨에서 공통 URL을 지정
                                -> 내부에 메서드들은 맵핑 URL앞에 자동으로 "/member"가 붙음
* */

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    /*
        회원목록 조회
        요청 : GET /member/list
    * */
    @GetMapping("/list")
    public String list(Model model){
        //회원목록을 조회 후 request영역에 담아서 반환

        //요청과 응답은 controller에서 진행하지만
        //비즈니스 로직(실제 기능에대한 코드)는 service에서 진행
        List<MemberDTO> list = memberService.getMemberList();
        model.addAttribute("memberList", list);
        return "member/list";
    }

    /*
        회원 등록 처리
        접속 : POST /member/insert
        파라미터 : int age, String email, String name => MemberDTO 한번에 받을 수 있음
    * */
    @PostMapping("/insert")
    public String insert(@ModelAttribute MemberDTO dto){
        memberService.insertMember(dto);

        // member/list로 fowrad시에는 url은 insert고 화면은 list가 된다.
        // 새로고침시 다시 insert가 되는 문제가 발생할 수 있기 때문에
        // url과 화면을 맞춰 /member/list redirect해준다.
        return "redirect:/member/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        memberService.deleteMember(id);
        return "redirect:/member/list";
    }
}
