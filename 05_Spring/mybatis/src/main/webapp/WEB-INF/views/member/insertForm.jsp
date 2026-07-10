<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP File 기본구조</title>
</head>
<body>
    <h1>회원가입</h1>

    <form action="/member/insert" method="post">
        이름 : <input type="text" name="name" required> <br>
        이메일 : <input type="text" name="email" required> <br>
        나이 : <input type="text" name="age" required> <br>
        <button type="submit">등록</button>
    </form>
</body>
</html>