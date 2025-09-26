package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.AdminDao;
import com.hk.dtos.UserDto;


@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//command 구현(요청 구분)
		// --> command=boardList	와 같이 파라미터 값을 추가전달해야하는 불편함이 있음
		// getRequestURI(): ?전까지 구해줌
//		request.getRequestURI(); //요청 주소를 얻어옴
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		String contextPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		System.out.println(requestURI+"\n"+requestURL+"\n"+contextPath+"\n"+pathInfo);
		
		//command값 구하기 --> "/boardList.board" 추출
		String command = requestURI.substring(contextPath.length());
		System.out.println("요청 command: "+command);
		
		//세션값 가져오기
		HttpSession session = request.getSession();
		
		AdminDao dao = AdminDao.getAdminDao();
		
		if (command.equals("/userlistall.admin")) {
			List<UserDto> list = dao.getAllUserList();
			request.setAttribute("list", list);
			dispatch("admin_listAll.jsp",request,response);
		}else if(command.equals("/userlist.admin")) {
			List<UserDto> list = dao.getAllUserList();
			request.setAttribute("list", list);
			//이동한다.
			dispatch("admin_list.jsp",request,response);
		}else if(command.equals("/updateRole.admin")) {
			String id = request.getParameter("id");
			String role = request.getParameter("role");
			boolean isS = dao.getUpdateRole(id, role);
			
			if (isS) {
				jsResponse(response, "수정완료!", "userlist.admin");
			}else {
				jsResponse(response, "수정실패!", "error.jsp");
			}
		}
	}

	//forward 기능 구현
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void jsResponse(HttpServletResponse response,String msg, String url) throws IOException {
		PrintWriter out = response.getWriter();
		String js = 
		"<script type='text/javascript'>"
		+	"alert('"+msg+"');"
		+	"location.href='"+url+"';"
		+"</script>";
//		response.sendRedirect("boardlist.board?seq="+seq);
		out.print(js);
	}
	
	
}
