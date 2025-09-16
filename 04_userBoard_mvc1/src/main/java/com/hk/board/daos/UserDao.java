package com.hk.board.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.datasource.Database;
import com.hk.board.dtos.UserDto;

public class UserDao extends Database{
	//메서드 구현: JDBC 2~6단계 구현
	//회원목록 조회 기능: Select (반환타입: List[DTO,DTO,...])
	public List<UserDto> getAllUser(){
		//회원 정보 목록을 저장할 객체
		List<UserDto>list = new ArrayList<UserDto>();
		
		//DB에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null; //DB연결시 사용할 객체
		PreparedStatement psmt = null; //쿼리 준비 및 실행시 사용할 객체
		ResultSet rs=null; //쿼리 실행 결과를 받을 객체
		
		//실행할 쿼리 준비
		String sql=" SELECT userID, "
				+ "		   name, "
				+ "		   birthYear, "
				+ "		   addr, "
				+ "		   mobile1, "
				+ "		   mobile2, "
				+ "		   height, "
				+ "		   mDate "
				+ " FROM USERTBL ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql); //쿼리를 준비하는 중
			rs=psmt.executeQuery(); //쿼리 결과를 반환
			
			// java: 	문자열을 나타내는 타입은 String -> DB는 VARCHAR나 CHAR를 반환
			// --> DB와 JAVA의 타입자체가 다르므로 변환하는 작업이 필요
			
			while(rs.next()) { //next()는 rs에 값이 있는지 확인: true/false
				UserDto dto = new UserDto();
				//select절에서 작성한 컬럼순서에 맞게 작성해야함
				dto.setUserID(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
				list.add(dto); //마지막에 완성된 DTO를 담아야 한다.
			}
			System.out.println("5단계: 쿼리결과받기 성공");
			//6단계: DB닫기 --> try에서 실패가 나더라도 반드시 닫아야함
		} catch (SQLException e) {
			System.out.println("2~5단계 실패");
			e.printStackTrace();
		} finally {
			close(rs,psmt,conn);
		}
		
		
		return list;
	}
	//회원등록 기능: Insert (반환타입: boolean)
	// 전달받은 파라미터들을 현재 메서드에 전달해야 함
	public boolean insertUser(UserDto dto) {
		int count = 0; //쿼리 성공 여부
		
		//DB에 연결할 정보를 정의
		//DB에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null; //DB연결시 사용할 객체
		PreparedStatement psmt = null; //쿼리 준비 및 실행시 사용할 객체
				
		
		String sql = " INSERT INTO userTbl VALUES(?,?,?,?,?,?,?,SYSDATE()) ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserID());
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getBirthYear());
			psmt.setString(4, dto.getAddr());
			psmt.setString(5, dto.getMobile1());
			psmt.setString(6, dto.getMobile2());
			psmt.setInt(7, dto.getHeight());
			count = psmt.executeUpdate(); //추가,수정,삭제 작업은 excuteUpdate()
								  //반환값: 업데이트된 행의 갯수(int)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 close(psmt,conn);
		}
		return count>0?true:false;
	}
	//회원정보 상세 조회: Select (반환타입: DTO)
	public UserDto getUser(String userID){
		UserDto dto = new UserDto();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="wlgns5002!";
		
		String sql = " SELECT * FROM userTbl WHERE userID=?";
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto.setUserID(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) 
					rs.close();
				if(psmt!=null)
					psmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	//회원정보 수정: Update (반환타입: boolean)
	public boolean updateUser(UserDto dto) {
		int count=0;
		//DB에 연결하고 작업할 준비를 위한 객체 선언
		Connection conn=null; //DB연결시 사용할 객체
		PreparedStatement psmt = null; //쿼리 준비 및 실행시 사용할 객체
				
		//2단계: DB연결(localhost:3306 id, pw)
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="wlgns5002!";
		//String pool 메모리 영역: +연산자로 문자열 더하기
		//						-> 값이 변경될때마다 새롭게 객체가 생성
		String sql = " UPDATE userTbl SET name=?, birthYear=?, addr=?, mobile1=?, mobile2=?, height=? WHERE userID=? ";
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getBirthYear());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getMobile1());
			psmt.setString(5, dto.getMobile2());
			psmt.setInt(6, dto.getHeight());
			psmt.setString(7, dto.getUserID());
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt!=null)
					psmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count>0?true:false;
	}
	
	//회원삭제: Delete (반환타입: boolean)
	public boolean deleteUser(String userID) {
		int count=0;
		Connection conn=null; //DB연결시 사용할 객체
		PreparedStatement psmt = null; //쿼리 준비 및 실행시 사용할 객체
				
		String url="jdbc:mariadb://localhost:3306/hk";
		String user="root";
		String password="wlgns5002!";
		
		String sql = "DELETE FROM userTbl WHERE userID=?";
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt!=null)
					psmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count>0?true:false;
	}
}
