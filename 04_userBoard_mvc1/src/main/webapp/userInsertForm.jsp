<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록폼</title>
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
<body>
	<h1>신규회원입력</h1>
	<form action="boardController.jsp?command=userInsert" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" required="required" name="userID"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" required="required" name="name"/></td>
			</tr>
			<tr>
				<th>출생년도</th>
				<td><input type="text" required="required" name="birthYear"/></td>
			</tr>
			<tr>
				<th>지역</th>
				<td><input type="text" required="required" name="addr"/></td>
			</tr>
			<tr>
				<th>국번</th>
				<td><input type="text" name="mobile1"/></td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="mobile2"/></td>
			</tr>
			<tr>
				<th>키</th>
				<td><input type="text" name="height"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="회원정보입력"/></td>
			</tr>
		</table>
	</form>
</body>
</html>