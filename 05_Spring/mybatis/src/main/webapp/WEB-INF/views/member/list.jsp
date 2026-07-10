<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP File 기본구조</title>
</head>
<body>
    <c:if test="${message != null}">
        <script>
            alert("${message}")
        </script>

        <c:remove var="message" />
    </c:if>
    <h1>회원목록</h1>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>이메일</th>
                <th>나이</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${memberList}">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.name}</td>
                    <td>${m.email}</td>
                    <td>${m.age}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>