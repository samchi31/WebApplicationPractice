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
	request.setCharacterEncoding("UTF-8");
	String userId = request.getParameter("id");
	String password = request.getParameter("passwd");
	
	// 호스트명
	String hostValue = request.getHeader("host");
	// 설정된언어
	String alValue = request.getHeader("accept-language");
	%>
	<p>아이디: <%=userId %></p>
    <p>비밀번호: <%=password %></p>
    <p>호스트명: <%=hostValue %></p>
    <p>설정된언어: <%=alValue %></p>
</body>
</html>