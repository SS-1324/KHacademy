<%--
	JSP 지시어
	- <%@ ... %> 형태로 작성하며, "실행 코드"가 아니라 페이지 자체의 설정을 지정함.
 --%>

<%-- page 지시어: 응답의 타입/인코딩형식을 지정 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 지시어</h1>
	
	<h3>1) page 지시어로 import한 클래스 사용해보기</h3>
	<p>현재 시각 : <%= new Date() %></p>

	<h3>2) include 지시어로 공통헤더/푸터 포함하기</h3>
	<p>
		include 지시어는 컴파일 시점에 파일 내용을 그대로 이 자리에 붙여넣는 방식
	</p>
	<%@ include file="/common/header.jsp" %>
	<p>이부분은 본문 내용이 들어있습니다 ~~~~~~~~~~</p>
	<%@ include file="/common/footer.jsp" %>
	
	<h3>3) taglib 지시어</h3>
	<p>위에서 taglib을 선언하면, 실제로 c:태그와같은 태그방식의 jsp코드 작성이 가능하다. </p>
</body>
</html>



