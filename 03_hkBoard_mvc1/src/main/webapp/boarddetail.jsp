<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<script type="text/javascript">
	// 필요한 pk값
	function updateForm(seq){
		//수정폼 이동
		location.href = "boardController.jsp?command=updateboardform&seq="+seq;
	}
	
	function delBoard(seq) {
		location.href = "boardController.jsp?command=deleteboard&seq="+seq;
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
	HkDto dto = (HkDto)request.getAttribute("dto");
%>
<body>
	<h1>글 상세보기</h1>
	<table>
			<tr>
				<th>작성자(ID)</th>
				<td><%=dto.getId()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getTitle()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content" readonly="readonly"><%=dto.getContent()%></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="글 수정" onclick="updateForm(<%=dto.getSeq()%>)"/>
					<input type="button" value="글 삭제" onclick="delBoard(<%=dto.getSeq()%>)"/>
					<input type="submit" value="글 목록" onclick="location.href='boardController.jsp?command=boardlist'"/>
				</td>
			</tr>
		</table>
</body>
</html>