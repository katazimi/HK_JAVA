package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.DepartmentDto;

@Mapper
public interface DepartmentMapper {
	public boolean addDepartment(String name);
	
	public List<DepartmentDto> findAllDepartments();
}
