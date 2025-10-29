package com.hk.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	//구현된 interceptor 객체를 등록한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.order(1) //우선순위 설정
				.addPathPatterns("/**")   //전체 요청에 대해 적용
				.excludePathPatterns("/error"
//						            ,"/board/boardList"
//									,"/board/boardDetail"
//									,"/board/mulDel"
//									,"/board/boardUpdate"
//									,"/board/download"
						            ,"/","/user/**","/css/**","/js/**"
						            ,"/img/**","/upload/**");
	
//		registry.addInterceptor(new LoginInterceptor())
//		.order(2)
//		.addPathPatterns("/**")   //전체 요청에 대해 적용
//		.excludePathPatterns("/board","/","/user/**","/css/**","/js/**");
	}
	
	//파일을 불러올때 경로 설정(클라이언트에서 파일을 불러오려면 resources/static 하위에 있어야 함
	//                                  -> 다른경로에 있으면 못불러오기때문에 경로 설정해줌) 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("user.dir:"+System.getProperty("user.dir"));
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:"+System.getProperty("user.dir")+"/upload/");
	}
}





