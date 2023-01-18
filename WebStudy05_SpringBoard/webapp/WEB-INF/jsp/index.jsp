<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<h4>WELCOME</h4>
<h4>${pageContext.request.userPrincipal}</h4>

<security:authorize url="/mypage">
	<security:authentication property="principal" var="authMember"/>
	<a href="${pageContext.request.contextPath }/mypage">${authMember.realMember.memName }</a>
	<form name="logoutForm" action="${pageContext.request.contextPath }/login/logout" method="post">
		<security:csrfInput />
	</form>
	<a href="#" onclick="document.logoutForm.submit();">로그아웃</a>
</security:authorize>
<security:authorize access="isAnonymous()">	<!-- =!isAuthenticated() -->
	<a href="${pageContext.request.contextPath }/login/loginForm">로그인</a>
	<a href="${pageContext.request.contextPath }/member/memberInsert.do">회원가입</a>
</security:authorize>