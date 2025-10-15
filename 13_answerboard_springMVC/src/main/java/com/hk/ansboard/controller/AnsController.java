package com.hk.ansboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;
import com.hk.board.util.Paging;

@Controller
public class AnsController {
	
	@Autowired
	private IAnsService ansService;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {
		System.out.println("HOME 페이지로 이동");
		return "home";
	}
	
	@RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
	public String boardList(String pnum, Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(pnum==null) {
			pnum=(String)session.getAttribute("pnum");
		}else {
			session.setAttribute("pnum", pnum);
		}
		
		List<AnsDto>list = ansService.getAllList(pnum);
		model.addAttribute("list",list);
		
		//페이지 갯수 구해서 전달
		int pCount = ansService.getPCount();
		model.addAttribute("pCount", pCount);
		
		//페이지에 페이징 처리 기능 추가
		//필요한 값: pcount(페이지 갯수), pnum(요청페이지번호), 페이지 범위
		Map<String,Integer>map=Paging.pagingValue(pCount, pnum, 5);
		model.addAttribute("pMap", map);
		
		return "boardlist";
	}
	
	//글 상세보기
	@RequestMapping(value = "/boarddetail.do", method = RequestMethod.GET)
	public String boarddetail() {
		return "insertboardform";
	}
	
	//글추가 폼이동
	@RequestMapping(value = "/insertboardform.do", method = RequestMethod.GET)
	public String insertBoardForm() {
		return "insertboardform";
	}
	
	//글 추가
	@RequestMapping(value = "/insertboard.do", method = RequestMethod.POST)
	public String insertBoard(AnsDto dto) {
		
		boolean isS=ansService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			return "error";
		}
	}
}
