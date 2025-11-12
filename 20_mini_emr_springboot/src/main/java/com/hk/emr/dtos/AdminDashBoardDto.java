package com.hk.emr.dtos;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias(value = "adminDashBoardDto")
@Getter
@Setter
public class AdminDashBoardDto {

    private int totalAppointments;
    private int waitingCount;
    private int paymentWaitingCount;
    private int totalDoctors;
    private int totalStaff;
}