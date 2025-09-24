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
<% 	//id조회 결과 받기 -> null이면 사용가능 / null이 아니라면 사용불가
	String resultID = (String)request.getAttribute("resultID");
%>
<body>
	<div>
		<span><%=resultID==null?"사용가능한 아이디 입니다.":"중복된 아이디 입니다."%></span>
		<span><button onclick="confirmID('<%=resultID%>')">확인</button></span>
	</div>
<script type="text/javascript">
	function confirmID(resultID) {
		if(resultID=='null') {
			localStorage.setItem("idChk","y");
			opener.document.getElementsByName("name")[0].focus();
		}else {
			localStorage.setItem("idChk","n");
			opener.document.getElementsByName("id")[0].focus();
		}	
		
		self.close();
	} 
</script>
</body>
</html>