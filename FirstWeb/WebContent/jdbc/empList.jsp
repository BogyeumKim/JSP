<%@page import="dao.EmpDao"%>
<%@page import="guestbook.jdbc.ConnectionProvider"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Emp"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMP List</title>
<style>
table {
	width: 80%;
}

table td {
	padding: 10px;
	text-align: center;
}
</style>
</head>
<body>



	<%
	
	Connection conn = null;
	/* Statement stmt = null;
	ResultSet rs = null; 

	List<Emp> empList = new ArrayList();
 */
	//DB Connection 정보
	/* String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "scott";
	String pw = "tiger";
 */
	// 2. Connection 생성
	try {
		//conn = DriverManager.getConnection(dbUrl, user, pw);
	conn = ConnectionProvider.getConnection();
	
	
	empList = EmpDao.getInstance().getEmpList(conn);
	
	
	
		/*  3. Statement 생성 */
	/* 	stmt = conn.createStatement();

		String sql = "select * from emp order by ename desc";

		/*  select 의 결과 Resultset 객체에 담는다. */
		rs = stmt.executeQuery(sql);

		/* stmt.executeQuery(sql); */

		/*  결과 출력 */
		while (rs.next()) {
			empList.add(new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getInt("sal"), rs.getString("job")));
		} 

	} catch (SQLException ex) {
		/* 예외 처리 */

	} finally {
		rs.close();
		stmt.close();
		conn.close();
	}

	request.setAttribute("empList", empList);
	%>


	<jsp:forward page="empList_view.jsp"/>

</body>
</html>