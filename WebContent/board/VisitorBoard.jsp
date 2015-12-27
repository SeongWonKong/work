<%@ page import="spms.vo.Board" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
<h1>글 목록</h1>
<p><a href='add'>새로운 글 쓰기</a></p>
<%
ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
for(Board board : boards){
%>
<%=board.getVno() %>,
<%=board.getEmail() %>,
<%=board.getContent() %>,
<%=board.getDate() %>
<a href='modify?vno=<%=board.getVno() %>'> [수정] </a><br>
<%} %>

</body>
</html>