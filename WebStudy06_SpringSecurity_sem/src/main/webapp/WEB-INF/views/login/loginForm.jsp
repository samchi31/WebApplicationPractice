<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
	<script>
		alert("${SPRING_SECURITY_LAST_EXCEPTION.message}");
	</script>
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>

	<form method="post" action="<c:url value='/login/loginProcess.do'/>" id="loginForm">
		<security:csrfInput/>
		<div class="row mb-3">
			<div class="col-auto">
				<input type="text" name="memId" placeholder="아이디" class="form-control"/>
			</div>
			<div class="col-auto">
				<div class="form-check">
				<label><input type="checkbox" class="form-check-input" name="rememberMe" />rememberMe</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<input type="password" name="memPass" placeholder="비밀번호" class="form-control"/>
			</div>
			<div class="col-auto">
				<input type="submit" value="로그인" class="btn btn-primary"/>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col	">
				<select class="form-select" aria-label="Default select example" onchange="selectedLogin(event);">
				  <option>사용자선택</option>
				  <option data-mem-id="c001" data-mem-pass="java">관리자</option>
				  <option data-mem-id="a001" data-mem-pass="java">일반사용자</option>
				  <option data-mem-id="v001" data-mem-pass="java">탈퇴한 케이스</option>
				  <option data-mem-id="b001" data-mem-pass="javaaa">비밀번호오류 케이스</option>
				</select>
				<script>
					function selectedLogin(event){
						let target = $(event.target);
						let option = target.find("option:selected");
						let memId = option.data("memId");
						let memPass = option.data("memPass");
						if(memId && memPass) {
							loginForm.memId.value = memId;
							loginForm.memPass.value = memPass;
// 							loginForm.submit();
						}
					}
				</script>
			</div>
		</div>
	</form>
					








