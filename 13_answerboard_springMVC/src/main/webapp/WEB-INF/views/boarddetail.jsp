<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<script type="text/javascript">
	// 필요한 pk값
	function updateForm(seq){
		//수정폼 이동
		location.href = "updateboardform.board?seq="+seq;
	}
	
	function delBoard(seq) {
		location.href = "deleteboard.board?seq="+seq;
	}
	
	function replyForm() {
		document.querySelector("#replyForm").style.display="block"
	}
</script>
<style>
	#replyForm{
		display: none;
	}
</style>
</head>
<body>
	<h1>글 상세보기</h1>
	<form>
		<table class="table table-striped">
			<tr>
				<th>작성자(ID)</th>
				<td>${dto.id} (조회수: ${dto.readCount})</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea class="form-control"rows="10" cols="60" name="content" readonly="readonly">${dto.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn btn-primary" type="button" value="답글" onclick="replyForm()"/>
					<input class="btn btn-primary" type="button" value="글 수정" onclick="updateForm(${dto.seq})"/>
					<input class="btn btn-primary" type="button" value="글 삭제" onclick="delBoard(${dto.seq})"/>
					<input class="btn btn-primary" type="button" value="글 목록" onclick="location.href='boardlist.board'"/>
				</td>
			</tr>
		</table>
	</form>
	<div id="replyForm">
			<h2>답글 작성하기</h2>
			<form action="replyboard.board" method="post">
				<input type="hidden" name="seq" value="${dto.seq}" />
				<table class="table table-striped">
					<tr>
						<th>작성자(ID)</th>
						<td><input class="form-control" 
									type="text" name="id"
									pattern="^[a-zA-Z0-9]+$" 
									required="required"/></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input class="form-control" type="text" name="title" required="required"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea class="form-control" rows="5" cols="60" name="content" required="required"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-primary" type="submit" value="답글 등록" />
							<button class="btn btn-primary" type="button" onclick="location.href='boardlist.board'">글 목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>
<%-- <jsp:include page="footer.jsp"/> --%>