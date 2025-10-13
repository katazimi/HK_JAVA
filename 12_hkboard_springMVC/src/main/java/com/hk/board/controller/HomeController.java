package com.hk.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHkService;

@Controller
//@RequestMapping("/board")  --> /board/boardlist.do
public class HomeController {

	@Autowired
	private IHkService hkService;
	
	@RequestMapping(value = "/home.do",method = RequestMethod.GET)
	public String home() {
		
		return "home";// WEB-INF/views/home.jsp로 응답 : ViewResolver가 찾아줌
	}
	
	//URL맵핑작업
	@RequestMapping(value="/boardlist.do",
			        method = RequestMethod.GET)
	public String boardList(HttpServletRequest request,
							Model model) {
		System.out.println("글목록 요청");
		List<HkDto> list=hkService.getAllList();
//		request.setAttribute("list", list);//가능함
		model.addAttribute("list", list);//스프링문법
		
		//viewResolver객체가 찾아 준다.
		return "boardlist";//forward기능으로 실행됨
//		return "redirect:boardlist.do";//redirect방식으로 응답할 경우 문법
	}
	
	//글추가폼 이동
	@RequestMapping(value="/insertboardform.do",
			        method= RequestMethod.GET)
	public String insertBoardForm() {
		return "insertboardform";
	}
	
	//글추가
	@RequestMapping(value="/insertboard.do",
			        method= RequestMethod.POST)
	public String insertBoard(HkDto dto) {
		           //파라미터 id,title,content -> HkDto가 받음
		boolean isS=hkService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			return "redirect:error.jsp";
		}
	}
	
	//글 상세보기
	@RequestMapping(value="/boarddetail.do",
			        method= RequestMethod.GET)
	public String boardDetail(Model model,int seq) {
		                    //int seq로 파라미터 받을 수 있다.
		HkDto dto=hkService.getBoard(seq);
		model.addAttribute("dto", dto);
		return "boarddetail";
	}
	
	//글삭제하기
	@RequestMapping(value = "/muldel.do",
			 method= {RequestMethod.GET,RequestMethod.POST})
	public String mulDel(String[] seq) {
		boolean isS=hkService.mulDel(seq);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			return "redirect:error.jsp";
		}
	}
	
	//글 수정폼이동
	@RequestMapping(value="/boardupdateform.do",
			        method= RequestMethod.GET)
	public String boardUpdateForm(Model model,int seq) {
		                    //int seq로 파라미터 받을 수 있다.
		HkDto dto=hkService.getBoard(seq);
		model.addAttribute("dto", dto);
		return "boardupdateform";
	}
	
	//글수정하기
	@RequestMapping(value="/boardupdate.do",
			        method= RequestMethod.POST)
	public String boardUpdate(HkDto dto) {
		           //파라미터 seq,title,content -> HkDto가 받음
		boolean isS=hkService.updateBoard(dto);
		if(isS) {
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			return "redirect:error.jsp";
		}
	}
}





