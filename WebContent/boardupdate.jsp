<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width:100%;
		border-top : 1px solid #444444;
		border-collapse: collapse;
	}
	th,td{
		border-bottom: 1px solid #444444;
		padding : 10px;
	}
	#first{
	background-color: #b3c6ff;
	}
	
	textarea :focus {
		outline-color: white;
	}
	
	textarea{
		border-bottom: 1px;
		border-top: 1px;
		border-left: 1px;
		border-right: 1px;
	}
	
</style>
</head>
<body>
<%
BoardDAO dao = new BoardDAO();
/* int seq = Integer.parseInt((String)session.getAttribute("seq")); */
%>
<% BoardVO vo = (BoardVO)session.getAttribute("boardinfo"); %>
<% session.setAttribute("boardinfo2", vo); %>

<form action ="boardupdateresult.jsp" method = "post" accept-charset="utf-8">
<table border = '2px'>
	<tr>
		<td>게시물 번호</td>
		<%
		out.println("<td>" + vo.getBoardseq() + "</td>");
		%>
	</tr>
	<tr>
		<td>제목</td>
		<%
		out.println("<td><input type=text name = 'title' value='" + vo.getBoardtitle() + "'/"+"</td>");
		%>
		
		
	</tr>
	<tr>
		<td>작성자</td>
		<%
		out.println("<td>" + vo.getBoardwriter() + "</td>");
		%>
	</tr>
	<tr>
		<td>내용</td>
		<%
		out.println("<td><textarea rows = '10' cols = '50' name = 'content'>" + vo.getBoardcontents() + "</textarea>"+"</td>");
		%>
	</tr>	
</table>
<input type ="submit" value="저장" style="background-color: aqua; color: blue;">
<input type ="button" value="취소" id = 'exit' style="background-color: aqua; color: blue;">
</form>

<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$("#exit").on("click", function(){
		history.back();
	})
</script>
</body>
</html>