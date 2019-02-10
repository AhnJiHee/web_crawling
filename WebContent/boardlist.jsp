<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>


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
	
	
</style>
</head>
<body>

<%

	int pagenum = 1;
	if(request.getParameter("page") != null){
		pagenum = Integer.parseInt(request.getParameter("page"));
	}
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> list = dao.getBoardList(pagenum);
	int total = dao.getTotalBoard();
	
	int pagecount = total/3;
	if(pagecount % 3 !=0){
		pagecount ++;
	}
%>

<table>
	<tr id ='first'>
	<td>번호</td><td>제목</td><td>작성자</td>
	<td>시간</td>
	<td>조회수</td>
	</tr>
	
	<%
		for(int i = 0; i < list.size(); i++){
			BoardVO vo = list.get(i);
			out.println("<tr><td>" + vo.getBoardseq() + "</td><td>"
					+ "<a href = 'boarddetail.jsp?boardseq=" + vo.getBoardseq() + "'>" + vo.getBoardtitle() + "</a></td><td>"
					+ vo.getBoardwriter() + "</td><td>"
					+ vo.getBoardtime() + "</td><td>"
					+ vo.getBoardviewcount() + "</td></tr>");
		}
	%>
</table>



<!-- html input 태그 : 입력받은 걸로 css, jquery 작업을 할때는 (id 속성 사용)
html input 태그 : jsp, servlet 같은 전송받은 서버 파일 내부에서 입력받은 파라메터의 이름으로
쓰려면 name 속성 사용 -->

<input type = button id = 'write' value = "글쓰기">
<% 
for(int i = 1; i<=pagecount; i++){
	out.println("<a href='boardlist.jsp?page=" + i +"'>"+ i + "</a>");
}
%>

<!-- ------------------------------------------------------------------------------------>
<script src = "jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		$("#write").on("click", function(){ //글쓰기 화면으로 이동
			//baordwrite.jsp로 이동
			location.href = "writecheck.jsp"
			//location.href = "boardwriteform.jsp";	
		})
	})

</script>
</body>
</html>