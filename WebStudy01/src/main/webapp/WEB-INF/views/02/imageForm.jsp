<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.FileFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>model1구조에서model2구조로</title>
</head>
<body>
<%

File[] imageFiles = (File[])request.getAttribute("imageFile");
%>

	<form action="<%=request.getContextPath()%>/imageStreaming.do">
	<select>
	<%
	for (File tmp : imageFiles){
	%> 
	<option><%=tmp.getName() %></option>
	<%
	}
	%> 
	</select>
	</form>
</body>
</html>