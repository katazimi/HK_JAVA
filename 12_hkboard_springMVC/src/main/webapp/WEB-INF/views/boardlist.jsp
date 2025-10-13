<%@page import="java.util.List"%>
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
<title>글목록 조회</title>
<script type="text/javascript">
	function insertBoardForm(){
		location.href="insertboardform.do";
	}
	
	//전체선택 체크박스 기능
	function allSel(bool){// bool:전체 선택 박스의 체크 여부(true/false)
		const seqs=document.getElementsByName("seq");//[seq,seq..]
		for (let i = 0; i < seqs.length; i++) {
			seqs[i].checked=bool;//체크여부 적용
		}
	}
	
	//삭제 체크박스 유효값 처리 기능
	function isAllCheck(){

		let chks=document.querySelectorAll("input[name=seq]:checked");
		console.log(chks.length);
		if(chks.length==0){
// 			alert("하나이상 체크하세요");
			document.querySelector("#msg").textContent="하나이상 체크하세요";
			return false;// submit X
		}else{
			if(confirm("정말 삭제하겠습니까?")){
				return true;// submit O					
			}else{
				document.querySelector("#msg").textContent="";
				return false;// submit X	
			}
		}//if종류
	}
</script>
</head>
<body>
<h1>게시판</h1>
<h2>글목록</h2>
<form action="muldel.do" method="post" onsubmit="return isAllCheck()">
<table border="1">
	<col width="50px"/>
	<col width="50px"/>
	<col width="100px"/>
	<col width="300px"/>
	<col width="200px"/>
	<tr><th>
			<input type="checkbox" name="all" 
			       onclick="allSel(this.checked)">
		</th>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="5">--작성된 글이 없습니다.--</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto" >
				<tr>
					<td><input type="checkbox" name="seq" value="${dto.seq}" /></td>
					<td>${dto.seq}</td>
					<td>${dto.id}</td>
					<td><a href="boarddetail.do?seq=${dto.seq}">${dto.title}</a></td>
					<td><fmt:formatDate value="${dto.regDate}" 
										pattern="yyyy년 MM월 dd일"/> </td>
				</tr>	
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="5">
			<button type="button" onclick="insertBoardForm()">글추가</button>	
			<button type="submit">글삭제</button>
			<span id="msg"></span>
		</td>
	</tr>
</table>
</form>
</body>
</html>








