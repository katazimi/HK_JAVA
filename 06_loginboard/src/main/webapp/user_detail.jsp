<%@page import="com.hk.dtos.UserDto"%>
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
<script type="text/javascript">
	function userUpdate(id){
	    location.href="userUpdate.jsp?id="+id;
	}
	
	function userDel(id) {
		if(confirm('정말 탈퇴하시겠습니까?'))
			location.href="userController.jsp?command=userDel&id="+id;
	}
</script>
</head>
<%
	Object obj = request.getAttribute("dto");
	UserDto dto = (UserDto)obj;
%>
<body>
	<h1>회원정보</h1>
	<table>
		<tr><th>아이디</th><td><%=dto.getId()%></td></tr>
		<tr><th>이름</th><td><%=dto.getName()%></td></tr>
		<tr><th>주소</th><td><%=dto.getAddress()%></td></tr>
		<tr><th>전화번호</th><td><%=dto.getPhone()%></td></tr>
		<tr><th>이메일</th><td><%=dto.getEmail()%></td></tr>
		<tr><th>회원등급</th><td><%=dto.getRole()%></td></tr>
		<tr><th>가입일</th><td><%=dto.getRegdate()%></td></tr>
		<tr>
			<td colspan="2">
				<button onclick="userUpdate('<%=dto.getId()%>')">회원정보수정</button>
				<button onclick="userDel('<%=dto.getId()%>')">회원탈퇴</button>
				<button onclick="location.href='userController.jsp?command=moveMain'">메인페이지</button>
			</td>
		</tr>
	</table>
</body>
</html>