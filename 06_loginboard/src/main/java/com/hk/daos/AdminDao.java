package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.Database;
import com.hk.dtos.UserDto;

public class AdminDao extends Database{
	private static AdminDao adminDao;
	private AdminDao() {}
	
	public static AdminDao getAdminDao() {
		if (adminDao==null) {
			adminDao = new AdminDao();
		} 
		return adminDao;
	}

	//1.회원목록 전체 조회[사용회원,탈퇴회원 모두]
	// getAllUserList: select문
	public List<UserDto> getAllUserList() {
		List<UserDto> list = new ArrayList<UserDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TID,TNAME,TADDRESS,TPHONE,TEMAIL,TROLE,REGDATE FROM T_USER";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			while (rs.next()) {
				UserDto dto = new UserDto();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAddress(rs.getString(3));
				dto.setPhone(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}
	//2.회원목록 전체 조회[사용중]
	// getUserList: select문
	public List<UserDto> getUserList() {
		List<UserDto> list = new ArrayList<UserDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TID,TNAME,TADDRESS,TPHONE,TEMAIL,TROLE,REGDATE FROM T_USER WHERE enabled='Y'";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			while (rs.next()) {
				UserDto dto = new UserDto();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAddress(rs.getString(3));
				dto.setPhone(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}
	//3.회원상세조회(1명에 대한)
	// getUserRole: select문
	public UserDto getUserRole(String id) {
		UserDto dto = new UserDto();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT seq,id,name,address,email,role,regdate FROM userinfo WHERE enabled='Y'";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			
			while (rs.next()) {
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return dto;
	}
	//4.회원등급수정
	// getUpdateRole: update문
	public boolean getUpdateRole(String id, String role) {
		int count=0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "UPDATE T_USER SET TROLE=? WHERE TID=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, role);
			psmt.setString(2, id);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
}
