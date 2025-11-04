package com.hk.emr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hk.emr.command.LoginCommand;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.MemberMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;// <-- @Bean으로 등록:SecurityConfig클래스에서..
	
	public String login(LoginCommand loginCommand
	           ,HttpServletRequest request
	           ,Model model) {
		MemberDto dto = memberMapper.loginUser(loginCommand.getId());
		String path="home";
		if(dto!=null) {
			//로그인 폼에서 입력받은 패스워드값과 DB에 암호화된 패스워드 비교
			if(passwordEncoder.matches(loginCommand.getPassword()
					                  , dto.getPassword())) {
				System.out.println("패스워드 같음: 회원이 맞음");
				//session객체에 로그인 정보 저장
				request.getSession().setAttribute("mdto", dto);
				if (dto.getRole().equals("ADMIN")) {
					return path="redirect:/admin/";
				}else if(dto.getRole().equals("DOCTOR")) {
					return path="redirect:/doctor/";
				}else if(dto.getRole().equals("STAFF")) {
					return path="redirect:/staff/";
				}
					
			}else {
				System.out.println("패스워드 틀림");
				model.addAttribute("msg", "패스워드를 확인하세요");
				path="member/login";
			}
		}else {
			System.out.println("회원이 아닙니다. ");
			model.addAttribute("msg", "아이디를 확인하세요");
			path="member/login";
		}
		
		return path;
	}
}
