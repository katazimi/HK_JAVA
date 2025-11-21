package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.AppointmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.ScheduleDto;

@Mapper
public interface DoctorMapper {

	public List<DoctorDto> getDoctorList();
	
	public int addDoctor(DoctorDto ddto);
	
	public int addSchedule(int doctorId);

	public List<ScheduleDto> findSchedulesByDoctorId(int doctorId);
	
	public int updateSchedule(List<ScheduleDto> scheduleList);

	public int getDoctorId(int userId);

	public AppointmentDto findCurrentPatient(Integer doctorId);

	public List<AppointmentDto> findWaitingList(Integer doctorId);

	public List<AppointmentDto> findScheduledList(Integer doctorId);

	public void deleteSchedulesByDoctorId(Integer doctorId);

	public void deleteDoctorProfile(Integer doctorId);
	
	
}
