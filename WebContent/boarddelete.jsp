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

<%
String id = (String)session.getAttribute("sessionid");
String pw = (String)session.getAttribute("sessionpw");
%>

<% BoardVO vo = (BoardVO)session.getAttribute("boardinfo"); 
	String idval = vo.getBoardwriter();
	
	
if(idval.equals(id)){
	BoardDAO dao = new BoardDAO();
	dao.deleteBoard(vo);
%>

<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
alert("삭제를 완료하였습니다.");
//location.href = "http://localhost:8081/boardproject2/boardlist.jsp";
location.href = "boardlist.jsp";
</script>
<%
}
else{
	
%>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
alert("권한이 없습니다.");
//location.href = "http://localhost:8081/boardproject2/boardlist.jsp";
location.href = "boardlist.jsp";
</script>
<%
}
%>

<!-- =================================================================== -->


</body>
</html>