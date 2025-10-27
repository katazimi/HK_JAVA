package com.hk.board.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//spring 환경설정관련 클래스 선언(root-context.xml,servlet-context.xml 등 대체)
@Configuration 
public class WebConfig {
	
	@Bean //메서드 단위 선언 및 등록
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
