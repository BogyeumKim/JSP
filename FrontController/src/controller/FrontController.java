package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class FrontController extends HttpServlet{

	
	//1. http 요청
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//2. 사용자 요청 파악 : request 객체를 이용
		// request.getParameter(name) 이렇게 요청도 가능
		// request.getRequestURL() 이렇게 요청도 가능
		// request.getRequestURI() 이렇게 요청도 가능 ★★★★★★ 얘가 자주쓴다함
		//String type = request.getParameter("type");
		
		String type =null;
		String command = request.getRequestURI();
		System.out.println(command);
		System.out.println(command.indexOf(request.getContextPath()));
		
		
		// /fc/date -> /는 4번째 c까지는 3번째 
		if(command.indexOf(request.getContextPath()) == 0  ) {
			type = command.substring(request.getContextPath().length()); // 3가져옴 컨텍스트패스는 /fc 까지니까
			
		}
		
		
		
		System.out.println("요청 타입 :" +type);
		
		
		// 3. 핵심 처리 : 기능 수행
		Object resultObj = null;
		String page = "/WEB-INF/views/simple_view.jsp";
		
		
		
		
		// /greeting 요청 가져오기 -> 경로는 http://localhost:8080/fc/greeting
		// /date 요청 가져오기 -> 경로는 http://localhost:8080/fc/date
		
		if(type ==null||type.equals("/greeting")) {
			resultObj = "안녕하세요";
			page="/WEB-INF/views/view01.jsp";
		} else if(type.equals("/date")) {
			resultObj = new Date();
			page="/WEB-INF/views/view02.jsp";
			
		} else if(type.equals("/")) {
			page="/WEB-INF/views/index.jsp";
			
		}
		
		
		else {
			resultObj = "Invalid Type";
		}
		System.out.println("핵심처리결과"+resultObj);
		
		// 4. 결과 데이터를 속성에 저장 : view 페이지에 공유(전달)
		request.setAttribute("result", resultObj);
		
		
		// 5. 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
		
		
		
		
		
	}
	
	

}
