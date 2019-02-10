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
session = request.getSession();
if(session.getAttribute("sessionid")== null){
%>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
alert("로그인이 필요합니다.");
location.href = "loginform.jsp";
</script>
<%
}
else{
	

%>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
location.href = "boardwriteform.jsp";
</script>
<%
}
%>

</body>
</html>