package com.hk.emr.contoller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UserDetails;

import com.hk.emr.dtos.ChartDto;
import com.hk.emr.dtos.DayScheduleDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.dtos.PatientDto;
import com.hk.emr.service.DoctorService;
import com.hk.emr.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/")
    public String DoctorHome(Authentication authentication, Model model) {
        // 1. 세션에서 로그인한 사용자 정보(mdto) 가져오기
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        
        MemberDto loginUser = memberService.getMemberInfo(username);
        
        if (loginUser == null || !"DOCTOR".equals(loginUser.getRole())) {
            return "redirect:/user/logout"; 
        }

        // 2. 로그인한 user_id로 DOCTOR_PROFILE의 doctor_id를 조회
        Integer doctorId = doctorService.getDoctorId(loginUser.getUserId());

        if (doctorId == null) {
            throw new RuntimeException("의사 프로필 정보를 찾을 수 없습니다. (UserId: " + loginUser.getUserId() + ")");
        }

        // 3. Model에 WebSocket 연결에 필요한 doctorId 추가
        model.addAttribute("doctorId", doctorId); 
        
        // 4. Model에 환영 메시지에 필요한 이름 추가
        model.addAttribute("doctorName", loginUser.getName());

        // 5. (기존) 대시보드 목록 데이터 추가
        model.addAttribute("currentPatient", doctorService.findCurrentPatient(doctorId));
        model.addAttribute("waitingList", doctorService.findWaitingList(doctorId));
        model.addAttribute("scheduledList", doctorService.findScheduledList(doctorId));
        
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
	
	@PostMapping("/appointments/{appointmentId}/start")
    @ResponseBody // ◀ 뷰(HTML)가 아닌 JSON/데이터를 반환
    public ResponseEntity<?> startTreatment(
            @PathVariable("appointmentId") int appointmentId) { // ◀ URL의 {appointmentId} 값을 추출

        try {
            // 1. 서비스 로직 호출
            doctorService.startTreatment(appointmentId);
            
            // 2. 성공 시 (JS의 success: 실행)
            return ResponseEntity.ok().body(Map.of("message", "진료가 시작되었습니다."));

        } catch (Exception e) {
            // 3. 실패 시 (JS의 error: 실행)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "서버 오류가 발생했습니다."));
        }
    }
	
	@PostMapping("/appointments/{appointmentId}/complete")
    @ResponseBody // ◀ 뷰(HTML)가 아닌 JSON/데이터를 반환
    public ResponseEntity<?> completeTreatment(
            @PathVariable("appointmentId") int appointmentId) { // ◀ URL의 {appointmentId} 값을 추출

        try {
            // 1. 서비스 로직 호출
            doctorService.completeTreatment(appointmentId);
            
            // 2. 성공 시 (JS의 success: 실행)
            return ResponseEntity.ok().body(Map.of("message", "진료가 완료되었습니다."));

        } catch (Exception e) {
            // 3. 실패 시 (JS의 error: 실행)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "서버 오류가 발생했습니다."));
        }
    }
	
	@GetMapping("/chart/{patientId}")
    public String showPatientChart(@PathVariable int patientId, 
                                   HttpSession session, Model model) {
        
        // (세션 체크)
        MemberDto loginUser = (MemberDto) session.getAttribute("mdto");
        if (loginUser == null) {
            return "redirect:/user/logout";
        }
        
        // 1. 서비스 호출 (환자 정보, 과거차트, 오늘 진료ID 가져오기)
        Integer doctorId = doctorService.getDoctorId(loginUser.getUserId());
        PatientDto patientInfo = doctorService.getPatientInfo(patientId);
        List<ChartDto> pastCharts = doctorService.findPastChartsByPatient(patientId);
        Integer currentApptId = doctorService.findActiveAppointmentId(patientId, doctorId);
        
        // 2. 모델에 데이터 추가
        model.addAttribute("patient", patientInfo);
        model.addAttribute("pastCharts", pastCharts);
        model.addAttribute("currentAppointmentId", currentApptId);
        
        return "doctor/chart"; // ◀ templates/doctor/chart.html
    }

    @PostMapping("/charts")
    @ResponseBody
    public ResponseEntity<?> saveChartMemo(@RequestBody ChartDto chartDto) {
        try {
            // (chartDto에는 appointmentId와 memo가 들어있음)
            doctorService.saveChartMemo(chartDto);
            return ResponseEntity.ok().body(Map.of("message", "메모 저장 성공"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "메모 저장 실패"));
        }
    }
}
