<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap w-100 p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="${pageContext.request.contextPath }">WebStudy06</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
  	<security:authorize url="/board/boardList.do">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/board/boardList.do">자유게시판</a>
	    </li>
    </security:authorize>
    <security:authorize url="/member/memberList.do">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/member/memberList.do">회원관리</a>
	    </li>
    </security:authorize>
    <security:authorize url="/prod">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/prod">상품관리</a>
	    </li>
    </security:authorize>
    <security:authorize url="/buyer/buyerList.do">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/buyer/buyerList.do">거래처관리</a>
	    </li>
    </security:authorize>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="?language=ko">한글</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="?language=en">영어</a>
    </li>
  </ul>
  
  
  <ul class="nav px-3 col-2">
  	<security:authorize url="/mypage.do">
  		<security:authentication property="principal.realMember" var="authMember"/>
			<li class="nav-item text-nowrap">
      		<a class="nav-link" href="<c:url value='/mypage.do'/>">
	      		<c:if test="${not empty authMember.memImg }">
					<img class="profile_small" src="data:image/*;base64,${authMember.base64MemImg }" />
				</c:if>
				<c:if test="${empty authMember.memImg }">
					<img class="profile_small" src="<c:url value='/resources/images/noImage.png'/>" />
				</c:if>
      		${authMember.memName }
      		</a>
   		</li>
		<li class="nav-item text-nowrap">
			<form:form name="logoutForm" action="${pageContext.request.contextPath }/logout" method="post"></form:form>
      		<a class="nav-link logoutBtn" href="javascript::">Sign out</a>
      		<script type="text/javascript">
      			$(document).on("click", ".logoutBtn", function(event){
      				event.preventDefault();
      				document.logoutForm.submit();
      				return false;
      			});
      		</script>
   		</li>
	</security:authorize>
	<security:authorize url="/member/memberInsert.do">
		 <li class="nav-item text-nowrap">
	       <a class="nav-link" href="${pageContext.request.contextPath }/login">Sign in</a>
	     </li>
		 <li class="nav-item text-nowrap">
	       <a class="nav-link" href="<c:url value='/member/memberInsert.do'/>">Regist</a>
	     </li>
	</security:authorize>	
  </ul>
</nav>