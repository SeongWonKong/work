<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function CheckEmail(){
	var test_email = "^[_A-Za-z0-9\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	if(document.addform.email.value.match(test_email)==null){
		alert("올바른 E-mail 주소가 아닙니다");
		document.addform.email.focus();
		return;
	}else{
		return true;
	}
}
</script>
<title>방명록 등록</title>
</head>
<body>
<div style="margin:0 auto; width:500px; text-align:center;">
<h1>방명록 등록</h1>
<form name="addform" action='add' method='post'>
이메일 : <input type='text' name='email' onBlur="CheckEmail()"> 비밀번호 : <input type='password' name='password'><br><br>
내용 : <input type='text' name='content' style="width:400px;"><br><br>
<input type='submit' value='등록'>
<input type='reset' value='취소'>
</form>
</div>
</body>
</html>