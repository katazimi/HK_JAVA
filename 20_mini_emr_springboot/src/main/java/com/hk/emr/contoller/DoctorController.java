package com.hk.emr.contoller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.emr.dtos.DayScheduleDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@GetMapping(value="/") 
	public String home(Model model, HttpSession session) {
		MemberDto loginUser = (MemberDto) session.getAttribute("mdto");
		
		if (loginUser != null) {
	        model.addAttribute("doctorName", loginUser.getName()); // DTO의 name 필드
	    } else {
	        model.addAttribute("doctorName", "의사"); // (혹시 모를 예외처리)
	    }
		
		return "doctor/main";
	}
	
	@GetMapping(value="/chart")
	public String chartView() {
		return "doctor/chart";
	}
	
	@GetMapping("/schedule")
    public String showMySchedule(
            // URL 파라미터 (e.g., ?date=2025-11-10), 없으면 오늘
            @RequestParam(required = false) String date, 
            HttpSession session, 
            Model model) {
        
        // 1. 세션에서 로그인한 의사 정보 가져오기
        MemberDto loginUser = (MemberDto) session.getAttribute("mdto");
        if (loginUser == null) {
            return "redirect:/user/login"; // 세션 만료
        }
        // (주의: loginUser에 doctorId가 없다면, user_id로 doctor_id를 조회해야 함)
        int doctorId = doctorService.getDoctorId(loginUser.getUserId()); // (로그인 DTO에 doctorId가 있다고 가정)

        // 2. 날짜 파라미터 처리
        LocalDate baseDate;
        if (date != null && !date.isEmpty()) {
            baseDate = LocalDate.parse(date);
        } else {
            baseDate = LocalDate.now(); // 기본값: 오늘
        }

        // 3. 서비스 호출 (7일치 데이터 조립)
        List<DayScheduleDto> weeklySchedule = doctorService.getWeeklySchedule(doctorId, baseDate);
        
        // 4. 네비게이션 URL 및 주차 표시
        LocalDate prevWeek = baseDate.minusWeeks(1);
        LocalDate nextWeek = baseDate.plusWeeks(1);
        
        model.addAttribute("weeklySchedule", weeklySchedule);
        model.addAttribute("prevWeekUrl", "/doctor/schedule?date=" + prevWeek.toString());
        model.addAttribute("nextWeekUrl", "/doctor/schedule?date=" + nextWeek.toString());
        model.addAttribute("weekDisplay", baseDate.format(DateTimeFormatter.ofPattern("YYYY년 MM월")));
        
        return "doctor/schedule";
    }
}
