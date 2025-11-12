package com.hk.emr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.AdminDashBoardDto;

@Mapper
public interface AdminMapper {
	
	AdminDashBoardDto getDashboardStats();
}
