<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="java.util.List"%>
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
<body>
<%
	//1단계: command값 받기
	String command = request.getParameter("command");

	//2단계: DAO객체 생성
	UserDao dao = new UserDao();
	
	//3단계 요청 분기
	if(command.equals("userList")) {
		List<UserDto> list = dao.getAllUser();
		request.setAttribute("list", list);
		pageContext.forward("userList.jsp");
	} else if (command.equals("userInsertForm")) {
		response.sendRedirect("userInsertForm.jsp");
	} else if (command.equals("userInsert")) {
		String userID = request.getParameter("userID");
		String name = request.getParameter("name");
		String birthYear = request.getParameter("birthYear");
		int birthYearInt = Integer.parseInt(birthYear);
		String addr = request.getParameter("addr");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String height = request.getParameter("height");
		int heightInt = Integer.parseInt(height);
		
		boolean isS = 
				dao.insertUser(new UserDto(userID,name,birthYearInt,addr,mobile1,mobile2,heightInt,null));
				
				if (isS) {
					response.sendRedirect("index.jsp");
				}else{
					response.sendRedirect("error.jsp");
				}
	} else if (command.equals("userUpdateForm")) {
		String seq = request.getParameter("seq");
		response.sendRedirect("userUpdateForm.jsp?seq="+seq);
	} else if (command.equals("userUpdate")) {
		String userID = request.getParameter("userID");
		String name = request.getParameter("name");
		String birthYear = request.getParameter("birthYear");
		int birthYearInt = Integer.parseInt(birthYear);
		String addr = request.getParameter("addr");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String height = request.getParameter("height");
		int heightInt = Integer.parseInt(height);
		
		boolean isS = 
				dao.updateUser(new UserDto(userID,name,birthYearInt,addr,mobile1,mobile2,heightInt,null));
				
				//응답페이지로 이동: 성공하면 index.jsp / 실패하면 error.jsp
				if (isS) {
//					response.sendRedirect("index.jsp");
					//회원 목록 페이지로 응답
//					response.sendRedirect("userList.jsp");
					//수정폼으로 응답
//					response.sendRedirect("userUpdateForm.jsp?userID="+userID);
				%>
				<!-- html영역 -->		
				<script type="text/javascript">
					alert("회원정보를 수정했습니다.");
					location.href="boardController.jsp?command=userList";
				</script>
					<%
				}else{
					response.sendRedirect("error.jsp");
				}
	}
%>
</body>
</html>