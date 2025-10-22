package com.hk.board.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CalServiceImp {
	//request객체를 전달받음
	public Map<String, Integer> makeCalendar(HttpServletRequest request) {
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		//달력에 필요한 값을 구하기 위해 calendar객체 필요
		Calendar cal = Calendar.getInstance();
		
		//paramYear등의 값이 null이 아니면 사용자가 원하는 달을 요청
		//이외의 요청은 현재날짜로 처리
		int year = (paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear);
		int month = (paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth);
		
		if (month>12) {
			month=1;
			year++;
		}
		
		if (month<1) {
			month=12;
			year--;
		}
		
		//1. 해당 월의 1일에 대한 요일을 구하기
		//	- 1~7 숫자를 반환: 1=일요일 ~ 7=토요일
		cal.set(year, month-1,1); //월:0~11월 -> -1했음
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		
		//2. 월의 마지막 날 구하기
		int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//달력 구현에 필요한 값을 map에 저장
		Map<String, Integer>map = new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		return map;
	}
	
}
