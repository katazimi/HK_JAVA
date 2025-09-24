<%@page import="com.hk.dtos.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
	Object obj = request.getAttribute("dto");
	BoardDto dto = (BoardDto)obj;
%>
<body>
	<div id="registForm" style="width:800px; height: 550px; margin: 100px auto;" class="form-control ml-5">
		<h1>글 수정</h1>
		<form action="boardController.jsp" method="post" >
			<input type="hidden" name="command" value="updateBoard" />
			<input type="hidden" name="id" value="<%=dto.getId()%>" />
			<input type="hidden" name="seq" value="<%=dto.getSeq()%>" />
			<div class="row mb-3">
				<div class="col-12">
					<input class="form-control" type="text" name="title"
						required="required" value="<%=dto.getTitle()%>" />
				</div>
			</div>
			<div class="row mb-3" >
				<div class="col-12" >
					 <textarea class="form-control" name="content" rows="15" required="required" ><%=dto.getContent()%></textarea>
				</div>
			</div>
			<input type="submit" value="수정완료"/>
		</form>
	</div>
</body>
</html>