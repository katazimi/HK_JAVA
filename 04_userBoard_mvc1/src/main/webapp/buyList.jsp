<%@page import="com.hk.board.dtos.BuyDto"%>
<%@page import="com.hk.board.daos.BuyDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse;
		border:2px solid black;
	}
	td,th{
		border: 1px solid black;
	}
</style>
</head>
<%
	BuyDao bDao = new BuyDao();
	List<BuyDto>list = bDao.getAllBuying();
%>
<body>
	<table>
		<tr>
			<th>순서</th>
			<th>아이디</th>
			<th>상품명</th>
			<th>그룹명</th>
			<th>가격</th>
			<th>수량</th>
		</tr>
		<%
			for(int i=0; i<list.size(); i++) {
				BuyDto dto = list.get(i);
			%>
			<tr>
				<td><%=dto.getNum() %></td>
				<td><%=dto.getUserID() %></td>
				<td><%=dto.getProdName() %></td>
				<td><%=dto.getGroupName() %></td>
				<td><%=dto.getPrice() %></td>
				<td><%=dto.getAmount() %></td>
			</tr>
			<%
			}  
		%>
	</table>
</body>
</html>