package com.hk.emr.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.emr.command.LoginCommand;

@Controller
public class MemberController {
	
	@GetMapping(value="/member/login") 
	public String login(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		return "member/login";
	}
}
