<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response</title>
</head>
<body>
  <%
  	// 설정한 URL 페이지로 강제 이동
    response.sendRedirect("http://www.google.com");
    
  %>
</body>
</html>