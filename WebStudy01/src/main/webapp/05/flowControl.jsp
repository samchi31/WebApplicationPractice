<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
<!-- 동적내포 -->
<%-- <jsp:include page="/includee/preScript.jsp"></jsp:include> --%>
<!-- 정적내포 -->
<%@ include file="/includee/preScript.jsp" %>
<%=varOnPre %> <!-- 정적내포일때만 사용가능 -->
</head>
<body>
<h4>흐름 제어 방법</h4>
<pre>
	Http : Connectless, Stateless
	A -> B 이동방식
	1. 요청 분기(request dispatch) : A를 대상으로한 최초의 요청이 계속 유지됨 (서버안에서만 이동)
		1) forward(Model2) : A(request 처리, controller) -> B(response 생성, view) 에서 이동 후의 최종 응답은 B에서 전송
		2) include(페이지 모듈화) : A -> B -> A 최종 응답에 A + B의 모든 데이터가 포함됨
			내포되는 시점과 내포되는 대상에 따라 분류됨
			- 정적 내포 : 컴파일전에 소스가 파싱되는 단계에서 소스파일이 내포됨
			- 동적 내포 : 실행 시(runtime)에 실행의 결과 데이터가 내포됨
		<%
			request.setAttribute("attr1", new Date());	// parameter data type : string, attribute : object
			String path = "/02/standard.jsp"; // 서버는 이 주소를 모른다
			//request.getRequestDispatcher(path).forward(request, response);
			//request.getRequestDispatcher(path).include(request, response);
			pageContext.include(path);
		%>
	2. Redirect : 
		A -> response body 가 없고, Line(302) + Header(Location) 로만 구성된 응답이 전송
		-> Location 방향으로 새로운 요청을 전송함 -> B 에서 Body를 가진 최종 응답이 전송됨
		<%--
			session.setAttribute("attr2", "세션 속성");
			String location = request.getContextPath() + path;
			response.sendRedirect(location/* +"?param1="+request.getParameter("param1") */);
		--%>
</pre>
</body>
</html>