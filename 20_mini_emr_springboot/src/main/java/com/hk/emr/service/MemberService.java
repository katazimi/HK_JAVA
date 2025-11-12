package com.hk.emr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public MemberDto getMemberInfo(String username) {
		return memberMapper.loginUser(username);
	}

}
