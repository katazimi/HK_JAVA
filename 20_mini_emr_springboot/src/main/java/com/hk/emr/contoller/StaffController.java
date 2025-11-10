package com.hk.emr.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.emr.command.AddPatientCommand;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.dtos.PatientDto;
import com.hk.emr.service.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;

	@GetMapping(value="/") 
	public String home(Model model, HttpSession session) {
		MemberDto loginUser = (MemberDto) session.getAttribute("mdto");
		
		if (loginUser != null) {
	        model.addAttribute("adminName", loginUser.getName()); // DTO의 name 필드
	    } else {
	        model.addAttribute("adminName", "창구직원"); // (혹시 모를 예외처리)
	    }
		
		return "staff/main";
	}
	
	@GetMapping(value = "/payment")
	public String payment() {
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
}
