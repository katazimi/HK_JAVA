<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="com.hk.board.daos.UserDao"%>
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
	//UserDao클래스: updateUser()메서드 구현
	//파라미터받기
	//dao객체 생성
	//updateUser()실행
	//응답페이지로 이동: 성공하면 index.jsp / 실패하면 error.jsp
	
	String userID = request.getParameter("userID");
	String name = request.getParameter("name");
	String birthYear = request.getParameter("birthYear");
	int birthYearInt = Integer.parseInt(birthYear);
	String addr = request.getParameter("addr");
	String mobile1 = request.getParameter("mobile1");
	String mobile2 = request.getParameter("mobile2");
	String height = request.getParameter("height");
	int heightInt = Integer.parseInt(height);
	
	UserDao dao = new UserDao();
	
	boolean isS = 
	dao.updateUser(new UserDto(userID,name,birthYearInt,addr,mobile1,mobile2,heightInt,null));
	
	if (isS) {
		response.sendRedirect("index.jsp");
	}else{
		response.sendRedirect("error.jsp");
	}
%>
<body>

</body>
</html>