package com.hk.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("index페이지");
		return "index";
	}
	
	@GetMapping("/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		log.info("calendar페이지");
		//request(요청)객체를 전달하면, 받은쪽에서도 요청처리를 할 수 있음
		Map<String, Integer> calMap = calService.makeCalendar(request);
		model.addAttribute("calMap", calMap);
		return "calboard/calendar";
	}
	
	@GetMapping("/addcalboardform") 
	public String addCalBoardForm(Model model,InsertCalCommand insertCalCommand) {
		log.info("일정추가폼이동");
		model.addAttribute("insertCalCommand",insertCalCommand);
		return "calboard/addcalboardform";
	}
	
	@PostMapping("/addcalboard") 
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
							  BindingResult result ,Model model) {
		//유효값 처리 결과를 받아 에러를 확인
		if(result.hasErrors()) {
			log.info("일정을 모두 입력해야됨");
			return "calboard/addcalboardform";
		}
 		
		
		return "redirect:/schedule/calendar?year="+insertCalCommand.getYear()
												  +"&month="+insertCalCommand.getMonth();
	}
}
