package com.hk.emr.dtos;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias(value="dayScheduleDto")
@Getter
@Setter
@ToString
public class DayScheduleDto {
	private String date;
	private String baseHours;
	List<AppointmentDto> appointments;
}
