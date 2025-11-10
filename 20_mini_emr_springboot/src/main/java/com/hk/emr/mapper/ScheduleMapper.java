package com.hk.emr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hk.emr.dtos.BaseScheduleDto;

@Mapper
public interface ScheduleMapper {
	BaseScheduleDto findBaseSchedule(
	        @Param("doctorId") int doctorId, 
	        @Param("dayOfWeek") String dayOfWeek
	    );
}
