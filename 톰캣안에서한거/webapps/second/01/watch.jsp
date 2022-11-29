<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>

<html>
<style>
 h4{
	 background-color:yellow;
 }
</style>
<body>
<h4>한글 컨텐츠</h4>
<h4>current context name : <%=request.getContextPath() %></h4>
<h4>last recieved server time : <%=new Date() %></h4>
<h4 id='id_h4'></h4>

<img src="http://localhost/second/image.do" />

<video>
 <source src="http://localhost/second/Video.do" type="video/mp4"/>
</video>

<script>
setInterval(function(){
let today = new Date();
document.querySelector('#id_h4').innerHTML = 'current client time : '+today;
},1000);
</script>
</body>
</html>