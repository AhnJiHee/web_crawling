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
<%
request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	
	BoardVO vo = new BoardVO();
	
	vo.setBoardtitle(title);
	vo.setBoardcontents(content);
	vo.setBoardwriter(writer);
	
	BoardDAO dao = new BoardDAO();
	dao.insertBoard(vo);
	
%>

<table>

	<tr>
		<td>제목</td>
		<%
		out.println("<td>" + vo.getBoardtitle() + "</td>");
		%>
	</tr>
	<tr>
		<td>내용</td>
		<%
		out.println("<td><textarea rows='10' cols='50' readonly = 'readonly'>" + vo.getBoardcontents() + "</textarea></td>");
		%>
	</tr>
	<tr>
		<td>작성자</td>
		<%
		out.println("<td>" + vo.getBoardwriter() + "</td>");
		%>
	</tr>
	
</table>

<h5> 저장하였습니다. </h5>

<input type = button id = 'returnlist' value = "목록으로 돌아가기">

<!-- ------------------------------------------------------------------------------------>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		$("#returnlist").on("click", function(){ 
			//목록 화면으로 이동
			//location.href = "http://localhost:8081/boardproject2/boardlist.jsp";
			location.href = "boardlist.jsp";	
		})
	})
</script>


	
	

</body>
</html>