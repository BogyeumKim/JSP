<%@page import="guestbook.service.DeleteMessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	String pw = request.getParameter("pw");
   		int mid = Integer.parseInt(request.getParameter("mid")) ;  /* mid는 int형으로해야하니까 파스인트로변환 */
    	
    	// MessageDeleteService 
    	// -> dao.selectMessage(mid) 아이디가 있는지없는지 확인먼저 , 정보가같은지도 확인
    	// -> dao.deleteMessage() dao에서 삭제처리
    	
    	
    	DeleteMessageService service = DeleteMessageService.getInstance();
    	int resultCnt = service.deleteMessage(mid, pw);
    	
    	request.setAttribute("resultCode", resultCnt);
    	
    	
    %>
    
   <%--   ${resultCode } --%>
<jsp:forward page="deleteMessage_view.jsp"/>