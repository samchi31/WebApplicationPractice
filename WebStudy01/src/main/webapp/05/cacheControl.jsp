<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
</head>
<body>
<h4>Cache 제어</h4>
<pre>
	cache 란? 시스템 내부에서 발생하는 속도 저하를 커버하기 위한 임시 저장 데이터
	Header 종류
		- Pragma(Http v1.0), Cache-Control(v1.1) : 캐싱 정책 설정용
			no-cache (저장O) : 캐시 데이터 사용 전 확인 절차를 거치도록 함
			no-store (저장X)
			must-revalidate : 시한이 만료된 캐시 데이터 사용 전 확인 절차를 거치도록 함
			public : 클라이언트, 프록시 서버에 캐싱
			private : 클라이언트
			ex)public;maxages=milliseconds
		- Expires : 캐싱 데이터 만료 시한 설정용(구체적인 날짜)
			<%
				response.setHeader("Pragma", "no-store");	//1.0
				response.setHeader("Cache-Control", "no-store");	//1.1
				
				response.addHeader("Pragma", "no-cache");	//1.0
				response.addHeader("Cache-Control", "no-cache");	//1.1
				
				response.setDateHeader("Expires", 0);	//1970-1-1,0:0:0 
			%>
	
</pre>
</body>
</html>