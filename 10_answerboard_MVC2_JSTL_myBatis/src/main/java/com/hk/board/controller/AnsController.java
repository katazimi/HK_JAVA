package com.hk.board.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.hk.board.util.Paging;
import com.hk.dao.AnsDao;
import com.hk.dto.AnsDto;

//servlet 만들기: HttpServlet을 상속받아야함
@WebServlet("*.board")
public class AnsController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//command 값 구하기
		
		String command = request.getRequestURI().substring(request.getContextPath().length());
		
		System.out.println("요청 Command: "+ command);
		
		AnsDao dao = new AnsDao();
		
		if (command.equals("/boardlist.board")) {
			//페이지 번호 받기
			String pnum=request.getParameter("pnum");
			
			HttpSession session = request.getSession();
			if(pnum==null) {
				pnum=(String)session.getAttribute("pnum");
			}else {
				session.setAttribute("pnum", pnum);
			}
			
			List<AnsDto> list = dao.getAllList(pnum);
			request.setAttribute("list",list);
			
			//페이지 갯수 구해서 전달
			int pCount = dao.getPCount();
			request.setAttribute("pCount", pCount);
			
			//페이지에 페이징 처리 기능 추가
			//필요한 값: pcount(페이지 갯수), pnum(요청페이지번호), 페이지 범위
			Map<String,Integer>map=Paging.pagingValue(pCount, pnum, 5);
			request.setAttribute("pMap", map);
			
			dispatch("boardlist.jsp", request, response);
		} else if (command.equals("/insertboardform.board")) {
			response.sendRedirect("insertboardform.jsp");
		} else if (command.equals("/insertboard.board")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS = dao.insertBoard(new AnsDto(0,id,title,content));
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		} else if (command.equals("/boarddetail.board")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			AnsDto dto = dao.getBoard(seq);
			
//			String review = request.getParameter("review");
//			//글 목록 페이지에서 요청한 경우
//			if(review!=null&&review.equals("y")) {
//				dao.readCount(seq); //조회수 증가
//				response.sendRedirect("boarddetail.board?seq="+seq);
//			}else {
//				request.setAttribute("dto", dto);
//				dispatch("boarddetail.jsp", request, response);
//			}
			HttpSession session = request.getSession();
			
			//sessionScope를 이용해서 처리하는 방법
			Set<Integer> viewBoards = (Set<Integer>)session.getAttribute("viewBoards");
			if (viewBoards == null) {
				viewBoards = new HashSet<Integer>();
			}
			
			if (!viewBoards.contains(seq)) {
				viewBoards.add(seq);
				session.setAttribute("viewBoards", viewBoards);
				dao.readCount(seq); //조회수 증가
				System.out.println(session.getAttribute("viewBoards"));
				response.sendRedirect("boarddetail.board?seq="+seq);
			}else {
				request.setAttribute("dto", dto);
				System.out.println(session.getAttribute("viewBoards"));
				dispatch("boarddetail.jsp", request, response);
			}
		}else if (command.equals("/updateboardform.board")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			AnsDto dto = dao.getBoard(seq);
			
			request.setAttribute("dto", dto);
			dispatch("updateboardform.jsp", request, response);
		}else if (command.equals("/updateboard.board")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS = dao.boardUpdate(seq, title, content);
			
			if(isS) {
				response.sendRedirect("boarddetail.board?seq="+seq);
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if (command.equals("/deleteboard.board")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			boolean isS = dao.boardDelete(seq);
			
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if (command.equals("/muldel.board")) {
			String[]seqs=request.getParameterValues("seq");
			
			boolean isS = dao.mulDel(seqs);
			
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if (command.equals("/replyboard.board")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS=dao.replyBoard(new AnsDto(seq,id,title,content));
			
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//forward 기능 구현
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}
}