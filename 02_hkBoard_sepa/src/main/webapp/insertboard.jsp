<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="com.hk.board.daos.HkDao"%>
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
<%	
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	HkDao dao = new HkDao();
	
	boolean isS = dao.insertBoard(new HkDto(id,title,content));
	
	if (isS) {
		response.sendRedirect("boardlist.jsp");
	}else{
		response.sendRedirect("error.jsp");
	}
%>
<body>

</body>
</html>