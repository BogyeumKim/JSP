<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="requestInfo" class="model.RequestMemberInfo" scope="page"/>
<jsp:setProperty property="*" name="requestInfo"/>

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
			
			<li> 아이디 :<%= requestInfo.getId() %> <%-- <%= request.getParameter("id") %> --%></li>	
			<li> 비밀번호 :<%= requestInfo.getPw() %><%-- <%= request.getParameter("pw") %> --%></li>
			<li> 이름 :<%= requestInfo.getUname() %><%-- <%= request.getParameter("uname") %> --%></li>
			<li> 사진 :<%-- <%= request.getProtocol() %> --%></li>
		</ul>
		
	</table>





</body>
</html>