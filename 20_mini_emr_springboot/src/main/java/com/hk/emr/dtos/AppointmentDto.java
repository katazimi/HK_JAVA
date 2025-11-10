package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias(value="appointmentDto")
@Getter
@Setter
@ToString
public class AppointmentDto {
	private String time;
	private String patientName;
}
