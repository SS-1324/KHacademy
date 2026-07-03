package com.servlet.practice.lifecycle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
	
	서블릿은 tomcat이 3단계로 관리한다.
	1. init() : 이 서블릿에 대한 최초 요청이 들어왔을 때 딱1번 호출.(객체 생성 시점) -> 객체생성 1회(싱글톤)
	2. service() : 요청이 들어올 때마다 호출됨, doGet/doPost등으로 자동 분기해줌.
	3. destory() : 서버가 종료되거나 서블릿이 컨테이너에서 내려갈 떄 딱 1회만 호출됨. -> 객체삭제
 */
@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LifecycleServlet() {
        super();
    }
    
    @Override
	public void init() throws ServletException {
		System.out.println("/lifecycle에 init() 호출됨.");
	}

	@Override
	public void destroy() {
		System.out.println("/lifecycle에 destroy() 호출됨.");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/lifecycle에 doGet() 호출됨");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("생명주기 테스트 - 콘솔을 확인바람");
	}


}
