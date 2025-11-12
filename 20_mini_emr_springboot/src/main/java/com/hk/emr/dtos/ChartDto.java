package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias(value="chartDto")
@Getter
@Setter
public class ChartDto {
    // (메모 저장용)
    private int appointmentId;
    private String memo;
    
    // (과거 기록 조회용)
    private String visitDate; // (예: "2025-11-10")
    private String doctorName; 
}