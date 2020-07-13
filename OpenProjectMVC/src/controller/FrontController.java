package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.NullServiceImpl;
import service.Service;

public class FrontController extends HttpServlet{

	
	private Map<String,Service> commands = new HashMap<>();
	

	//1. http 요청
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		
		// 1. commandService.properties(외부 설정) => Propertise
		// 2. 클래스 정보의 클래스들을 생성 => 인스턴스 생성
		// 3. map 에 사용자 요청 command 와 인스턴스를 저장
		
		
		
		// 1. 외부 설정 파일의 내용을 메모리의 데이터로 이동
		
		Properties prop = new Properties();
		
		FileInputStream fis = null;
		
		// 설정 파일의 웹 경로
		String path ="/WEB-INF/commandService.propertise"; 
		
		// 설정 파을의 시스템 절대경로 
		String configFile = config.getServletContext().getRealPath(path);
		
		
		try {
			// 프로퍼티 객체로 파일을 읽어 온다.
			fis = new FileInputStream(configFile);
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator itr = prop.keySet().iterator();
		
		
		
		while(itr.hasNext()) {
			 // 사용자 요청 URI
			String command = (String) itr.next();
			// 사용자 요청의 처리를 위한 클래스 이름, 정보
			String serviceClassName = prop.getProperty(command);
			
			try {
				
				
				// 인스턴스 생성을 위한 Class 객체 
			Class serviceClass=	Class.forName(serviceClassName);
				
			
				// 인스턴스 생성
			Service service = (Service) serviceClass.newInstance();
				
			commands.put(command, service);
			System.out.println(command + " = " + service );
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
//		while(itr.hasNext()) {
//			String command = (String) itr.next();
//			String serviceClassName = prop.getProperty(command);
//			System.out.println(command + " = " + serviceClassName);
//		}
		
		
		
		
		
		
//		commands.put("/", new IndexServiceImpl());
//		commands.put("/index", new IndexServiceImpl());
//		
//		commands.put("/greeting", new GreetingServiceImpl());
//		commands.put("/date", new DateServiceImpl());
//		commands.put("null", new NullServiceImpl());
		
		
	}



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
		
		
		
		
		//System.out.println("요청 타입 :" +type);
		
		
		// 3. 핵심 처리 : 기능 수행
		//Object resultObj = null;
		//String page = "/WEB-INF/views/simple_view.jsp";
		
		Service service = commands.get(type);
		
		if(service == null) {
			//service = commands.get("null");
			service = new NullServiceImpl();
		}
		
		
		
		// /greeting 요청 가져오기 -> 경로는 http://localhost:8080/fc/greeting
		// /date 요청 가져오기 -> 경로는 http://localhost:8080/fc/date
		
//		if(type.equals("/greeting")) {
//			
//			service = new GreetingServiceImpl();
//			
//			
//		} else if(type.equals("/date")) {
//			
//			service = new DateServiceImpl();
//			
//		} else if(type.equals("/")|| type.equals("/index")) {
//			
//			service = new IndexServiceImpl();
//		}
//		
//		
//		else {
//			service = new NullServiceImpl();
//		}
		
		//System.out.println("핵심처리결과"+resultObj);
		
		
		
		String page = service.getViewPage(request, response);
		
		
		
		
		// 5. 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
		
		
		
		
		
	}
	
	

}
