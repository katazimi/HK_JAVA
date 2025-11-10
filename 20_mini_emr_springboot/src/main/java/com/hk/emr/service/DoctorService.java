package com.hk.emr.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.emr.dtos.AppointmentDto;
import com.hk.emr.dtos.BaseScheduleDto;
import com.hk.emr.dtos.DayScheduleDto;
import com.hk.emr.mapper.AppointmentMapper;
import com.hk.emr.mapper.DoctorMapper;
import com.hk.emr.mapper.ScheduleMapper;

@Service
public class DoctorService {
	@Autowired
    private ScheduleMapper scheduleMapper;
    
    @Autowired
    private AppointmentMapper appointmentMapper;
    
    @Autowired
    private DoctorMapper doctorMapper;
    
    public int getDoctorId(int userId) {
    	return doctorMapper.getDoctorId(userId);
    }
    
    public List<DayScheduleDto> getWeeklySchedule(int doctorId, LocalDate date) {
        
        List<DayScheduleDto> weeklySchedule = new ArrayList<>();
        
        // 1. 기준 날짜가 포함된 주의 일요일(Sunday)을 찾습니다.
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        
        // 날짜 포맷 (e.g., "11/10")
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");

        // 2. 일요일부터 7일간 반복
        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = startOfWeek.plusDays(i);
            DayScheduleDto dayDto = new DayScheduleDto();

            // 2-1. 날짜 설정 (e.g., "11/10")
            dayDto.setDate(currentDay.format(dateFormatter));
            
            // 2-2. 기본 근무시간 조회 (SCHEDULE 테이블)
            String dayOfWeekName = currentDay.getDayOfWeek().toString(); // "MONDAY", "SUNDAY"
            BaseScheduleDto baseSchedule = scheduleMapper.findBaseSchedule(doctorId, dayOfWeekName);
            
            if (baseSchedule != null) {
                if (baseSchedule.isClosed()) {
                    dayDto.setBaseHours("휴무");
                } else {
                    dayDto.setBaseHours(baseSchedule.getStartTime() + " - " + baseSchedule.getEndTime());
                }
            } else {
                dayDto.setBaseHours("스케줄 미정"); // 기본값이 없는 경우
            }

            // 2-3. 실제 예약 목록 조회 (APPOINTMENT 테이블)
            List<AppointmentDto> appointments = appointmentMapper.findAppointmentsByDate(doctorId, currentDay);
            dayDto.setAppointments(appointments);

            // 3. 리스트에 추가
            weeklySchedule.add(dayDto);
        }
        
        return weeklySchedule;
    }
}
