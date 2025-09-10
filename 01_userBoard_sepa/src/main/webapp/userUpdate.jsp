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
	String userID = request.getParameter("userID");
	String name = request.getParameter("name");
	String birthYear = request.getParameter("birthYear");
	int birthYearInt = Integer.parseInt(birthYear);
	String addr = request.getParameter("addr");
	String mobile1 = request.getParameter("mobile1");
	String mobile2 = request.getParameter("mobile2");
	String height = request.getParameter("height");
	int heightInt = Integer.parseInt(height);
	
	//dao객체 생성
	UserDao dao = new UserDao();
	
	//updateUser()실행
	boolean isS = 
	dao.updateUser(new UserDto(userID,name,birthYearInt,addr,mobile1,mobile2,heightInt,null));
	
	//응답페이지로 이동: 성공하면 index.jsp / 실패하면 error.jsp
	if (isS) {
//		response.sendRedirect("index.jsp");
		//회원 목록 페이지로 응답
//		response.sendRedirect("userList.jsp");
		//수정폼으로 응답
//		response.sendRedirect("userUpdateForm.jsp?userID="+userID);
	%>
	<!-- html영역 -->		
	<script type="text/javascript">
		alert("회원정보를 수정했습니다.");
		location.href="userUpdateForm.jsp?userID=<%=userID%>";
	</script>
		<%
	}else{
		response.sendRedirect("error.jsp");
	}
%>
<body>

</body>
</html>