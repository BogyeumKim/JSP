<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<li> 아이디 : <%= request.getParameter("id") %></li>
		</ul>
		<ul>
			
			<li> 비밀번호 :<%= request.getParameter("pw") %></li>
		</ul>
</body>
</html>