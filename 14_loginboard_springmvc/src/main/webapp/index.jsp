<%-- <%@ include file="header.jsp" %> --%>
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
<style type="text/css">
	
</style>
</head>
<body>
	<div id="container" style="width:500px; margin:100px auto">
		<form class="form-control" action="login.do" method="post" onsubmit="userIdCookie()">
			<h1>Login</h1>
			<input class="form-control mb-2" type="text" name="id" placeholder="ID" required="required"/>
			<input class="form-control mb-2" type="password" name="password" placeholder="Password" required="required"/>
			<input class="form-check-input mb-2" type="checkbox" value="remember-me" /><label>아이디 저장</label>
			<label><small><c:if test="${not empty param.msg}"><c:out value="${param.msg}" /></c:if></small></label>
			<button class="form-control btn btn-primary mb-2" type="submit">Sign In</button>
			<button class="form-control mb-2" type="button" onclick="registForm()">Sign Up</button>
		</form>
	</div>
	
	
	<script type="text/javascript">
		function registForm() {
			location.href="registForm.do";
		}
		
		//아이디를 쿠키에 저장
		function userIdCookie() {
			const chkID = document.querySelectorAll("input[type=checkbox]")[0];
			const id = document.querySelectorAll("input[name=id]")[0].value;
			
			if (chkID.checked) {
				setCookie("rememberid",id,100);
			}
		}
		//cookie에 아디값이 저장되어 있다면 id입력박스에 id를 출력시키자
		onload=function() {
			const cookieID=getCookie("rememberid");
			if (cookieID!=null) {
				document.querySelectorAll("input[name=id]")[0].value=cookieID;
			}
		}
	</script>
</body>
</html>