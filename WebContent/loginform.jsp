<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src = "../jquery-3.2.1.min.js">
$(document).ready(function(){
	var value = $("#here input[type=submit]").val();
	if(value == "로그인"){
		$("#here").html("<form action= '../logoutsession'> <input type='submit' value = '로그아웃'>")
	}
	
})
</script>

</head>
<body>

<h1>로그인 화면</h1>
<h1 id = "here">
<!-- <form action= "http://localhost:8081/boardproject2/logindb"> -->
<form action= "logindb">
아이디 : <input type = text name = 'id' > <br>
비밀번호 : <input type = "password" name = 'pw' ><br>
<input type="submit" value = "로그인">
</form>
</h1>

<!-- <form action= "../logoutsession">
<input type="submit" value = "로그아웃">
</form> -->

</body>
</html>