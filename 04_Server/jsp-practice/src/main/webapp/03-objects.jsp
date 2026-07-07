<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSP 내장 객체
	
	request, response, session, application, out, pageContext, config, exception등..
	JSP에서 별도로 선언하지 않아도 자동으로 사용 가능한 내장객체.

 --%>
 
<%
	// request 내장 객체
	String name = request.getParameter("name");
	if(name == null || name.isEmpty()){
		name = "게스트";
	}
	
	// session 내장 객체
	// 세션에 로그인 사용자 이름을 저장
	// session.setAttribute("loginUser", name);
	
	// application 내장객체
	// 애플리케이션단으로 저장이되므로, 모든 사용자가 공유하는 영역
	Integer visitCount = (Integer)application.getAttribute("visitCount");
	
	if(visitCount == null){
		visitCount = 0;
	}
	visitCount++;
	application.setAttribute("visitCount", visitCount);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 내장 객체</h1>
	
	<h3>request - 요청 파라미터</h3>
	<p>request.getParameter("name") 값 : <%=name %></p>
	<%-- 1번의 요청마다 새로운 request객체가 생기므로 1번의 요청이 끝날때까지 값이 보존된다. --%>
	
	<h3>session - 사용자별 분리된 저장소</h3>
	<p>session에 저장된 loginUser : <%=session.getAttribute("loginUser") %></p>
	<%-- 브라우저 접속시 쿠키에 세션ID가 저장되고 브라우저를 닫거나 세션을 만료할 때 까지 해당 ID를 통해 세션을 사용할 수 있다. --%>
	
	<h3>application - 모든 사용자가 공유하는 저장소</h3>
	<p>총 방문자 수 : <%=visitCount %></p>
	
	<h3>pageContext</h3>
	<p>현재 페이지의 프로젝트path : ${pageContext.request.contextPath}</p>
</body>
</html>




