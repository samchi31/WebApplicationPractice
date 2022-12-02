<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
private int factorial(int num){
	if(num<0)
		throw new IllegalArgumentException("음수는 연산 불가");
	if(num==1)
		return 1;
	return factorial(num-1)*num;
}
%>
<%
	// _JSPService(request, response)
	String numParam = request.getParameter("number");
	if(numParam != null && !numParam.matches("\\d{1,2}")){
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h4>팩토리얼 연산 처리</h4>
  number = 10;<br>
  <form>
    <input type="number" name="number" value='<%=Objects.toString(numParam, "") %>' onchange="this.form.submit()"/>
  </form>
	<%
	  if(numParam != null){
		  int input = Integer.parseInt(numParam);
		  String pattern = "%d != %d";
		  int result = factorial(input);
		  String expr = String.format(pattern,input,result);
	%>
	<%=expr %>
	<%} %>
<script type="text/javascript">

</script>
</body>
</html>