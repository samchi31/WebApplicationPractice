<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! 
  // java code (class 안)
  String variable; // 전역변수 
  private void testMethod(){}	// 메소드 정의 가능
%>

<h4> request parameter param1 : <%=request.getParameter("param1") %> </h4>
<h4> request attribute attr1 : <%=request.getAttribute("attr1") %> </h4>
<h4> session attribute attr2 : <%=session.getAttribute("attr2") %> </h4>
  <h4>JSP spec</h4>
  <pre>
  	: 서블릿 스펙에서 파생된 하위 스펙, 템플릿 기반의 스크립트형태를 가진 스펙
  	
  	JSP 소스 표준 구성 요소
  	1. 정적 텍스트 : 일반 텍스트, HTML, JavaScript, CSS
  	2. backend script code 
  	  1) scriptlet : 
  	  	<% // java code (service 메소드 안)
  	  	  String data = "데이터";	// 지역변수
  		  Date now = new Date();
  	  	%>
  	  2) directive : <!-- 전처리구문,  응답페이지 부가설명 -->
  	  			JSP 페이지에 대한 부가설정이나 전처리 구문에 사용되며, 
  	  				지시자의 이름과 속성들의 형태로 사용됨
  	  			page(required) : 페이지에 대한 환경설정
  	  			include(optional) : 정적 include
  	  			taglib(optional) : 커스텀 태그 로딩
<%--   	  <%@ page import="java.util.Date" %>  --%>
  	  3) expression : <%=data %>, <%=now %>
  	  4) declaration : 대부분 코드 상단에 위치함
  	    <%-- <%! 
  	      // java code (class 안)
  	      String variable; // 전역변수 
  	      private void testMethod(){}	// 메소드 정의 가능
  	    %> --%>
  	  5) comment : <%-- --%>
 	    - client side comment : HTML, JS, CSS -> 브라우저에 의해 주석처리됨 (브라우저에서 주석내용을 확인 가능)
<!--   	    comment -->
		- server side comment : java, jsp -> 응답데이터에 포함안됨
		<% // java comment %>
		<%-- jsp comment --%>
	3. action tag
	4. EL(표현언어)
	5. JSTL
	<%
		if(1==1)
			throw new RuntimeException("강제발생 예외");
	%>
  </pre>
  
<script type="text/javascript">
//   comment
  console.log("body 렌더링 완료");
  console.log($("body"));
</script>
