package com.kh.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.kh.mvc.model.member.MemberDAO;
import com.kh.mvc.model.member.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 	[Controller] 회원목록조회
 	- 요청을 받아서 무엇을 응답할지 데이터를 준비하고 전달하는 계층
 	
 	흐름 : 브라우저 요청 -> Contoller(Servlet) -> Model(DAO) -> 결과를 request에 담기
 		  -> view(list.jsp)로 forward -> 화면에 보임
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private MemberDAO memberDAO = new MemberDAO();
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<MemberDTO> list = memberDAO.findAll(); // model레이어를 통해 실제 member list를 조회
			request.setAttribute("memberList", list); // view에 넘길 데이터 담기
			
			// WEB-INF안의 View는 브라우저가 URL로 직접 못들어옴 -> 반드시 Controller를 거처야만 접근 가능.
			request.getRequestDispatcher("/WEB-INF/views/member/list.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
