package com.hk.emr.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.emr.command.LoginCommand;
import com.hk.emr.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/user")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/login") 
	public String login(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		return "member/login";
	}
	
	@PostMapping(value="/login") 
	public String login(@Validated LoginCommand loginCommand
	           			,BindingResult result
	           			,Model model
	           			,HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "member/login";
		}
		
		String path=memberService.login(loginCommand, request, model);
		
		return path;
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}
}
