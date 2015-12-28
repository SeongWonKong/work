<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 등록</title>
</head>
<body>
<div style="margin:0 auto; width:500px; text-align:center;">
<h1>방명록 등록</h1>
<form action='add' method='post'>
이메일 : <input type='text' name='email'> 비밀번호 : <input type='password' name='password'><br><br>
내용 : <input type='text' name='content' style="width:400px;"><br><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>
</div>
</body>
</html>