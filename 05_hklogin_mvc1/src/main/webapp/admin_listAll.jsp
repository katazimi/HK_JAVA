<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		border-collapse: collapse;
		border: 2px solid black;
	}
	th,td{
		border: 1px solid black;
	}
	th{
		background-color: lightgrey;
	}
</style>
</head>
<%
	Object obj = request.getAttribute("list");
	List<UserDto> list = (List<UserDto>)obj;
%>
<body>
<div id="container">
	<div class="main">
		<div>
			<h1>관리자 페이지</h1>
			<span>
			<%=ldto.getId()%>[<%=ldto.getRole()%>]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="adminController.jsp?command=userlistall">회원전체조회</a>
			</span>|
			<span>
				<a href="adminController.jsp?command=userlist">회원정보[등급]수정</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
		<div>
			<table>
				<col width="60px"/>
				<col width="100px"/>
				<col width="100px"/>
				<col width="400px"/>
				<col width="200px"/>
				<col width="100px"/>
				<col width="100px"/>
				<col width="100px"/>
				<tr>
					<th>회원번호</th><th>ID</th><th>이름</th><th>주소</th><th>E-MAIL</th><th>가입여부</th><th>회원등급</th><th>가입일</th>
				</tr>
				<%
				for(UserDto dto:list) {
				%>
				<tr>
					<td><%=dto.getSeq()%></td>
					<td><%=dto.getId()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getAddress()%></td>
					<td><%=dto.getEmail()%></td>
					<td><%=dto.getEnabled()=="Y"?"가입중":"회원탈퇴"%></td>
					<td><%=dto.getRole()%></td>
					<td><%=dto.getRegdate() %></td>
					<td></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>