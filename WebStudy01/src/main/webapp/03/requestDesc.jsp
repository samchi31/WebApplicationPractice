<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>request (HttpServletRequest)</h4>
<form action="" method=""></form>
<pre>
	Http의 요청 패키징 방식
		: 자원에 대한 식별 + 자원에 대한 행위 정보를 기본으로 함
	
	1. Request Line : URI, Http(Request) Method
		request Method : 행위, 의도(목적)
		<!-- 표준 메서드 : 모든 서버가 지원 -->
		GET(R)
		POST(C)
		<!-- 비표준 메서드 --> 
		PUT/PATCH(U)	// PATCH : 동적으로 필요한 부분만 수정, PUT : 전부 수정 -> 구현쉬움
		DELETE(D)
		HEAD : 응답데이터의 패키징 구조(LINE + HEADER)	// 요청에 대한 응답데이터의 body가 없는 응답을 받고 싶을 때의 요청
		OPTION : 현재 서버가 특정 메소드를 지원하는지 여부를 확인하기 위한 사전 요청(preFlight request)에 사용
		TRACE : 서버 디비깅 용도로 제한적으로 사용
		
		ex) <!-- /member/memberInsert.do <- 예전 uri -->			
			- RESTful URI (자원 식별과 행위를 분리하자) (응답데이터는 JSON/XML 로 자원 표현)
			/member 	 GET : 전체회원 조회
			/member/a001 GET : 회원 한명 조회
			/member/a001 PUT : 회원 한명 정보 전체 수정
			/member/a001 DELETE : 회원 한명 삭제
			/member 	 POST
			-> URI는 하나 method마다 트랜잭션이 달라짐
			<!-- (응답이 html인 경우 ui 정보가 들어가니까 RESTful이라고 할 수 없다) -->
			<%
				String requestURI = request.getRequestURI();
				StringBuffer requestURL = request.getRequestURL();
				String method = request.getMethod();
			%>
			requestURI : <%=requestURI %>
			requestURL : <%=requestURL %>
			request method : <%=method %>
	2. Request Header : 클라이언트에 대한 부가정보(meta data)
					  : 이름-값 의 쌍으로 구성된 "문자" 데이터
		<%
			String userAgent = request.getHeader("User-Agent");
			out.println(userAgent);
			// Enumeration = iterator : Collection 뷰
			Enumeration<String> headers =  request.getHeaderNames(); 
		%>
	3. Request Body(optional) : POST, PUT
		: 클라이언트가 서버로 보내는 컨텐츠 영역 (Content-body, Message-Body)
		<%=request.getInputStream().available() %>
</pre>
<table border='1'>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
	<%
	while(headers.hasMoreElements()){
		String headerName = headers.nextElement();
	%>
	<tr>
		<td><%=headerName %></td>
		<td><%=request.getHeader(headerName) %></td>
	</tr>
	<%
	}
	%>
	</tbody>
</table>

</body>
</html>