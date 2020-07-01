<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

안녕하세요 컨트롤페이지입니다

<%--
response.sendRedirect("../hello.jsp");
--%>

<jsp:forward page="../hello.jsp"></jsp:forward>
</body>
</html>