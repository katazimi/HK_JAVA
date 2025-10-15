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
		location.href="insertboardform.do";
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
	<div id="container">
		<jsp:useBean id="util" class="com.hk.board.util.Util"/>
		<h1>게시판</h1>
		<h2>글 목록</h2>
		<form action="muldel.board" method="post" onsubmit="return isAllCheck()">
			<table class="table table-striped">
			<tr>
				<th style="text-align: center;"><input class="form-check-input" type="checkbox" id="chkAll" onchange="allSel(this.checked)"/></th>
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
							<td>
								<c:choose>
									<c:when test="${dto.delFlag=='Y'}">
										---삭제된 글입니다.---
									</c:when>
									<c:otherwise>
										<%-- <jsp:setProperty property="arrowNbsp" name="util" value="${dto.depth}"/>
										<jsp:getProperty property="arrowNbsp" name="util"/> --%>
										<c:forEach begin="1" end="${dto.depth}" var="i" step="1">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${i==dto.depth}">
												<img src="img/arrow.png" width="15px" height="15px">
											</c:if>
										</c:forEach>
										<a href="boarddetail.board?seq=${dto.seq}&review=y">
											${fn:length(dto.title)>10?fn:substring(dto.title,0,10)+='...':dto.title}
										</a>
									</c:otherwise>
								</c:choose>
							</td>
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
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item">
		    	<c:choose>
		    		<c:when test="${param.pnum > 1}">
		    			<c:set var="prevPageNum" value="${param.pnum-1}"/>
		    		</c:when>
		    		<c:otherwise>
		    			<c:set var="prevPageNum" value="${param.pnum}"/>
		    		</c:otherwise>
		    	</c:choose>
		      	<a class="page-link" href="boardlist.board?pnum=${prevPageNum}">Previous</a>
		    </li>
		    <c:forEach begin="${pMap.startPage}" end="${pMap.endPage}" var="i" step="1">
		    	<li class="page-item ${sessionScope.pnum==i?'active':''}"><a class="page-link" href="boardlist.board?pnum=${i}">${i}</a></li>
		    </c:forEach>
		    <li class="page-item">
		    	<c:choose>
		    		<c:when test="${param.pnum < pCount}">
		    			<c:set var="nextPageNum" value="${param.pnum+1}"/>
		    		</c:when>
		    		<c:otherwise>
		    			<c:set var="nextPageNum" value="${param.pnum}"/>
		    		</c:otherwise>
		    	</c:choose>
		      	<a class="page-link" href="boardlist.board?pnum=${nextPageNum}">Next</a>
		    </li>
		  </ul>
		</nav>
	</div>
</body>
</html>
<jsp:include page="footer.jsp"/>