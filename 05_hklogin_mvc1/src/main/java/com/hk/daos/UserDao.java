package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.datasource.Database;
import com.hk.dtos.RoleStatus;
import com.hk.dtos.UserDto;

// 싱글톤 패턴 : 객체를 한번만 생성해서 사용
public class UserDao extends Database{

	private static UserDao userDao;
	//new로 생성하지 못하게 private로 선언
	private UserDao() {}
	public static UserDao getUserDao() {
		if (userDao==null) {
			userDao = new UserDao();
		} 
		return userDao;
	}
	
	//사용자 기능
	
	//1. 회원가입 기능(endabled:"Y", role:"USER", regdate:SYSDATE())
	//insert문
	public boolean insertUser(UserDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql = "INSERT INTO userinfo VALUES(NULL,?,?,?,?,?,'Y',?,SYSDATE())";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getPassword());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, String.valueOf(RoleStatus.USER));
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null,psmt,conn);
		}
		return count>0?true:false;
	}
	
	//ID 중복체크
	public String idCheck(String id) {
		String resultID = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id FROM userinfo WHERE id=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				resultID = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return resultID; 
	}
	
	//관리자 기능
}
