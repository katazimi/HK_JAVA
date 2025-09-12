<%@page import="com.hk.board.daos.HkDao"%>
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
<%
	HkDao dao = new HkDao();
	int seq = Integer.parseInt(request.getParameter("seq"));
	boolean isD = dao.deleteBoard(seq);
	
	if (isD) {
		%>
		<script type="text/javascript">
			alert("글을 삭제했습니다.")
			location.href="boardlist.jsp"
		</script>
		
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글을 삭제에 실패했습니다")
			location.href="error.jsp"
		</script>
		<%
	}
	
%>
<body>

</body>
</html>