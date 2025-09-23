package com.hk.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.board.daos.HkDao;
import com.hk.board.dtos.HkDto;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//command 구현(요청 구분)
		// --> command=boardList	와 같이 파라미터 값을 추가전달해야하는 불편함이 있음
		// getRequestURI(): ?전까지 구해줌
		request.getRequestURI(); //요청 주소를 얻어옴
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		String contextPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		System.out.println(requestURI+"\n"+requestURL+"\n"+contextPath+"\n"+pathInfo);
		
		//command값 구하기 --> "/boardList.board" 추출
		String command = requestURI.substring(contextPath.length());
		System.out.println("요청 command: "+command);
		
		HkDao dao = new HkDao();
		
		if (command.equals("/boardlist.board")) {
			List<HkDto> list = dao.getAllList();
			request.setAttribute("list", list);
			
//			request.getRequestDispatcher("boardlist.jsp").forward(request, response);
			dispatch("boardlist.jsp", request, response);
		} else if (command.equals("/insertboardform.board")) {
			response.sendRedirect("insertboardform.jsp");
		} else if (command.equals("/insertboard.board")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS = dao.insertBoard(new HkDto(id,title,content));
			if (isS) {
				response.sendRedirect("boardlist.board");
			}else{
				response.sendRedirect("boardlist.board");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//		test(request);
	}

	public void test(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
	}
	
	//forward 기능 구현
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}
}
