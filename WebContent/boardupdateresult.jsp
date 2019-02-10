<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% BoardVO vo = (BoardVO)session.getAttribute("boardinfo2"); 
	int seq = vo.getBoardseq();
%>
<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	vo.setBoardtitle(title);
	vo.setBoardcontents(content);
	
	BoardDAO dao = new BoardDAO();
	dao.updateBoard(vo);
%>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	alert("수정을 완료하였습니다.");
	<%-- location.href = "http://localhost:8081/boardproject2/boarddetail.jsp?boardseq=" + "<%=seq%>"; --%>
	location.href = "boarddetail.jsp?boardseq=" + "<%=seq%>";

</script>
</body>
</html>