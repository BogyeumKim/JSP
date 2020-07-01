<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reg form data</title>
</head>
<body>


<h1>회원가입 처리 페이지</h1>
	<hr>
	<table border="1">
		<ul>
			
			<li> 아이디 : <%= request.getParameter("id") %></li>
		</ul>
		<ul>
			
			<li> 비밀번호 :<%= request.getParameter("pw") %></li>
		</ul>
		<ul>
			
			<li> 이름 :<%= request.getParameter("username") %></li>
		</ul>
		
		<ul>
			
			<li> 사진 :<%= request.getProtocol() %></li>
		</ul>
		
	</table>





</body>
</html>