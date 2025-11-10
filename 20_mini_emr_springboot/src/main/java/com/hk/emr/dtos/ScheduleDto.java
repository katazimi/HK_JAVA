package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("scheduleDto")
@Getter
@Setter
@ToString
public class ScheduleDto {
	private int doctorId; 
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Integer isClosed;
}
