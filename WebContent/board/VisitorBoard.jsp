<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
<c:forEach var="board" items="${boards}">
<tr>
<td style="height:20px">
이메일 : ${board.email}<br>
작성시간(수정) : ${board.date}
${board.time}<br>
</td>
</tr>
<tr>
<td style="height:20px">
${board.content }
<a href='modify?vno=${board.vno}'> [수정] </a><br>
</td>
</tr>
<tr>
<td style="height:15px">
</td>
</tr>
</c:forEach>
</table>

</body>
</html>