<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
</style>
</head>
<body>
	<form action="" method="get">
		<input type="hidden" name="command" value="login" />
		<h1>Login</h1>
		<input type="text" name="id" placeholder="ID" required="required"/>
		<input type="password" name="password" placeholder="Password" required="required"/>
	</form>
	<button type="submit">Sign In</button>
	<button type="button" onclick="registForm()">Sign Up</button>
	
	<script type="text/javascript">
		function registForm() {
			location.href="userController.jsp?command=registForm";
		}
	</script>
</body>
</html>