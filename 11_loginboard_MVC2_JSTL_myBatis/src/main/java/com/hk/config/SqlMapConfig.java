package com.hk.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	//작업을 진행할 객체(SqlSession) 만들어줄 객체
		private SqlSessionFactory sqlSessionFactory;
		
		//SqlSessionFactory를 생성해줄 메서드
		public SqlSessionFactory getSessionFactory() {
			String resource = "sql/Configuration.xml";
			
			try {
				Reader reader = Resources.getResourceAsReader(resource);
				sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
				reader.close();
				System.out.println("SqlSessionFactory 생성 성공!"); // 확인용 로그
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 System.out.println("SqlSessionFactory 생성 실패!"); // 확인용 로그
			} 
			
			return sqlSessionFactory;
		}
}
