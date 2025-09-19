<%@page import="java.util.List"%>
<%@page import="com.hk.dtos.UserDto"%>
<%@page import="com.hk.daos.AdminDao"%>
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
	/* 만들어 줄 페이지
	admin_main.jsp
	admin_listAll.jsp
	admin_list.jsp
	admin_userRoleForm.jsp : 회원상세조회(등급수정폼 포함)
	*/
	
	String command = request.getParameter("command");

	AdminDao dao = AdminDao.getAdminDao();
	
	if (command.equals("userlistall")) {
		//객체(dto)를 스코프객체에 저장하고
		List<UserDto> list = dao.getAllUserList();
		request.setAttribute("list", list);
		//이동한다.
		pageContext.forward("admin_listAll.jsp");
	}else if (command.equals("userlist")) {
		//객체(dto)를 스코프객체에 저장하고
		List<UserDto> list = dao.getAllUserList();
		request.setAttribute("list", list);
		//이동한다.
		pageContext.forward("admin_list.jsp");
	}else if (command.equals("updateRole")) {
		String id = request.getParameter("id");
		String role = request.getParameter("role");
		boolean isS = dao.getUpdateRole(id, role);
		
		if (isS) {
			%>
			<script type="text/javascript">
				alert("수정완료");
				location.href="adminController.jsp?command=userlist";
			</script>
			<%
		}else {
			%>
			<script type="text/javascript">
				alert("수정 실패.");
				location.href="adminController.jsp?command=userlist";
			</script>
			<%
		}
	}else if (command.equals("logout")) {
		session.invalidate(); //세션의 모든 정보를 삭제
		response.sendRedirect("index.jsp");
	}
%>
</body>
</html>