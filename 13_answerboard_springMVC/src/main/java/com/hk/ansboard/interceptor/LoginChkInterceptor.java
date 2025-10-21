package com.hk.ansboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//해당 클래스를 인터셉터로 사용하려면, HandlerInterceptor를 구현한다.
public class LoginChkInterceptor implements HandlerInterceptor{
	
	// slf4j는 로그형식을 정의 --> log4j는 실제 그 형식을 출력하는 작업을 수행
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//컨트롤러 실행 전에 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인 상태 확인하기
		HttpSession session=request.getSession(false);
		
		if(session==null||session.getAttribute("ldto")==null) {
			logger.info("로그인 필요함");
//			response.sendRedirect("index.jsp");
//			return false;
		}
		
		return true; //true가 반환되면 컨트롤러로 진입
	}
	
	//컨트롤러 실행 후 DispatcherServlet이 뷰로 보내기 전에 실행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("인터셉터: postHandle실행");
	}
	
	//컨트롤러에서 뷰까지 실행이 완료된 후 호출
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("인터셉터: aftercompletion실행");
	}
	
}
