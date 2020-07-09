<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

    <%
    List<Member> members= new ArrayList();
    members.add(new Member("손흥민1","son1@gmail.com"));
    members.add(new Member("손흥민2","son1@gmail.com"));
    members.add(new Member("손흥민3",null));
    members.add(new Member("손흥민4","son1@gmail.com"));
    members.add(new Member("손흥민5","son1@gmail.com"));
    members.add(new Member("손흥민6","son1@gmail.com"));
    members.add(new Member("손흥민7","son1@gmail.com"));
    members.add(new Member("손흥민8",null));
    
    application.setAttribute("members", members);
    %>
    
    데이터생성