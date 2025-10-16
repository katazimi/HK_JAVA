<%@ include file="header.jsp"%>
<%@page import="com.hk.dtos.UserDto"%>
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
	.main{
		padding:10px;
		font-size: 15pt;
	}
	.main a{
		text-decoration: none;
	}
</style>
</head>
<body>
<%-- <% UserDto ldto = (UserDto)session.getAttribute("ldto"); %> --%>
<div id="container">
	<div class="main">
		<div>
			<span>
				${ldto.id}[${ldto.role}]님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userinfo.user?id=${ldto.id}">나의 정보</a>
			</span>
			<span>
				<a href="logout.user">로그아웃</a>
			</span>
		</div>
	</div>
</div>

</body>
</html>
<%@ include file="footer.jsp"%>