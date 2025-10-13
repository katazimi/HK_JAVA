<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error페이지</title>
</head>
<%
	//오류 유형: status(페이지 처리 상태)
	//         exception (예외발생)
%>
<div class="error-box">
	<h2>시스템 오류입니다. 관리자에게 문의하세요(02-1234-5678)</h2>
	<%
		//상태코드 , 요청 URI
		Integer statusCode
		=(Integer)request.getAttribute("javax.servlet.error.status_code");
	
		String requestUri
		=(String)request.getAttribute("javax.servlet.error.request_uri");
		
		//예외 정보
		Throwable throwable=
		(Throwable) request.getAttribute("javax.servlet.error.exception");
		
		if(throwable != null){
			%>
			<p><strong>예외 타입:</strong><%=throwable.getClass().getName()%></p>
			<p><strong>예외 메시지:</strong><%=throwable.getMessage()%></p>
			<%
		}else if(statusCode!=null && requestUri!=null){
			%>
			<p><strong>에러 코드:</strong><%=statusCode%></p>
			<p><strong>요청한 주소:</strong><%=requestUri%></p>
			<%
		}
	%>
	<hr>
	<p>불편을 드려 죄송합니다. 잠시 후 다시 시도하세요</p>
	<a href="index.jsp">메인으로 돌아가기</a>
</div>
<body>

</body>
</html>





