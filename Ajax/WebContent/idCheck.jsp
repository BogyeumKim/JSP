<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page trimDirectiveWhitespaces="true" %>
<%

	String uid = request.getParameter("uid");

	if(uid != null && uid.equals("test")){
	//	out.print("사용 가능한 아이디 입니다.");
	out.print("Y");
	}else {
		//out.print("사용 불가능한 아이디 입니다.");
		out.print("N");
	}



%>