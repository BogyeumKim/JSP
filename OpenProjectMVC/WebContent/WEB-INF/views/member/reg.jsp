<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>


<link rel="stylesheet" href="<c:url value="/css/default.css"/>">

<style>

</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<div>
		<h1 class="subtitle">회원 가입</h1>
		<hr>
		
		<h3>
		
		<c:if test="${result>0 && member!=null }">
		<div>
			회원가입 완료
		</div>
		${member} <!-- to스트링 -->
		</c:if>
		
		<c:if test="${ not  (result>0 && member!=null) }">
			회원가입 실패
		</c:if>
		
		
		</h3>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>









