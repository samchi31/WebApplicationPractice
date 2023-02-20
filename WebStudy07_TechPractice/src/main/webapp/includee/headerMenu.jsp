<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap w-100 p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="${pageContext.request.contextPath }">Practice</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/etc/procedural">1. Procedure/Function(RESTful)</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/etc/calculate">2. Calculator(RESTful+CSRF)</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/uiplugin/fancyTree">3. UI Plugins</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/file">4. File Download</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/realtime/websocket">5. Realtime Full Duplex</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link text-white" href="${pageContext.request.contextPath }/scheduling/async">6. Scheduling</a>
    </li>
  </ul>
  <ul class="nav px-3 col-2">
  	<security:authorize access="isAuthenticated()">
  		<security:authentication property="principal" var="authMember"/>
		<li class="nav-item text-nowrap">
			<a class="nav-link text-white">${authMember.username }</a>
   		</li>
		<li class="nav-item text-nowrap">
			<form:form name="logoutForm" action="${pageContext.request.contextPath }/logout" method="post"></form:form>
      		<a class="nav-link text-white logoutBtn" href="javascript::">Sign out</a>
      		<script type="text/javascript">
      			$(document).on("click", ".logoutBtn", function(event){
      				event.preventDefault();
      				document.logoutForm.submit();
      				return false;
      			});
      		</script>
   		</li>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		 <li class="nav-item text-nowrap">
	       <a class="nav-link text-white" href="${pageContext.request.contextPath }/login">Sign in</a>
	     </li>
	</security:authorize>	
  </ul>
</nav>