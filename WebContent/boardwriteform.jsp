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
</style>
</head>
<body>

<%
String sessionid = (String)session.getAttribute("sessionid");
%>
<form action ="boardwriterresult.jsp" method = "post" accept-charset="utf-8">
	<table>
	<tr>
		<td>제목</td>
		<td><input type="text" name = 'title'></td>
		
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="10" cols="50" name = 'content'>게시물 내용을 작성하세요(총 10줄씩 보여드립니다)</textarea></td>
		
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name = 'writer' value = '<%=sessionid %>' readonly="readonly"></td>	
	</tr>
	</table>
	<p>
	<input type ="submit" value="저장버튼" style="background-color: aqua; color: blue;">
	<input type="button" value='입력취소' id = 'exit'style="background-color: aqua; color: blue;">
	</p>
	</form>
	
	<script src = "jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
	$("#exit").on("click", function(){
		history.back();
	})
	</script>
	
	
	<%-- <%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	
	BoardVO vo = new BoardVO();
	
	vo.setBoardtitle(title);
	vo.setBoardcontents(content);
	vo.setBoardwriter(writer);
	
	BoardDAO dao = new BoardDAO();
	dao.insertBoard(vo);
	
	%> --%>

	

	
</body>
</html>