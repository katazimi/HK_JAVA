package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.MemberDto;

@Mapper
public interface MemberMapper {
	public int addUser(MemberDto dto);
	
	public MemberDto loginUser(String id);
	
	public String idChk(String id);

	public List<MemberDto> findAllAccounts();
	
	public int getUserId(String username);
}
