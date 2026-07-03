<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSP방식의 주석--%>    
<!-- html방식의 주석-->
<%--
	이 파일은 JSP(Java Server Page)파일이다.

	<%= request.getAttribute("message") %>
	-> ForwardTestServlet에서 request.setAttribute("message", "forward로 전달된 데이터입니다.");담은 값을
	꺼내서 화면에 보여준다.
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결과 페이지</h1>
	<p>전달받은 message : <%= request.getAttribute("message") == null ? "값 없음" : request.getAttribute("message") %></p>
</body>
</html>