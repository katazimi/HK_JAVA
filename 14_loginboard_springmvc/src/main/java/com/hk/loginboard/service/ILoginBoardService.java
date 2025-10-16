package com.hk.loginboard.service;

import java.util.List;

import com.hk.loginboard.dtos.UserDto;

public interface ILoginBoardService {
	public boolean insertUser(UserDto dto);
	//ID 중복체크
	public String idChk(String id);
	//로그인
	public UserDto getLogin(String id, String password);
	//정보가져오기
	public UserDto getUser(String id);
	//정보수정
	public boolean updateUser(UserDto dto);
	//탈퇴
	public boolean delUser(String id);
	
	//1.회원목록 전체 조회[사용회원,탈퇴회원 모두]
	// getAllUserList: select문
	public List<UserDto> getAllUserList();
	
	//2.회원목록 전체 조회[사용중]
	// getUserList: select문
	public List<UserDto> getUserList();
	
	//3.회원상세조회(1명에 대한)
	// getUserRole: select문
	public UserDto getUserRole(String id);
	
	//4.회원등급수정
	// getUpdateRole: update문
	public boolean getUpdateRole(String id, String role);
}
