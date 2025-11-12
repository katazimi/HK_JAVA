package com.hk.emr.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.emr.command.AddPatientCommand;
import com.hk.emr.dtos.AppointmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.dtos.PatientDto;
import com.hk.emr.service.MemberService;
import com.hk.emr.service.StaffService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping(value="/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping(value="/") 
	public String home(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        
        MemberDto loginUser = memberService.getMemberInfo(username);
		
		if (loginUser != null) {
	        model.addAttribute("adminName", loginUser.getName()); // DTO의 name 필드
	    } else {
	        model.addAttribute("adminName", "창구직원"); // (혹시 모를 예외처리)
	    }
		
		List<DoctorDto>doctorList = staffService.getDoctors();
		model.addAttribute("doctorList",doctorList);
		
		List<AppointmentDto>todayList = staffService.getSchedules();
		model.addAttribute("todayList", todayList);
		
		return "staff/main";
	}
	
	@GetMapping(value = "/payment")
	public String payment(Model model) {
		List<AppointmentDto>paymentList = staffService.getPaymentList();
		model.addAttribute("paymentList", paymentList);
		return "staff/payment";
	}
	
	@GetMapping(value = "/patients")
	public String patients(Model model) {
		List<PatientDto>patientList = staffService.getPatients();
		model.addAttribute("patientList",patientList);
		return "staff/patients";
	}
	
	@PostMapping(value = "/patients") 
	public ResponseEntity<?> addPatients(@Validated @RequestBody AddPatientCommand addPatientCommand,
            BindingResult result) {
		System.out.println("환자추가하기 (AJAX)");
		PatientDto dto = new PatientDto();
		
		if(result.hasErrors()) {
			System.out.println("환자추가 유효값 오류");
			// ◀ 실패 시 400 에러와 JSON 메시지 반환
			return ResponseEntity.badRequest().body(Map.of("message", "입력값에 오류가 있습니다."));
		}
		
		try {
		dto.setName(addPatientCommand.getName());
		dto.setPhone(addPatientCommand.getPhone());
		dto.setBirth(addPatientCommand.getBirth());
		
		staffService.addPatient(dto);
		System.out.println("회원가입 성공");
		// ◀ 성공 시 200 OK와 JSON 메시지 반환
		return ResponseEntity.ok().body(Map.of("message", "환자가 성공적으로 등록되었습니다."));
		} catch (Exception e) {
		System.out.println("환자등록실패");
		e.printStackTrace();
		// ◀ 서버 에러 시 500 에러와 JSON 메시지 반환
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    .body(Map.of("message", "서버 오류로 등록에 실패했습니다."));
		}
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<PatientDto> patientSearch(String query) {
		List<PatientDto>list = staffService.patientSearch(query);
		
		return list;
	}
	
	@PostMapping("/appointments/walk-in")
	public ResponseEntity<?> createWalkIn(@Valid @RequestBody AppointmentDto dto, 
            HttpSession session) {
		try {
		// 1. 세션에서 로그인한 직원(STAFF)의 ID를 가져옵니다.
		MemberDto loginStaff = (MemberDto) session.getAttribute("mdto");
		if (loginStaff == null) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
		       .body(Map.of("message", "세션이 만료되었습니다."));
		}
		Integer staffId = loginStaff.getUserId(); // (MemberDto에 userId가 있다고 가정)
		
		// 2. 서비스 로직 호출 (상태: WAITING)
		staffService.createWalkInAppointment(dto, staffId);
		
		// 3. 성공 응답 (JS의 success: 실행)
		return ResponseEntity.ok().body(Map.of("message", "현장 접수 완료"));
		
		} catch (Exception e) {
		// 4. 실패 응답 (JS의 error: 실행)
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		   .body(Map.of("message", "서버 오류: " + e.getMessage()));
		}
	}
	
	@PostMapping("/appointments/schedule")
    public ResponseEntity<?> createScheduled(@Valid @RequestBody AppointmentDto dto, 
                                             HttpSession session) {
        try {
            // 1. 세션에서 로그인한 직원(STAFF)의 ID를 가져옵니다.
            MemberDto loginStaff = (MemberDto) session.getAttribute("mdto");
            if (loginStaff == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                     .body(Map.of("message", "세션이 만료되었습니다."));
            }
            Integer staffId = loginStaff.getUserId();

            // 2. 서비스 로직 호출 (상태: SCHEDULED)
            staffService.createScheduledAppointment(dto, staffId);
            
            // 3. 성공 응답 (JS의 success: 실행)
            return ResponseEntity.ok().body(Map.of("message", "사전 예약 완료"));

        } catch (Exception e) {
            // 4. 실패 응답 (JS의 error: 실행)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "서버 오류: " + e.getMessage()));
        }
    }
	
	@PostMapping("/appointments/{id}/arrive")
    public ResponseEntity<?> checkInPatient(@PathVariable("id") int appointmentId) {
        try {
            // 1. 서비스 로직 호출 (DB 업데이트 + WebSocket 전송)
            staffService.checkInPatient(appointmentId);
            
            // 2. 성공 응답 (JS의 success: 실행)
            return ResponseEntity.ok().body(Map.of("message", "환자 접수 완료"));

        } catch (Exception e) {
            // 3. 실패 응답 (JS의 error: 실행)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "서버 오류: " + e.getMessage()));
        }
    }
	
	@PostMapping("/appointments/{id}/pay")
    public ResponseEntity<?> paymentComplete(@PathVariable("id") int appointmentId) {
        try {
            // 1. 서비스 로직 호출
            staffService.paymentComplete(appointmentId);
            
            // 2. 성공 응답 (JS의 success: 실행)
            return ResponseEntity.ok().body(Map.of("message", "수납 완료"));

        } catch (Exception e) {
            // 3. 실패 응답 (JS의 error: 실행)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "서버 오류: " + e.getMessage()));
        }
    }
}
