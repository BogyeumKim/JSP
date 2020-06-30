<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

header{
	text-align: center;
}

#wrap{
overflow: hidden;
}

#news{
	width:40%;
	float:left;
}
#shopping{
width:40%;
float:right;
}


</style>
</head>
<body>


	<%@ include file="include/header.jsp" %>
	<div id="wrap">
	<%@ include file="include/news.jsp" %>
	<%@ include file="include/shopping.jsp" %>
	</div>	
	<jsp:forward page="include/footer.jsp">
	<jsp:param value="cool@gmail.com" name="email"/>
	<jsp:param value="asfasf" name="wrqrwr"/>
	</jsp:forward>

</body>
</html>