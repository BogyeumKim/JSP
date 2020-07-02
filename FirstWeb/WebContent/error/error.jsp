<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>

<h1>에러가 발생했습니다.</h1>
	<h3>
	에러 타입: <%= exception.getClass().getName() %> <br>
	에러 메시지: <b><%= exception.getMessage() %></br>
	<%= exception.getStackTrace() %>
	</h3>
<a href="index.jsp">홈으로가기</a>

</body>
</html>