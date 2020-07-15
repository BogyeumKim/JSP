<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/default.css">


<style>
img{
width: 40px;
height: 40px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div>
	<h1 class="subtitle">회원 리스트</h1>
	<c:if test="${not empty listview }">
	
	<div>전체회원 ${listview.memberTotalCount }명</div>
	<hr>
	
	<table class="table">
		<tr>
			<th>no</th>
			<th>아이디</th>
			<th>이름</th>
			<th>비밀번호</th>
			<th>사전</th>
			<th>관리</th>
		</tr>
	
	<c:if test="${not empty listview.memberList}">
		<c:forEach items="${listview.memberList}" var="member">
		<tr>
			<td>${member.idx}</td>
			<td>${member.uid}</td>
			<td>${member.upw}</td>
			<td>${member.uname}</td>
			<td><img alt="없음 " src="<c:url value="${member.uphoto}"/>">  </td>
			
		</tr>
	</c:forEach>
	
	</c:if>
	
	<c:if test="${empty listview.memberList }">
			<tr>
				<th>조회된 회원이 없습니다.</th>
			</tr>
		</c:if>
	
	</table>
	
	<div class="paging">
	<c:forEach begin="1" end="${listview.pageTotalCount }" var="i">
	
	<a class="pagin_num ${i == listview.currentPageNumber ? 'now_page' : '' }" href="memberList.do?page=${i}" >${i}</a>
	
	</c:forEach>
		
	</div>
	
	
	
	
	
	</c:if>


</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>