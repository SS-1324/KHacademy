package com.kh.mybatis.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/*
    세션에 loginUser가 없으면 회원목록 확인 막기

    Interceptor는 DispatcherServlet 이후에 실행됨. SrpingMVC 내부에서 동작.
    DispatcherServlet이 HandlerMapping을 통해서 요청을 처리할 Controller를 이미 찾아낸 뒤에 실행됨.
    Interceptor는 어떤 Contoller 메서드가 실행될지 알 수 있고, Model, View에도 관여 가능.

    HandlerInterceptor의 3개의 시점
    - preHandle() : Controller 메서드 실행 전에 실행되는 메서드
    - postHandle() : Controller 메서드 실행 후에 실행되는 메서드
    - afterConpletion() : View렌더링까지 모두 끝난 후에 실행되는 메서드
 */

public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // handler : 이 요청을 처리할 Controller의 메서드 정보를 담고있음

        HttpSession session = request.getSession();
        boolean isLogin = session != null && session.getAttribute("loginUser") != null;

        if(!isLogin){
            System.out.println("[interceptor] 로그인 정보 없음 -> 접근 차단, 회원가입으로 forward");
            request.setAttribute("errorMsg", "로그인 사용자만 접근할 수 있는 경로, 먼저 /member/login 로그인하세요.");
            request.getRequestDispatcher("/WEB-INF/views/common/loginAccessDenied.jsp").forward(request, response);
            return false;// 메서드 응답을 false로 하면 여기서 요청을 끝내고 controller실행 안됨.
        }

        return true; // 메서드 응답을 true로하면 그대로 controller로 진입을 함.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
