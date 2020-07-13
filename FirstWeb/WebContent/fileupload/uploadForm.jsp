<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>


	<h1>과제 제출</h1>
	<form action="upload2.jsp" method="post" enctype="multipart/form-data">  <!-- method 포스트, enctype 멀티파트 필수임 (기본) -->
	
	이름 : <input type="text" name="sname"><br>
	학번 : <input type="text" name="sno"><br>
	과제 파일 : <input type="file" name="report"><br>
	<input type="submit" value="제출">	
	
	
	</form>


</body>
</html>JSP