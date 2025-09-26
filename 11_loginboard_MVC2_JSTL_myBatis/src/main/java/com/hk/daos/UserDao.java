package com.hk.daos;

import org.apache.ibatis.session.SqlSession;
import com.hk.config.SqlMapConfig;
import com.hk.dtos.UserDto;

public class UserDao extends SqlMapConfig{
	private String namespace="com.hk.daos.";
	
	public boolean insertUser(UserDto dto) {
		int count = 0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertuser",dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public String idCheck(String id) {
		String resultID = null;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			resultID = sqlSession.selectOne(namespace+"idcheck",id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return resultID;
	}
	
	public UserDto getLogin(String id, String password) {
		UserDto dto = new UserDto();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			dto = sqlSession.selectOne(namespace+"getlogin",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return dto;
	}
	
	public UserDto getUser(String id) {
		UserDto dto = new UserDto();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			dto = sqlSession.selectOne(namespace+"getuser",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return dto;
	}
	
	public boolean updateUser(UserDto dto) {
		int count=0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			count = sqlSession.update(namespace+"updateuser",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public boolean delUser(String id) {
		int count=0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSessionFactory().openSession(true);
			count = sqlSession.update(namespace+"updateuser",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
}
