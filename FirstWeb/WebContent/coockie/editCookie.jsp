<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Cookie[] cookies = request.getCookies();
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>쿠키정보 수정</h1>
<%

Cookie cookie = new Cookie("uid","hottttttt");
cookie.setPath("/web/index.jsp");


response.addCookie(cookie);

%>

<a href="viewCookie.jsp">쿠키값 확인하기</a>

</body>
</html>