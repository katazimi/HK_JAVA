package com.hk.emr.contoller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.emr.command.AddDepartmentCommand;
import com.hk.emr.command.AddUserCommand;
import com.hk.emr.dtos.DepartmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping(value="/") 
	public String home(Model model, HttpSession session) {
		MemberDto loginUser = (MemberDto) session.getAttribute("mdto");
		
		if (loginUser != null) {
	        model.addAttribute("adminName", loginUser.getName()); // DTO의 name 필드
	    } else {
	        model.addAttribute("adminName", "관리자"); // (혹시 모를 예외처리)
	    }
		
		return "admin/main";
	}
	
	@GetMapping("/accounts")
	public String showAccountsPage(Model model) {
	    
	    // 1. 서비스에서 모든 계정 목록을 가져옵니다.
	    // (adminService에 'findAllAccounts' 같은 메서드가 필요합니다.)
	    List<MemberDto> accountList = adminService.findAllAccounts(); 

	    // 2. 템플릿(HTML)으로 목록을 전달합니다.
	    model.addAttribute("accounts", accountList);

	    // 3. 'admin/account.html' 템플릿을 렌더링합니다.
	    return "admin/account"; 
	}
	
	@PostMapping(value="/addUser") 
	@ResponseBody // ◀ HTML(String)이 아닌 JSON(ResponseEntity)을 반환
	public ResponseEntity<?> addUser(@Validated @RequestBody AddUserCommand addUserCommand,
	                                     BindingResult result) {
	    System.out.println("회원가입하기 (AJAX)");

	    if(result.hasErrors()) {
	        System.out.println("회원가입 유효값 오류");
	        // ◀ 실패 시 400 에러와 JSON 메시지 반환
	        return ResponseEntity.badRequest().body(Map.of("message", "입력값에 오류가 있습니다."));
	    }

	    try {
	        adminService.addUser(addUserCommand);
	        System.out.println("회원가입 성공");
	        // ◀ 성공 시 200 OK와 JSON 메시지 반환
	        return ResponseEntity.ok().body(Map.of("message", "계정이 성공적으로 생성되었습니다."));
	    } catch (Exception e) {
	        System.out.println("회원가입실패");
	        e.printStackTrace();
	        // ◀ 서버 에러 시 500 에러와 JSON 메시지 반환
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("message", "서버 오류로 등록에 실패했습니다."));
	    }
	}
	
	@ResponseBody
	@GetMapping(value = "/idChk")
	public Map<String,String> idChk(String id){
		System.out.println("ID중복체크");
		
		String resultId=adminService.idChk(id);
		// json객체로 보내기 위해 Map에 담아서 응답
		// text라면 그냥 String으로 보내도 됨
		Map<String,String>map=new HashMap<>();
		map.put("id", resultId);
		return map;
	}
	
	@GetMapping(value = "/settings") 
	public String settings(Model model) {
		// 1. 서비스에서 모든 계정 목록을 가져옵니다.
	    // (adminService에 'findAllAccounts' 같은 메서드가 필요합니다.)
	    List<DepartmentDto> departmentList = adminService.findAllDepartments(); 

	    // 2. 템플릿(HTML)으로 목록을 전달합니다.
	    model.addAttribute("departmentList", departmentList);
		
		return "admin/settings";
	}
	
	@PostMapping(value = "/departments")
	public ResponseEntity<?> departments(@Validated @RequestBody AddDepartmentCommand addUserCommand,
            BindingResult result) {
		if(result.hasErrors()) {
	        System.out.println("회원가입 유효값 오류");
	        // ◀ 실패 시 400 에러와 JSON 메시지 반환
	        return ResponseEntity.badRequest().body(Map.of("message", "입력값에 오류가 있습니다."));
	    }
		
		try {
	        adminService.addDepartment(addUserCommand);
	        System.out.println("진료과 등록 성공");
	        // ◀ 성공 시 200 OK와 JSON 메시지 반환
	        return ResponseEntity.ok().body(Map.of("message", "계정이 성공적으로 생성되었습니다."));
	    } catch (Exception e) {
	        System.out.println("진료과 등록 실패..");
	        e.printStackTrace();
	        // ◀ 서버 에러 시 500 에러와 JSON 메시지 반환
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("message", "서버 오류로 등록에 실패했습니다."));
	    }
	}
	
	@GetMapping("/schedules") 
	public String schedules(Model model) {
		List<DoctorDto>doctors = adminService.getDoctorList();
		model.addAttribute("doctors", doctors);
		return "admin/schedule";
	}
}
