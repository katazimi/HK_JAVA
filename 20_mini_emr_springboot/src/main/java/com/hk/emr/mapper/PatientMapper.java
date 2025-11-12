package com.hk.emr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.PatientDto;

@Mapper
public interface PatientMapper {

	PatientDto getPatientById(int patientId);

}
