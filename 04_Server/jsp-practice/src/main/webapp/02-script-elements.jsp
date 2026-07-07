<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List" %>
    
<%!
	// 선언부
	// 변환된 서블릿 클래스의 필드/메서드 영역
	// 즉, 이부분에 만든 메서드와 변수는 모든 사용자의 요청에 공유된다.
	
	private int callCount = 0;
	private int addNumbers(int a, int b){
		return a + b;
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 스크립트 요소</h1>
	
	<h3>1) 선언부 테스트</h3>
	<%
		//스크립틀릿 - 해당 영역은 자바코드를 사용하는 영역
		callCount++;
	%>
	<p>addNumbers(3, 4) 결과 : <%= addNumbers(3, 4) %></p>
	<p>해당 페이지 누적 호출 횟수 : <%= callCount %></p>
	<%
		// 스크립틀릿에 쓰는 코드는 서블릿의 service() 메서드 내부에 그대로 들어감. -> 요청시마다 실행
		List<String> fruits = new ArrayList<>();
		fruits.add("사과");
		fruits.add("바나나");
		fruits.add("포도");
	%>
	<ul>
		<% for (String fruit : fruits) {%>
		<%-- 스크립틀릿 안에 =를 붙이면 표현식으로 out.print()의 역학을 한다. --%>
		<li><%=fruit %></li>
		<%} %>
	</ul>
	<%-- 해당 주석은 JSP 주석으로 서버에서 처리됨.--%>
	<!-- 해당 주석은 HTML주석으로 브라우저에서 처리됨. -->
</body>
</html>