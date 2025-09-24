package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.UserDao;
import com.hk.dtos.UserDto;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

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
		
		//세션값 가져오기
		HttpSession session = request.getSession();
		
		UserDao dao = UserDao.getUserDao();
		
		if(command.equals("/registForm.user")) {
			response.sendRedirect("registForm.jsp");
		}else if (command.equals("/insertUser.user")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String address = request.getParameter("address1") +":" + request.getParameter("address2") + ":" + request.getParameter("address3")+ ":" + request.getParameter("address4");
			String email = request.getParameter("email");
			
			boolean isS = dao.insertUser(new UserDto(id,name,password,address,email));
			
			if (isS) {
				jsResponse(response, "회원가입이 완료되었습니다.", "index.jsp");
			}else {
				jsResponse(response, "회원가입 실패!", "error.jsp");
			}
		}else if(command.equals("/idChk.user")) {
			String id=request.getParameter("id");
			String resultID=dao.idCheck(id);
			
			request.setAttribute("resultID", resultID);
			dispatch("idChkForm.jsp",request,response);
		} else if (command.equals("/login.user")) {
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			
			UserDto dto = dao.getLogin(id, password);
			
			if(dto==null||dto.getId()==null) {
				System.out.println("로그인 실패");
				response.sendRedirect("index.jsp?msg="+URLEncoder.encode("회원가입을 해주세요","utf-8"));
			}else {
				System.out.println("로그인 성공");
				session.setAttribute("ldto", dto);
				session.setMaxInactiveInterval(10*60);
				
				if(dto.getRole().toUpperCase().equals("ADMIN")){
					response.sendRedirect("admin_main.jsp");
				}else if(dto.getRole().toUpperCase().equals("MANAGER")){
					response.sendRedirect("manager_main.jsp");
				} else if(dto.getRole().toUpperCase().equals("USER")){
					response.sendRedirect("user_main.jsp");
				} 
			}
		}else if (command.equals("/userinfo.user")) {
			String id=request.getParameter("id");
			UserDto dto=dao.getUser(id);
			
			//객체(dto)를 스코프객체에 저장하고
			request.setAttribute("dto", dto);
			//이동한다.
			dispatch("userinfo.jsp",request,response);
		}else if (command.equals("/logout.user")) {
			session.invalidate(); //세션의 모든 정보를 삭제
			response.sendRedirect("index.jsp");
		}else if (command.equals("/userUpdate.user")) {
			String id=request.getParameter("id");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			
			boolean isS=dao.updateUser(new UserDto(id,address,email));
			
			if(isS) {
				jsResponse(response, "수정성공!", "userinfo.user?id="+id);
			}else{
				jsResponse(response, "수정실패!", "error.jsp");
			}
		}else if (command.equals("/deluser.user")) {
			String id=request.getParameter("id");
			
			boolean isS=dao.delUser(id);
			session.invalidate();//세션 삭제
			
			if(isS){
				jsResponse(response, "탈퇴성공!", "index.jsp");
			}else{
				jsResponse(response, "탈퇴실패!", "userinfo.user?id="+id);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
