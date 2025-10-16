<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정</title>
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
	<h1>글 수정</h1>
	<form action="updateboard.do" method="post">
		<input type="hidden" name="seq" value="${dto.seq}">
		<table class="table table-striped">
			<tr>
				<th>작성자(ID)</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input class="form-control" type="text" name="title" value="${dto.title}" required="required"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea class="form-control" rows="10" cols="60" name="content" required="required">${dto.title}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn btn-primary" type="submit" value="글 수정" />
					<input class="btn btn-primary" type="button" value="글 목록" onclick="location.href='boardlist.do?pnum=1'; return false;"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<jsp:include page="footer.jsp"/>