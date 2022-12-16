<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/bufferDesc.jsp</title>
</head>
<body>
<h4>out : JspWriter</h4>
<pre>
	출력 버퍼 관리자
	현재 버퍼의 크기 : <%=out.getBufferSize() %>
	잔여 버퍼의 크기 : <%=out.getRemaining() %>
	auto flush 지원 여부 : <%=out.isAutoFlush() %>
	<%
		for(int i=0;i<=100;i++){
			out.println(i+"번째 반복");
			if(out.getRemaining()<50){
				out.flush();
			}
			if(i==100){
				//throw new RuntimeException("강제 발생 예외"); // 버퍼 잘못 제어
				// request.getRequestDispatcher("/02/standard.jsp").include(request, response);
				// forward 버퍼를 지우고 응답함 -> 에러
				// include 버퍼에 다른 버퍼를 추가하여 응답함 -> 에러안남
				pageContext.include("/02/standard.jsp", false); // 방출하고 include, include 하고 방출 여부 제어 가능
			}
		}
	%>
</pre>
</body>
</html>