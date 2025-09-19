package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.datasource.Database;
import com.hk.dtos.RoleStatus;
import com.hk.dtos.UserDto;
import com.hk.utils.Hashutil;

public class UserDao extends Database{
	private UserDao() {}
	
	private static UserDao userDao;
	
	public static UserDao getUserDao() {
		if (userDao==null) {
			userDao = new UserDao();
		} 
		return userDao;
	}
	
	public boolean insertUser(UserDto dto) {
		int count=0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "INSERT INTO T_USER VALUES(?,?,?,?,?,?,'Y',?,SYSDATE())";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			// 솔트 생성 및 비밀번호 해시화
            String salt = Hashutil.generateSalt();
            String hashedPassword = Hashutil.hashPassword(dto.getPassword(), salt);
            psmt.setString(2, salt + ":" + hashedPassword); // 솔트와 해시를 함께 저장
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getPhone());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, String.valueOf(RoleStatus.USER));
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	public String idCheck(String id) {
		String resultID = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TID FROM userinfo WHERE TID=?";
		
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
	public UserDto getLogin(String id, String password) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		UserDto dto = new UserDto();
		
		String sql = "SELECT TID, TPASSWORD, TROLE  FROM T_USER WHERE TID=? AND TENABLED='Y'";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String storedPassword = rs.getString("TPASSWORD"); // "솔트:해시" 형태
	            
	            // 저장된 비밀번호에서 솔트와 해시 분리
	            String[] parts = storedPassword.split(":");
	            if (parts.length == 2) {
	                String salt = parts[0];
	                String storedHash = parts[1];
	                
	                // 입력된 비밀번호를 같은 솔트로 해시화
	                String inputHash = Hashutil.hashPassword(password, salt);
	                
	                if (storedHash.equals(inputHash)) {
	                	dto.setId(id);
	                	dto.setRole(rs.getString("TROLE"));
	                }
	            }
	            
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, psmt, conn);
		}
		
		return dto; 
	}
	
	public UserDto getUser(String id) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		UserDto dto = new UserDto();
		
		String sql = "SELECT TID,TNAME,TADDRESS,TPHONE,TEMAIL,TROLE,REGDATE FROM T_USER WHERE TID=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAddress(rs.getString(3));
				dto.setPhone(rs.getString(4));
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
	public boolean updateUser(UserDto dto) {
		int count =0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql = "UPDATE T_USER SET TNAME=?,TPASSWORD=?,TADDRESS=?,TPHONE=?,TEMAIL=? WHERE TID=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			// 솔트 생성 및 비밀번호 해시화
            String salt = Hashutil.generateSalt();
            String hashedPassword = Hashutil.hashPassword(dto.getPassword(), salt);
            psmt.setString(2, salt + ":" + hashedPassword); // 솔트와 해시를 함께 저장
			psmt.setString(3, dto.getAddress());
			psmt.setString(4, dto.getPhone());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getId());
			System.out.println(sql);
			count = psmt.executeUpdate();
			System.out.println(dto.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
}
