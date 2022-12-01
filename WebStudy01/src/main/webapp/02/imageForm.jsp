<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.FileFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  File folder = new File(application.getInitParameter("imageFolder"));
//   File[] imageFiles = folder.listFiles( (dir, name) -> {
// 	  String mime = application.getMimeType(name);
// 	  System.out.println(name);
// 	  return mime != null && mime.startsWith("image/");
//   });

  File[] imageFiles = folder.listFiles(new FilenameFilter(){	  
	  @Override
	  public boolean accept(File dir, String name){
		  String mime = application.getMimeType(name);
		  return mime!= null && mime.startsWith("image/");
	  }
  });
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="">
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