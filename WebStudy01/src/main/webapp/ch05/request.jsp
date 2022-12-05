<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object (내장객체)</title>
</head>
<body>
  <!-- 
  URI : process.jsp?name=개똥이
  URL : process.jsp
  요청파라미터(HTTP 파라미터) : name = 개똥이
   -->
  <form action="process.jsp" method="post" >
    <p>이름 : <input type="text" name="name" required/> </p>
    <p><input type="submit" value="전송"/> </p>
  </form>
</body>
</html>