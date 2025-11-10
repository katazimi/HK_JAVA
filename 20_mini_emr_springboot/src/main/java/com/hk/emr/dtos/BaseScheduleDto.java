package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias(value="baseScheduleDto")
@Getter
@Setter
@ToString
public class BaseScheduleDto {
	private String startTime; // "09:00"
    private String endTime;   // "18:00"
    private boolean isClosed; // true/false
}
