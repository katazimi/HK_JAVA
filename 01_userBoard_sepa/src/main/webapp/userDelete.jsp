<%@page import="com.hk.board.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String userID=request.getParameter("userID");
	UserDao dao = new UserDao();
	
	boolean isD = dao.deleteUser(userID);
	
	if (isD) {
		response.sendRedirect("index.jsp");
	}else{
		response.sendRedirect("error.jsp");
	}
%>
<body>

</body>
</html>