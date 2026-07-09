package com.kh.spring.test;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ResponseTestController {
    //응답하는 방식의 정리

    // @ResponseBody
    // 환면(View)없이 결과를 바로 텍스트로 응답하겠다.
    @ResponseBody
    @GetMapping
    public String responseBodyTest(){
        return "텍스트로 응답을 하겠다.";
    }

    // 기본적인 forward방식
    @GetMapping("/index")
    public String responseIndex(){
        // /WEB-INF/views/ 뒤부터 jsp파일의 경로를 작성해주면
        // 그대로 응답은 ViewResolver에서 jsp파일을 매칭시켜 응답해 준다.
        return "test/index";
    }

    // forward방식으로 jsp view에 값을 전달할 때
    // request영역을 사용한다 -> 요청이 끝나는 시점까지만 데이터를 저장해서 사용할 때
    // HttpServletRequest를 직접 사용하지않고 Model객체를 받아서 처리
    @GetMapping("/model-forward")
    public String responseIndex(Model model){

        model.addAttribute("message", "Model에 데이터를 저장함.");

        return "test/modelResult";
    }

    // HttpSession 사용 -> 매개변수로 받아서 바로 사용이 가능
    // Redirect방식 사용시에는 데이터가 request영역에는 보존이 안되므로 session사용
    @GetMapping("/session-login")
    public String sessionLoginTest(HttpSession session){
        //로그인 정보와 같은 여러페이지에서 지속적으로 유지해야하는 데이터는 세션에 저장.
        session.setAttribute("loginUser", "최지원");

        // redirect: 접두사 -> 새로운 요청이 다시 발생함, 주소창의 요청주소도 변경됨
        return "redirect:/test/session-check";
    }

    @GetMapping("/session-check")
    public String sessionCheckTest(HttpSession session, Model model){
        String name = (String) session.getAttribute("loginUser");

        model.addAttribute("message", "세션에 저장된 loginUser 값 : " + name);
        return "test/modelResult";
    }
}
