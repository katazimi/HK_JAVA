<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 조회</title>
<style>
	table{
		border-collapse: collapse;
		border: 2px solid black;
	}
	td,th{
		border: 1px solid black;
	}
</style>
</head>
<% //Scriptlet: java 실행부
	//Dao객체: DB에 접근해야 되기 때문에
	UserDao dao = new UserDao(); 
	List<UserDto>list = dao.getAllUser(); //회원목록 가져오기
%>
<body>
	<h1>회원 조회 결과</h1>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>가입일</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<%
			for(int i=0; i<list.size(); i++) {
				UserDto dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getUserID()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getmDate()%></td>
				<td><a href="userUpdateForm.jsp?userID=<%=dto.getUserID()%>">수정</a></td>
				<td><a href="userDelete.jsp?userID=<%=dto.getUserID()%>">삭제</a></td>
			</tr>
			<% 	
			}
		%>
		<tr>
			<td colspan="5">
				<a href="index.jsp">메인화면</a>
			</td>
		</tr>
	</table>
	
</body>
</html>