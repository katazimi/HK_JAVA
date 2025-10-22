package com.hk.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/schedule")
public class CalController {
	// boardlist.do, boarddetail.do...
	// board/boardlist.do, board/boarddetail.do
	// board/* --> 로그인 확인
	
	@Autowired
	private CalServiceImp calService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/calendar")
	public String calendar(HttpServletRequest request) {
		//request(요청)객체를 전달하면, 받은쪽에서도 요청처리를 할 수 있음
		Map<String, Integer> calMap = calService.makeCalendar(request);
		request.setAttribute("calMap", calMap);
		return "calboard/calendar";
	}
}
