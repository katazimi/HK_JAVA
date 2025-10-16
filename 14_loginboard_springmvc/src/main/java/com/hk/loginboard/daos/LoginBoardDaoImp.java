package com.hk.loginboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.hk.loginboard.dtos.UserDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginBoardDaoImp implements ILoginBoardDao{
	
	private final SqlSessionTemplate sqlSession;
	
	private String namespace = "com.hk.loginboard.dao.";

	@Override
	public boolean insertUser(UserDto dto) {
		int count = sqlSession.insert(namespace+"insertuser",dto);
		return count>0?true:false;
	}

	@Override
	public String idChk(String id) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		return sqlSession.selectOne(namespace+"idChk",map);
	}

	@Override
	public UserDto getLogin(String id, String password) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne(namespace+"login",map);
	}

	@Override
	public UserDto getUser(String id) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		return sqlSession.selectOne(namespace+"getuser",map);
	}

	@Override
	public boolean updateUser(UserDto dto) {
		int count = sqlSession.update(namespace+"updateuser", dto);
		return count>0?true:false;
	}

	@Override
	public boolean delUser(String id) {
		int count = sqlSession.update(namespace+"deluser", id);
		return count>0?true:false;
	}

	@Override
	public List<UserDto> getAllUserList() {
		return sqlSession.selectList(namespace+"getalluserlist");
	}

	@Override
	public List<UserDto> getUserList() {
		return sqlSession.selectList(namespace+"getuserlist");
	}

	@Override
	public UserDto getUserRole(String id) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		return sqlSession.selectOne(namespace+"getuser", map);
	}

	@Override
	public boolean getUpdateRole(String id, String role) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		map.put("role", role);
		int count = sqlSession.update(namespace+"updaterole", map);
		return count>0?true:false;
	}

}
