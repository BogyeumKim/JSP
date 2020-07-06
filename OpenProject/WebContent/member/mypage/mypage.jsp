
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/include/sessionCheck.jsp" %>
   
    
    MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
    
    if(memberInfo==null){%>
    <script>
    	alert('로그인먼저 하세요');
    	location.href='../member/loginForm.jsp';
    	
    	</script>
    	<%
    }
    %>
    
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/default.css">

<style>

</style>
</head>
<body>


<%@ include file="/include/header.jsp" %>



<div>
<h1>마이페이지</h1>
</div>

<%@ include file="/include/footer.jsp" %>


</body>
</html>