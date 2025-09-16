<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
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
<body>
<%
	//1단계: command값 받기 -> 어떤 요청인지 확인
	//		-요청값: 별도의 command라는 값을 전달
	//		-요청url: boarlist.board: MVC2방식일때
	String command=request.getParameter("command");
	
	//2단계: DAO객체 생성
	HkDao dao = new HkDao();
	
	//3단계: 요청 분기
	if(command.equals("boardlist")) {
		//4단계: 파라미터 받기 (글목록의 경우에는 받을 파라미터가 없음)
		
		//5단계: dao메서드 실행
		List<HkDto> list = dao.getAllList(); //DB로 부터 글목록 데이터를 가져옴
		
		//6단계: Scope객체에 담기
		//request스코프: 객체전달범위 
		// 요청페이지 ---> 응답페이지
		request.setAttribute("list", list);
		
		//7단계: 페이지 응답(이동)
		pageContext.forward("boardlist.jsp");
	} else if(command.equals("insertboardform")) {
		response.sendRedirect("insertboardform.jsp");
	} else if(command.equals("insertboard")) {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boolean isS = dao.insertBoard(new HkDto(id,title,content));
		if (isS) {
			response.sendRedirect("boardController.jsp?command=boardlist");
		}else{
			response.sendRedirect("boardController.jsp?command=error");
		}
	} else if(command.equals("boarddetail")) {
		String sseq = request.getParameter("seq");
		int seq = Integer.parseInt(sseq);
		
		HkDto dto = dao.getBoard(seq);
		request.setAttribute("dto", dto);
		
		pageContext.forward("boarddetail.jsp");
	} else if (command.equals("updateboardform")) {
		String sseq = request.getParameter("seq");
		int seq = Integer.parseInt(sseq);
		response.sendRedirect("updateboardform.jsp?seq="+seq);
	} else if (command.equals("updateboard")) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		boolean isU = dao.updateBoard(new HkDto(seq,title,content));
		
		if (isU) {
			%>
			<script type="text/javascript">
				alert("글을 수정했습니다.")
				location.href="boardController.jsp?command=boarddetail&seq=<%=seq%>"
			</script>
			
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("글을 수정에 실패했습니다")
				location.href="boardController.jsp?command=error"
			</script>
			<%
		}
	} else if (command.equals("deleteboard")) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		boolean isD = dao.deleteBoard(seq);
		
		if (isD) {
			%>
			<script type="text/javascript">
				alert("글을 삭제했습니다.")
				location.href="boardController.jsp?command=boardlist"
			</script>
			
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("글을 삭제에 실패했습니다")
				location.href="boardController.jsp?command=error"
			</script>
			<%
		}
	} else if(command.equals("mulDel")) {
		String[] seqs = request.getParameterValues("seq");
		boolean isS = dao.mulDel(seqs);
		
		if(isS) {
			%>
			<script type="text/javascript">
			alert("글을 삭제했습니다.")
			response.sendRedirect("boardController.jsp?command=boardlist");
			</script>
			<%
		} else{
			%>
			<script type="text/javascript">
				alert("글을 삭제에 실패했습니다")
				response.sendRedirect("boardController.jsp?command=error");
			</script>
			<%
		}
			
	}
	
%>
</body>
</html>