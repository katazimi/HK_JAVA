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
		location.href="boardController.jsp?command=insertboardform";
	}
	
	//체크박스 전체 선택기능
	function allSel(bool) {
		var seqs = document.getElementsByName("seq");
		
		for (let i = 0; i < seqs.length; i++) {
			seqs[i].checked=bool;
		}
	}
	
	function isAllCheck() {
		var chks = document.querySelectorAll("input[name=seq]:checked");
		if (chks.length==0) {
			alert("한개 이상 체크하세요!");
			return false;
		}else {
			if(confirm("정말 삭제하겠습니까?")) 
				return true;
			else 
				return false;
		}
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
	Object obj = request.getAttribute("list");
	List<HkDto> list = (List<HkDto>)obj;
%>
<body>
	<h1>게시판</h1>
	<h2>글 목록</h2>
	<form action="boardController.jsp" method="post" onsubmit="return isAllCheck()">
		<input type="hidden" name="command" value="mulDel"/>
		<table>
		<col width="50px"/>
		<col width="50px"/>
		<col width="100px"/>
		<col width="300px"/>
		<col width="200px"/>
		<tr>
			<th><input type="checkbox" id="chkAll" onchange="allSel(this.checked)"/></th>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<%
			for(HkDto dto:list) {
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox" name="seq" value="<%=dto.getSeq()%>" /></td>
					<td><%=dto.getSeq()%></td>
					<td><%=dto.getId()%></td>
					<td><a href="boardController.jsp?command=boarddetail&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
					<td><%=dto.getRegdate() %></td>
					<td></td>
				</tr>
				<%
			}
		%>
		<tr>
			<td colspan="5">
				<button onclick="insertBoardFrom(); return false;">글 추가</button>
				<button type="submit">글삭제</button>
			</td>
		</tr>
	</table>
	</form>
	
</body>
</html>