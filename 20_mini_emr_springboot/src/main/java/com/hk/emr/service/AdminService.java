package com.hk.emr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hk.emr.command.AddUserCommand;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.MemberMapper;
import com.hk.emr.status.RoleStatus;

@Service
public class AdminService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;// <-- @Bean으로 등록:SecurityConfig클래스에서..
	
	public boolean addUser(AddUserCommand addUserCommand) {
		
		MemberDto mdto=new MemberDto();
		mdto.setUserName(addUserCommand.getId());
		mdto.setName(addUserCommand.getName());
		
		//password암호화하여 저장하자
		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
		
		if (addUserCommand.getRole().equals("DOCTOR")) {
			mdto.setRole(RoleStatus.DOCTOR+"");
		}else if (addUserCommand.getRole().equals("STAFF")) {
			mdto.setRole(RoleStatus.STAFF+"");
		}else if (addUserCommand.getRole().equals("ADMIN")) {
			mdto.setRole(RoleStatus.ADMIN+"");
		}
		
		return memberMapper.addUser(mdto);
	}
	
	public String idChk(String id) {
		return memberMapper.idChk(id);
	}

	public List<MemberDto> findAllAccounts() {
		
		return memberMapper.findAllAccounts();
	}
	
	
}
