package com.hk.emr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.emr.dtos.ChartDto;

@Mapper
public interface ChartMapper {

	List<ChartDto> findPastCharts(int patientId);

	void insertChartMemo(ChartDto chartDto);

}
