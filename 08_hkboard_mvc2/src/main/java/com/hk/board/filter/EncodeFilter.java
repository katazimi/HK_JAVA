package com.hk.board.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/*","/a","/b","/c"}, 
initParams = {@WebInitParam(name="encoding",value="utf-8")
})
public class EncodeFilter extends HttpFilter implements Filter {
       
    private String encode;

    //FilterConfig(필터의 환경설정을 관리)
    // 사용법 -> init메서드의 파라미터로 객체를 얻어올 수 있음
	public void init(FilterConfig fConfig) throws ServletException {
		encode=fConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("요청했을때 코드 실행");
		System.out.println("요청URL: "+((HttpServletRequest)request).getRequestURI());
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);

		chain.doFilter(request, response);
		
		//응답할때 실행하는 코드
		System.out.println("응답했을때 코드 실행");
	}

	public void destroy() {
		
	}

}
