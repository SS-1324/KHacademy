package com.kh.spring.test;

import com.kh.spring.member.MemberDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Map;

// @Component -> 앞으로 객체는 spring이 생성, 삭제한다. spring이 객체를 만들 클래스들을 모아놓은 목록은 빈펙토리라고한다.
//               spring은 빈펙토리에 등록된 class로만 객체를 생성해서 활용할 수 있다.
//               @Component을 사용하면 클래스를 Been으로 등록해서 spring이 사용할 수 있게 해준다.
// @Controller -> 요청을 받기위한 객체, 기본적으로 @Component기능이 포함
@Controller
@RequestMapping("/test") //(주소맵핑) : 해당 controller의 모든 메서드를 호출시 앞에 붙는 주소
@ResponseBody //해당 contoller의 응답은 화면없이 데이터만 응답하겠다.
public class RequestTestController {

    // spring의 controller에서 요청데이터를 처리하는 방법들

    //기존방법 HttpServletRequest라는 요청객체에서 직접 꺼내서 사용이 가능
    @GetMapping("/servlet-request") // /test/servlet-request의 get요청을 해당 메서드로 처리
    public String servletRequestTest(HttpServletRequest req){
        String userId = req.getParameter("userId");
        int age = Integer.parseInt(req.getParameter("age"));
        return "servletRequest 응답 : " + userId + ", " + age;
    }

    // @RequestParam - 파라미터를 하나씩 받을 때 사용시 spring이 HttpServletRequest객체에서 값을 꺼내서
    //                 매개변수의 인자값으로 전달해준다. 따로 변환할 필요가 없다.
    @GetMapping("/request-param") // /test/request-param의 get요청을 해당 메서드로 처리
    public String requestParameterTest(
            @RequestParam(value = "user_id", defaultValue = "guest") String userId,
            int age){

        return "requestParam 응답 : " + userId + ", " + age;
    }

    /*
        @ModelAttribute
        MemberDTO와같은 객체로 요청파라미터를 받아줄 때
        요청에 전달된 파라미터와 받아주는 객체의 필드면이 매칭되어 전달된다.
        이 때 내부적으로는 setter가 실행됨.
    * */
    @GetMapping("/request-member")
    public String requestModelAttribute(@ModelAttribute MemberDTO member){
        System.out.println(member);
        return "requestParam 응답 : " + member.getId() + ", " + member.getName();
    }

    /*
        @PathVariable - URL 경로에 있는 값을 변수로 받기
        /test/path-variable/{memberId}
        ->  /test/path-variable/3
     */
    @GetMapping("/path-variable/{memberId}")
    public String pathVariableTest(@PathVariable("memberId") int id){
        return "pathVariable 응답 : " + id;
    }

    /*
        개념적으로 dto는 따로 없지만 여러값을 한번에 받고싶다면
        @RequestParam Map으로 한번에 받아줄 수 있음.
     */
    @GetMapping("/query-map") // /query-map?type=title&keyword=Srping
    public String queryMapTest(@RequestParam Map<String, String> params){
        return "queryMap 응답 : " + params;
    }

    /*

    * */

    @GetMapping("/cookie/set")
    public String setCookieTest(HttpServletResponse rep){
        Cookie cookie = new Cookie("userId", "user01");
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        rep.addCookie(cookie);
        return "쿠키 저장 완료";
    }

    @GetMapping("/cookie")
    public String getCookieTest(@CookieValue(value = "userId", defaultValue = "저장id없음") String userId){
        return "쿠키 저장된 id : " + userId;
    }


}
