package com.hk.board.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.command.DeleteCalCommand;
import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
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
							  BindingResult result) {
		//유효값 처리 결과를 받아 에러를 확인
		if(result.hasErrors()) {
			log.info("일정을 모두 입력해야됨");
			return "calboard/addcalboardform";
		}
 		
		calService.insertCalBoard(insertCalCommand);
		
		return "redirect:/schedule/calendar?year="+insertCalCommand.getYear()
												  +"&month="+insertCalCommand.getMonth();
	}
	
	//일정 목록 조회
	@GetMapping("/calboardlist") 
	//					year,month,date --> Map{"year":"2025","month":"10"}
	public String calBoardList(@RequestParam Map<String, String>map,Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(map.get("year")==null) {
			//schedule/calboardlist 요청했을경우
			//세션에 저장된 ymd를 가져와서 쓰겠다.
			map=(Map<String,String>)session.getAttribute("ymd");
		}else {
			//schedule/calboardlist?year=2025&month=10&date=23
			//ymd 파라미터를 전송한 경우 세션에 저장
			session.setAttribute("ymd", map);
		}
		
		String id = "JH";
		//Service객체에 메서드 정의
		List<CalDto>list = calService.calBoardList(id, map); 
		model.addAttribute("list",list);
		//일정목록 페이지에서 유효값처리를 위한 deleteCalcommand를 정의했기 때문에
		//해당 객체를 전달해야 오류가 안남
		model.addAttribute("deleteCalCommand", new DeleteCalCommand());
//		request.setAttribute("list", list);
		
		return "calboard/calboardlist";
	}
	
	@PostMapping("/calmuldel")
	public String calMulDel(@Validated DeleteCalCommand deleteCalCommand, BindingResult result, Model model, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			log.info("여러글 삭제시 오류가 발생");
			
			String id="JH";
			
			HttpSession session = request.getSession();
			Map<String,String>map=(Map<String,String>)session.getAttribute("ymd");
			
			List<CalDto>list=calService.calBoardList(id, map);
			model.addAttribute("list", list);
			return "calboard/calboardlist";
		}
		
		calService.calMulDel(deleteCalCommand.getSeq());
		
		return "redirect:/schedule/calboardlist";
	}
	
	//일정 상세보기: 파라미터 seq전달받음
	@GetMapping("/calboarddetail")
	public String calBoardDetail(UpdateCalCommand updateCalCommand, Model model) {
		CalDto dto = calService.calBoardDetail(updateCalCommand.getSeq());
		
		//dto --> command 값 이동
		updateCalCommand=modelMapper.map(dto, UpdateCalCommand.class);
		updateCalCommand.mdateToYMDHM(dto.getMdate());
		
		model.addAttribute("updateCalCommand", updateCalCommand);
		
		return "calboard/updateboardform";
	}
	
	//수정하기
	@PostMapping("/calboardupdate")
	public String calBoardUpdate(@Validated UpdateCalCommand updateCalCommand, BindingResult result) {
		
		if(result.hasErrors()) {
			log.info("수정오류");
			return "calboard/calboarddetail";
		}
		
		calService.calBoardUpdate(updateCalCommand);
		
		return "redirect:/schedule/calboarddetail?seq="+updateCalCommand.getSeq();
	}
}
