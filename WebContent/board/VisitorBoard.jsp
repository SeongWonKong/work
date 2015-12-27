<%@ page import="spms.vo.Board" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<div style="margin:0 auto; width:500px; text-align:center;">
<h1>방명록</h1>
<p><a href='add'>새로운 글 쓰기</a></p>
</div>


<table style="margin:0 auto; width:600px; text-align:center">
<%
ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
for(Board board : boards){
%>
<tr>
<td style="height:20px">
<%=board.getEmail() %>
<%=board.getDate() %>
<%=board.getTime() %>
</td>
</tr>
<tr>
<td style="height:20px">
<%=board.getContent() %>
<a href='modify?vno=<%=board.getVno() %>'> [수정] </a><br>
</td>
</tr>
<tr>
<td style="height:15px">
</td>
</tr>
<%} %>
</table>
</body>
</html>