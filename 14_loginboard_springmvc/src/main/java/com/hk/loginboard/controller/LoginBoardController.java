package com.hk.loginboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.loginboard.dtos.UserDto;
import com.hk.loginboard.service.ILoginBoardService;

@Controller
public class LoginBoardController {
	
	@Autowired
	private ILoginBoardService loginboardService;
	
	//회원가입폼 이동
	@RequestMapping(value = "/registForm.do", method = RequestMethod.GET)
	public String registForm() {
		return "registForm";
	}
	
	//아이디중복체크
	@RequestMapping(value = "/idChk.do", method = RequestMethod.GET)
	public String idChkForm(String id, Model model) {
		String resultID = loginboardService.idChk(id);
		model.addAttribute("resultID", resultID);
		return "idChkForm";
	}
	
	//회원가입
	@RequestMapping(value = "/insertUser.do", method = RequestMethod.GET)
	public String insertUser(UserDto dto) {
		boolean isS = loginboardService.insertUser(dto);
		
		if (isS) {
			return "redirect:index";
		}else {
			return "redirect:error";
		}
		
	}
	
}
