<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<h4>WELCOME</h4>
<security:authorize url="/mypage.do">
	<security:authentication property="principal" var="memberVOWrapper"/>
	<security:authentication property="principal.realMember" var="authMember"/>
	<c:if test="${not empty authMember.memImg }">
		<img class="profile" src="data:image/*;base64,${authMember.base64MemImg }" />
	</c:if>
	<c:if test="${empty authMember.memImg }">
		<img class="profile" src="<c:url value='/resources/images/noImage.png'/>" />
	</c:if>
	<a href="<c:url value='/mypage.do'/>">${authMember.memName }[${authMember.memRole }]님</a>
	<a href="#" class="logoutBtn">로그아웃</a>
</security:authorize>
<security:authorize url="/member/memberInsert.do">
	<a href="<c:url value='/login'/>">로그인</a>
	<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
</security:authorize>
