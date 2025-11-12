package com.hk.emr.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hk.emr.dtos.AppointmentDto;

@Mapper
public interface AppointmentMapper {
	
	List<AppointmentDto> findAppointmentsByDate(
	        @Param("doctorId") int doctorId, 
	        @Param("date") LocalDate date
	);

	int updateAppointmentStatus(int appointmentId, String status);

	Integer findActiveAppointmentId(int patientId, int doctorId);
}
