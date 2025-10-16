package com.hk.loginboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.loginboard.daos.ILoginBoardDao;
import com.hk.loginboard.dtos.UserDto;

@Service
public class LoginBoardServiceImp implements ILoginBoardService{

	@Autowired
	private ILoginBoardDao dao;
	
	@Override
	public boolean insertUser(UserDto dto) {
		return dao.insertUser(dto);
	}

	@Override
	public String idChk(String id) {
		return dao.idChk(id);
	}

	@Override
	public UserDto getLogin(String id, String password) {
		return dao.getLogin(id, password);
	}

	@Override
	public UserDto getUser(String id) {
		return dao.getUser(id);
	}

	@Override
	public boolean updateUser(UserDto dto) {
		return dao.updateUser(dto);
	}

	@Override
	public boolean delUser(String id) {
		return dao.delUser(id);
	}

	@Override
	public List<UserDto> getAllUserList() {
		return dao.getAllUserList();
	}

	@Override
	public List<UserDto> getUserList() {
		return dao.getUserList();
	}

	@Override
	public UserDto getUserRole(String id) {
		return dao.getUserRole(id);
	}

	@Override
	public boolean getUpdateRole(String id, String role) {
		return dao.getUpdateRole(id, role);
	}

}
