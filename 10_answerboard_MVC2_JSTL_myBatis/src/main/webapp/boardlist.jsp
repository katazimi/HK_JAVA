<%@page import="com.hk.dto.AnsDto"%>
<jsp:include page="header.jsp"/>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 조회</title>
<script>
	function insertBoardForm(){
		location.href="insertboardform.board";
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
<body>
	<h1>게시판</h1>
	<h2>글 목록</h2>
	<form action="muldel.board" method="post" onsubmit="return isAllCheck()">
		<table class="table table-striped">
		<tr>
			<th><input class="form-check-input" type="checkbox" id="chkAll" onchange="allSel(this.checked)"/></th>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>삭제여부</th>
			<th>REFER</th>
			<th>STEP</th>
			<th>DEPTH</th>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="10">-- 작성된 글이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td style="text-align: center;"><input class="form-check-input" type="checkbox" name="seq" value="${dto.seq}" /></td>
						<td>${dto.seq}</td>
						<td>${dto.id}</td>
						<td><a href="boarddetail.board?seq=${dto.seq}&review=y">${dto.title}</a></td>
						<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy년 MM월 dd일"/></td>
						<td>${dto.readCount}</td>
						<td>${dto.delFlag}</td>
						<td>${dto.refer}</td>
						<td>${dto.step}</td>
						<td>${dto.depth}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="10">
				<button class="btn btn-primary" onclick="insertBoardForm(); return false;">글 추가</button>
				<button class="btn btn-primary" type="submit">글삭제</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
<jsp:include page="footer.jsp"/>