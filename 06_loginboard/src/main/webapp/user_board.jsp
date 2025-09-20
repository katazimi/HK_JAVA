<%@page import="com.hk.dtos.BoardDto"%>
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
<script type="text/javascript">
	function moveMain() {
		location.href="userController.jsp?command=moveMain";
	}
	
	function boardUpdate(id, seq) {
		location.href = "board_update.jsp?id="+id+"&seq="+seq;
	}
	
	function boardDelete(id, seq) {
		location.href = "boardController.jsp?command=delBoard&id="+id+"&seq="+seq;
	}
</script>
</head>
<%
	Object obj = request.getAttribute("list");
	List<BoardDto> list = (List<BoardDto>)obj;
%>
<body>
<div id="container">
	<div class="main">
		<div>
			<h1>내가 작성한 글</h1>
			<span>
				<a href="userController.jsp?command=userDetail&id=<%=ldto.getId()%>">나의정보조회</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
		<div>
			<table>
				<col width="50px"/>
				<col width="200px"/>
				<col width="600px"/>
				<col width="200px"/>
				<col width="120px"/>
				<tr>
					<th>글번호</th><th>제목</th><th>내용</th><th>작성일</th><th> </th>
				</tr>
				<%
				for(BoardDto bdto:list) {
				%>
				<tr>
					<td><%=bdto.getSeq()%></td>
					<td><%=bdto.getTitle()%></td>
					<td><%=bdto.getContent()%></td>
					<td><%=bdto.getRegdate()%></td>
					<td>
						<button class="btn btn-outline-success" onclick="boardUpdate('<%=ldto.getId()%>','<%=bdto.getSeq()%>')">수정</button>
						<button class="btn btn-outline-danger" onclick="boardDelete('<%=ldto.getId()%>','<%=bdto.getSeq()%>')">삭제</button>
					</td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="5">
						<button onclick="moveMain()">메인페이지</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>