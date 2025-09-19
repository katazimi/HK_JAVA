<%@page import="java.net.URLEncoder"%>
<%@page import="com.hk.dtos.UserDto"%>
<%@page import="com.hk.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");

	UserDao dao = UserDao.getUserDao();
	
	if (command.equals("registForm")) {
		response.sendRedirect("registForm.jsp");
	} 
	else if (command.equals("insertUser")) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address1") +":" + request.getParameter("address2") + ":" + request.getParameter("address3")+ ":" + request.getParameter("address4");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		boolean isS = dao.insertUser(new UserDto(id,password,name,address,phone,email));
		
		if (isS) {
			%>
			<script type="text/javascript">
				alert("회원가입이 완료되었습니다.");
				location.href="index.jsp";
			</script>
		<%
		} else {
		    // 회원가입 실패 시, 에러 메시지를 request 객체에 담는다.
		    request.setAttribute("errorMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
		    
		    // error.jsp 페이지로 현재 요청(request)과 응답(response)을 그대로 넘겨준다.
		    %>
		    <jsp:forward page="error.jsp" />
		    <%
		}
		%>
		<% 
	}else if(command.equals("idChk")) { //id체크하기
		String id=request.getParameter("id");
		String resultID=dao.idCheck(id);
		request.setAttribute("resultID", resultID);
		pageContext.forward("idChkForm.jsp");
	}else if (command.equals("login")) {
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
	}else if(command.equals("logout")) {
		//session의 로그인 정보를 삭제
//		session.removeAttribute("ldto");
		session.invalidate(); //세션의 모든 정보를 삭제
		response.sendRedirect("index.jsp");
	}
%>
</body>
</html>