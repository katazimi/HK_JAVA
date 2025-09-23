package com.hk.board.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
	
	//1단계: 드라이버 로딩
	public Database() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("1단계: 드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계 드라이버 로딩 실패...");
			e.printStackTrace();
		}
	}
	//2단계: DB연결(Connection pool구현: JNDI방식)
	public Connection getConnection() throws SQLException{
		Connection conn = null;
		
		DataSource ds = null;
		try {
			//1. JNDI 초기화작업(이름과 객체로 맵핑관리)
			Context initCtx = new InitialContext();
			// "java:comp/env"에 접근
			// -> Context.xml에 등록한 Resource가 "java:comp/env"안에 등록
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// "jdbc/hk"와 일치하는 이름의 Resource를 찾는다.
			ds = (DataSource) envCtx.lookup("jdbc/hk");
			
			//Connection객체 얻기
			conn=ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//6단계: DB닫기
	public void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계: DB닫기 성공");
		} catch (SQLException e) {
			System.out.println("6단계: DB닫기 실패");
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement psmt, Connection conn) {
		try {
			if (psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계: DB닫기 성공");
		} catch (SQLException e) {
			System.out.println("6단계: DB닫기 실패");
			e.printStackTrace();
		}
	}
}
