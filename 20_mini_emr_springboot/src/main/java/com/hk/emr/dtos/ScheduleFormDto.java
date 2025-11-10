package com.hk.emr.dtos;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleFormDto {

	private Integer doctorId;
    private Map<String, ScheduleDto> schedules;
}
