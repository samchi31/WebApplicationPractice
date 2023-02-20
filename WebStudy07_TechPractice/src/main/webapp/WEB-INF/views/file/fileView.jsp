<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row mt-3">
	<pre>
		파일 다운로드에 사용될 수 있는 컨트롤러 케이스 (**.file.controller.FileDownloadController)
		동시에 여러개의 파일을 다운로드 받는 경우, 압축 기술의 활용 방법(**.file.util.CompressUtils)	
	</pre>
	<div class="col-6">
		<select id="singleSel" class="form-select">
		  	<option selected value>파일선택</option>
		 	<c:forEach items="${files }" var="filename">
		 		<option>${filename }</option>
		 	</c:forEach>
		</select>
	</div>
	<div class="col-2">
		<a class="btn btn-primary downloadBtn" href="${pageContext.request.contextPath}/file/single" 
			data-target="#singleSel" data-role="single">single download</a>
	</div>
</div>
<div class="row mt-3">
	<div class="col-6">
		<select id="multiSel" class="form-select" size="10" multiple>
		 	<c:forEach items="${files }" var="filename">
		 		<option>${filename }</option>
		 	</c:forEach>
		</select>
	</div>
	<div class="col-2">
		<a class="btn btn-primary downloadBtn" href="${pageContext.request.contextPath}/file/multiple" 
			 data-target="#multiSel" data-role="multiple">multiple download</a>
	</div>
</div>
<script src="${pageContext.request.contextPath }/resources/packages/file/fileView.js"></script>