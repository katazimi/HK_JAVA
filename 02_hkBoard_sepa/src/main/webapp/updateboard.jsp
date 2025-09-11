<%@page import="javax.tools.DocumentationTool.Location"%>
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
	HkDao dao = new HkDao();
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	boolean isU = dao.updateBoard(new HkDto(seq,title,content));
	
	if (isU) {
		response.sendRedirect("boardlist.jsp");
	}else{
		response.sendRedirect("error.jsp");
	}
%>
<body>

</body>
</html>