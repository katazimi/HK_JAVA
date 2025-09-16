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
	Object obj = request.getAttribute("list");
	List<UserDto> list = (List<UserDto>)obj;
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
				<td><a href="boardController.jsp?command=userUpdateForm&seq=<%=dto.getUserID()%>">수정</a></td>
				<td><a href="#" onclick="deleteUser('<%=dto.getUserID()%>')">삭제</a></td>
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
	<script type="text/javascript">
		function deleteUser(userID) {
			if (confirm("정말 삭제하겠습니까?")){
				location.href="userDelete.jsp?userID="+userID;
			}else{
				location.href="userList.jsp";
			}
		}
		
	</script>
</body>
</html>