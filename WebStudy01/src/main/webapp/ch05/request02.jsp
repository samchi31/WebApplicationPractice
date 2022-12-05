<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object</title>
</head>
<body>
  <%
  	// 스크립틀릿
  	// 모든 헤더의 이름을 가져와 보자 => 리턴타입 : Enumeration(열거형)
  	Enumeration en = request.getHeaderNames();
    // hasMoreElements() 그 다음 값의 유무
    while(en.hasMoreElements()){
    	// 현재 헤더 이름을 가져옴(Object(먹을 것) -> String(삼겹살)으로 downcasting)
    	String headerName = (String)en.nextElement();
    	// headerName : host
    	// request.getHeader("host");
    	String headerValue = request.getHeader(headerName);
    	out.print("<p>"+headerName+":"+headerValue+"</p>");
    	
    }
  %>
</body>
</html>