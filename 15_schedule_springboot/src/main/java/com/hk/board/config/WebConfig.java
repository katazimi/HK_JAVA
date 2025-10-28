package com.hk.board.config;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//spring 환경설정관련 클래스 선언
//	-(root-context.xml,servlet-context.xml 등 대체)
@Configuration
@EnableTransactionManagement //선언해야 @Transaction을 사용 할 수 있다.
public class WebConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean //메서드 단위 선언 및 등록
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
}
