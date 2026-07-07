<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
	jsp:include : <%@ include %> 지시어와 동일한 역할을 하지만 동작 시점이 다름.
				  include 지시어는 컴파일 시점에 코드가 합처지는 방식이고(정적),
				  액션태그로 include시에는 요청이 들어올 때마다 다른페이지를 실행한 것과 같은 결과를 낼 수 있음(동적)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 액션태그</h1>
	
	<h3>1) jsp:include (동적 include)</h3>
	<jsp:include page="/common/header.jsp"/>
	<p>위아래 header/footer는 동적으로 포함</p>
	<jsp:include page="/common/footer.jsp" />
	
	<h3>2) jsp:forward</h3>
	<p>
		<a href="05-action-tags.jsp?goForward=true">여기를 클릭시 02-script-elements.jsp로 forward됨.</a>
	</p>
	<%
		String goForward = request.getParameter("goForward");
		if("true".equals(goForward)){
	%>
	<jsp:forward page="/02-script-elements.jsp" />
	<% } %>
</body>
</html>