<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 추가하기</title>
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
<body>
	<h1>글 추가하기</h1>
	<form action="insertboard.jsp" method="post">
		<table>
			<tr>
				<th>작성자(ID)</th>
				<td><input type="text" name="id" required="required"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" required="required"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글 등록" />
					<button type="button" onclick="location.href='boardlist.jsp'">글 목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>