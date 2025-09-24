package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.datasource.Database;
import com.hk.dtos.RoleStatus;
import com.hk.dtos.UserDto;
import com.hk.utils.PasswordUtil;

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
			// 솔트 생성 및 비밀번호 해시화
            String salt = PasswordUtil.generateSalt();
            String hashedPassword = PasswordUtil.hashPassword(dto.getPassword(), salt);
            psmt.setString(3, salt + ":" + hashedPassword); // 솔트와 해시를 함께 저장
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
	//로그인
	public UserDto getLogin(String id, String password) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		UserDto dto = new UserDto();
		
		String sql = "SELECT id, password, role  FROM userinfo WHERE id=? AND enabled='Y'";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String storedPassword = rs.getString("password"); // "솔트:해시" 형태
	            
	            // 저장된 비밀번호에서 솔트와 해시 분리
	            String[] parts = storedPassword.split(":");
	            if (parts.length == 2) {
	                String salt = parts[0];
	                String storedHash = parts[1];
	                
	                // 입력된 비밀번호를 같은 솔트로 해시화
	                String inputHash = PasswordUtil.hashPassword(password, salt);
	                
	                if (storedHash.equals(inputHash)) {
	                	dto.setId(id);
	                	dto.setRole(rs.getString("role"));
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
	//정보가져오기
	public UserDto getUser(String id) {
		UserDto dto=new UserDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql ="SELECT seq,id,name,address,email,role,regdate FROM userinfo WHERE id=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
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
	//정보수정
	public boolean updateUser(UserDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql = "UPDATE userinfo SET address=?, email=? WHERE id=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getAddress());
			psmt.setString(2, dto.getEmail());
			psmt.setString(3, dto.getId());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	//탈퇴
	public boolean delUser(String id) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql = "UPDATE userinfo SET enabled='N' WHERE id=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
		
	}
	
	//관리자 기능
}
