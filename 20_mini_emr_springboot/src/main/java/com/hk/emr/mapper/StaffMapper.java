package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.AppointmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.PatientDto;

@Mapper
public interface StaffMapper {
	public int addPatient(PatientDto dto);
	
	public int updateChartNum(PatientDto dto);

	public List<PatientDto> getPatients();
	
	public List<PatientDto> patientSearch(String query);

	public List<DoctorDto> getDoctors();

	public void createWalkInAppointment(AppointmentDto dto);

	public void createScheduledAppointment(AppointmentDto dto);

	public List<AppointmentDto> getSchedules();

	public int updateStatus(int appointmentId);

	public AppointmentDto getPatientInfo(int appointmentId);

	public List<AppointmentDto> getPaymentList();

	public void paymentComplete(int appointmentId);
}
