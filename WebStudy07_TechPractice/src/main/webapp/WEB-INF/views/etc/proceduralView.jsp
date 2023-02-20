<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h4>Procedural.xml, ProceduralController, prodecuralView.js 참고</h4>
<div class="mt-3">
	<input id="callFunc" type="button" class="btn btn-primary" value="Function 실행 후  cartNo 생성" 
		data-url="<c:url value='/etc/procedural/callFunction'/>"
	/>
	<input id="callProc" type="button" class="btn btn-primary" value="Procedure 실행 후  2020년 4월 구매왕 조회" 
		data-url="<c:url value='/etc/procedural/callProcedure'/>"
	/>
	<input id="caseError" type="button" class="btn btn-danger" value="비동기 요청에서 에러 메시지 처리 케이스" 
		data-url="<c:url value='/genError/hasNoController'/>"
	/>
	
	<div id="resultArea" class="border bg-success col-md-8 fs-1 mt-3" style="--bs-bg-opacity: .5;"></div>
	<div id="errorArea" class="border bg-danger col-md-8 fs-1 mt-3" style="--bs-bg-opacity: .5;"></div>
	<div id="logArea" class="border bg-info col-md-8 fs-3 mt-3" style="--bs-bg-opacity: .5;"></div>
</div>

<script src="<c:url value='/resources/packages/etc/prodecuralView.js' />"></script>
