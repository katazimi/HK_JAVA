<%@jsp:include page="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	</div>
</div>
</body>
</html>
<%@jsp:include page="footer.jsp"%>