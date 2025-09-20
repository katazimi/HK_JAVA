<%@page import="java.util.List"%>
<%@page import="com.hk.dtos.BoardDto"%>
<%@page import="com.hk.daos.BoardDao"%>
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
	
	BoardDao dao = BoardDao.getBoardDao();
	
	if(command.equals("insertBoard")) {
		BoardDto dto = new BoardDto();
		
		dto.setId(request.getParameter("id"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		boolean isS = dao.insertBoard(dto);
		
		
		if(isS) {
			%>
			<script type="text/javascript">
				alert("작성이 완료되었습니다..");
			</script>
			<%
		}else {
			%>
			<script type="text/javascript">
				alert("작성 실패...");
			</script>
			<%
		}
		
		List<BoardDto> list = dao.getAllList();
		request.setAttribute("list", list);
		pageContext.forward("user_main.jsp");
	}else if (command.equals("getList")) {
		String id = request.getParameter("id");
		List<BoardDto> list = dao.getList(id);
		request.setAttribute("list", list);
		pageContext.forward("user_board.jsp");
	}
%>
</body>
</html>