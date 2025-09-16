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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String address = "(" + request.getParameter("address") +")" + request.getParameter("address2") + " " + request.getParameter("address4");
		String email = request.getParameter("email");
		
		boolean isS = dao.insertUser(new UserDto(id,name,password,address,email));
		
		if (isS) {
			%>
			<script type="text/javascript">
				alert("회원가입이 완료되었습니다.");
				location.href="index.jsp";
			</script>
			<%
		}else {
			%>
			<script type="text/javascript">
				alert("회원가입 실패.");
				location.href="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("idChk")) { //id체크하기
		String id=request.getParameter("id");
		String resultID=dao.idCheck(id);
		
		request.setAttribute("resultID", resultID);
		pageContext.forward("idChkForm.jsp");
	}
%>
</body>
</html>