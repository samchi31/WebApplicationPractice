<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/generateException.jsp</title>
</head>
<body>
<h4>예외 처리 테스트</h4>
<%
	if(1==1)
		throw new IOException("강제발생예외");
	// 이미 jsp에서 try catch로 예외를 잡고 있어서 컴파일러 에러가 안보임
	// 에러 처리은 안되어 있으니 처리해줘야함
%>
</body>
</html>