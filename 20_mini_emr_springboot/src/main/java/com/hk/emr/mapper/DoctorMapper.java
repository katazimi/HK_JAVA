package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.DoctorDto;

@Mapper
public interface DoctorMapper {

	public List<DoctorDto> getDoctorList();
	
	public int addDoctor(DoctorDto ddto);

}
