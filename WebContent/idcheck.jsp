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
	
%>

<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
alert("수정가능합니다");
//location.href = "http://localhost:8081/boardproject2/boardupdate.jsp";
location.href = "boardupdate.jsp";
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


<%-- $(document).ready(function(){
	if(<%=idval%><%=id%>){
		alert("권한이 없습니다.");
		location.href = "http://localhost:8081/boardproject2/boardlist.jsp";
	
	}
	else if(<%=idval%> == <%=id%>){
		location.href = "http://localhost:8081/boardproject2/boardupdate.jsp";
	}
	
}) --%>


</body>
</html>