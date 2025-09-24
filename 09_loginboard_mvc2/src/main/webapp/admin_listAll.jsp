<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>
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
</head>
<body>
<div id="container">
	<div class="main">
		<div>
			<h1>관리자 페이지</h1>
			<span>
			${ldto.getId()}[${ldto.getRole()}]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userlistall.admin">회원전체조회</a>
			</span>|
			<span>
				<a href="userlist.admin">회원정보[등급]수정</a>
			</span>|
			<span>
				<a href="logout.user">로그아웃</a>
			</span>
		</div>
		<div>
			<table>
				<col width="60px"/>
				<col width="100px"/>
				<col width="100px"/>
				<col width="400px"/>
				<col width="200px"/>
				<col width="100px"/>
				<col width="100px"/>
				<col width="100px"/>
				<tr>
					<th>회원번호</th><th>ID</th><th>이름</th><th>주소</th><th>E-MAIL</th><th>가입여부</th><th>회원등급</th><th>가입일</th>
				</tr>
				<tr>
					
				</tr>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="8">-- 작성된 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="dto">
							<tr>
								<td>${dto.seq}</td>
								<td>${dto.id}</td>
								<td>${dto.name}</td>
								<td>${dto.address}</td>
								<td>${dto.email}</td>
								<td>
								  <c:choose>
								    <c:when test="${dto.enabled eq 'Y'}">가입중</c:when>
								    <c:otherwise>탈퇴회원</c:otherwise>
								  </c:choose>
								</td>
								<td>${dto.role}</td>
								<td>${dto.regdate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>