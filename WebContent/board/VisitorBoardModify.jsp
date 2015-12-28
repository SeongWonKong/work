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

<h1>방명록 수정</h1>

<form action='modify' method='post'>
EMAIL : <input type='text' name='email' value='${board.email}' readonly><br>
암호 : <input type='password' name='password' required><br>
내용 : <input type='text' name='content' value='${board.content}'><br>
<input type='hidden' name='vno' value='${board.vno}'>
<input type='submit' value='수정하기'>
<input type='reset' value='취소'>
</form>
</div>
</body>
</html>