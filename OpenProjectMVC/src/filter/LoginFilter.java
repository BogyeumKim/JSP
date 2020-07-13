package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//1. 현재 세션 객체를 확인
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		// 현재 세션의 객체
		HttpSession session = httpRequest.getSession(false); // true : 세션없으면 자동으로생성 / false : 세션있으면 반환, 없으면 널값반환 ! 
		
		// 로그인 유무 확인 하는 변수
		boolean login = false;
		
		if(session != null) {
			
			if(session.getAttribute("memberInfo") != null) {
			login = true;
			}
		}
		
		if(login) {
			
			chain.doFilter(request, response);
			
		}else {
			
			
			
			// 포워딩할 페이지 경로
			//String path = "/member/sessionloginForm.jsp";
			//RequestDispatcher dispatcher = request.getRequestDispatcher(path); // 메서드가 대신 객체를생성해서 반환처리해줌
			//dispatcher.forward(request, response); // 보내줌
			
			
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			
			String location =httpRequest.getContextPath()+"/member/sessionloginForm.jsp"; // 상대경로 지정해주기
			
			httpResponse.sendRedirect(location);
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override //초기화
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	
	@Override //소멸시
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	
}
