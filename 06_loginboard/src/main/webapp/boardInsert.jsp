<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div id="registForm" style="width:800px; height: 550px; margin: 100px auto;" class="form-control ml-5">
		<h1>글 작성</h1>
		<form action="boardController.jsp" method="post" >
			<input type="hidden" name="command" value="insertBoard" />
			<input type="hidden" name="id" value="<%=request.getParameter("id")%>" />
			<div class="row mb-3">
				<div class="col-12">
					<input class="form-control" type="text" name="title"
						required="required" placeholder="제목" />
				</div>
			</div>
			<div class="row mb-3" >
				<div class="col-12" >
					 <textarea class="form-control" name="content" rows="15" required="required" placeholder="글 내용"></textarea>
				</div>
			</div>
			<input type="submit" value="작성완료"/>
		</form>
	</div>
</body>
</html>