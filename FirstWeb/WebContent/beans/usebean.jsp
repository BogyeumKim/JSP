<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- id는 식별자, class는 객체생성 , scope는 page request&session&application -->
    <!-- scop 속성은 생성이아니라 가져오는거임 -->
    <jsp:useBean id="memberInfo" class="model.Logininfo" scope="session"/>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	memberInfo.setUid("hot");
memberInfo.setName("뜨거워");
%>

<h1><%= memberInfo %></h1>

</body>
</html>