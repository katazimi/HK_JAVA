package com.hk.hello;

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

/**
 * Servlet Filter implementation class EncodeFilter
 */
//@WebFilter("/EncodeFilter")
@WebFilter(urlPatterns = {"/*","/a","/b","/c"}, 
		   initParams = {@WebInitParam(name="encoding",value="utf-8")
		   })
public class EncodeFilter extends HttpFilter implements Filter {
       
    private String encode;

	public EncodeFilter() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
		encode=fConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//요청했을때 실행할 코드
		System.out.println("요청했을때 코드 실행");
		System.out.println("요청URL: "+((HttpServletRequest)request).getRequestURI());
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		//응답할때 실행하는 코드
		System.out.println("응답했을때 코드 실행");
	}

	public void destroy() {
		
	}


}
