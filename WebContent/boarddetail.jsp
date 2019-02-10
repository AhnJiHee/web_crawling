<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
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


</textarea>
<%
int boardseq = Integer.parseInt(request.getParameter("boardseq"));
BoardDAO dao = new BoardDAO();
dao.updateViewCount(boardseq);
BoardVO vo = dao.getBoardDetail(boardseq);
%>

<% session.setAttribute("boardinfo", vo); %>

<table>
	<tr>
		<td>게시물 번호</td>
		<%
		out.println("<td>" + vo.getBoardseq() + "</td>");
		%>
	</tr>
	<tr>
		<td>제목</td>
		<%
		out.println("<td>" + vo.getBoardtitle() + "</td>");
		%>
		
	</tr>
	<tr>
		<td>작성자</td>
		<%
		out.println("<td>" + vo.getBoardwriter() + "</td>");
		%>
		
	</tr>
	<tr>
		<td>작성일</td>
		<%
		out.println("<td>" + vo.getBoardtime() + "</td>");
		%>
	</tr>
	<tr>
		<td>조회수</td>
		<%
		out.println("<td>" + vo.getBoardviewcount() + "</td>");
		%>
	</tr>
	<tr>
		<td>내용</td>
		<%
		out.println("<td><textarea rows='10' cols='50' name = 'content' readonly='readonly'>" + vo.getBoardcontents() + "</textarea></td>");
		%>
	</tr>	
	
</table>

<input type = button id = 'returnlist' value = "목록">
<input type = button id = 'update' value = "수정">
<input type = button id = 'delete' value = "삭제">




<!-- ============================================================================ -->

<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		$("#returnlist").on("click", function(){ 
			//목록 화면으로 이동
			//location.href = "http://localhost:8081/boardproject2/boardlist.jsp";	
			location.href = "boardlist.jsp";	
		})
		$("#update").on("click", function(){ 
		
			//location.href = "http://localhost:8081/boardproject2/boardupdate.jsp";
			//location.href = "http://localhost:8081/boardproject2/idcheck.jsp";
			location.href = "idcheck.jsp";
			
			
		})
		$("#delete").on("click", function(){ 

			//location.href = "http://localhost:8081/boardproject2/boarddelete.jsp";
			location.href = "boarddelete.jsp";
		})
	})
	
</script>
</body>
</html>