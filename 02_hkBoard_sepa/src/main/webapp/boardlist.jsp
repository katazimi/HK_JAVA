<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 조회</title>
<script>
	function insertBoardFrom(){
		location.href="insertboardform.jsp";
	}
</script>
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
	HkDao dao = new HkDao();
	List<HkDto> list = dao.getAllList();
%>
<body>
	<h1>게시판</h1>
	<h2>글 목록</h2>
	<table>
		<col width="50px"/>
		<col width="100px"/>
		<col width="300px"/>
		<col width="200px"/>
		<tr>
			<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
		</tr>
		<%
			for(HkDto dto:list) {
				%>
				<tr>
					<td><%=dto.getSeq()%></td>
					<td><%=dto.getId()%></td>
					<td><a href="boarddetail.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
					<td><%=dto.getRegdate() %></td>
					<td></td>
				</tr>
				<%
			}
		%>
		<tr>
			<td colspan="4"><button onclick="insertBoardFrom()">글 추가</button></td>
		</tr>
	</table>
</body>
</html>