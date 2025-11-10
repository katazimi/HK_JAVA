package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.PatientDto;

@Mapper
public interface StaffMapper {
	public int addPatient(PatientDto dto);
	
	public int updateChartNum(PatientDto dto);

	public List<PatientDto> getPatients();
}
