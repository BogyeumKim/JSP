<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




	<%-- ${empList} --%>

	<table border=1>

		<tr>

			<td>사원 번호</td>
			<td>사원 이름</td>
			<td>사원 급여</td>
			<td>사원 직급</td>

		</tr>
		
	<c:if test="${empty empList }" var="emp">
	<tr>
	<td>데이터가 존재하지 않습니다.</td>
	</tr>
	
	</c:if>
	
			<c:if test="${not empty empList }">
		<c:forEach items="${empList}" var="emp">
		
			<tr>
				<td>${emp.empno}</td>
				<td>${emp.ename}</td>
				<td>${emp.sal}</td>
				<td>${emp.job}</td>
			<tr>
		</c:forEach>
		</c:if>
	</table>
</body>
</html>