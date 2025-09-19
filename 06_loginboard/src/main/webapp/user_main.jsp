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
	function insertBoard(id){
		location.href="boardInsert.jsp?id="+id;
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
			<h1>사용자 페이지</h1>
			<span>
			<%=ldto.getId()%>[<%=ldto.getRole()%>]
			님이 로그인 하였습니다.
			</span>
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
				<col width="100px"/>
				<col width="200px"/>
				<col width="600px"/>
				<col width="200px"/>
				<tr>
					<th>글번호</th><th>ID</th><th>제목</th><th>내용</th><th>작성일</th>
				</tr>
				<%
				for(BoardDto bdto:list) {
				%>
				<tr>
					<td><%=bdto.getSeq()%></td>
					<td><%=bdto.getId()%></td>
					<td><%=bdto.getTitle()%></td>
					<td><%=bdto.getContent()%></td>
					<td><%=bdto.getRegdate()%></td>
				</tr>
				<%
				}
				%>
				<tr><td colspan="5"><button onclick="insertBoard('<%=ldto.getId()%>')">글작성</button></td></tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>