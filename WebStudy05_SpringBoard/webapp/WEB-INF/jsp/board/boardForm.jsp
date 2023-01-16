<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>

<form:form modelAttribute="board" enctype="multipart/form-data" method="post">
<table>
	<tr>
		<th>게시글 제목</th>
		<td>
			<form:input path="boTitle" cssClass="form-control"/>
			<form:errors path="boTitle"/>
		</td>
	</tr>
	<tr><th>작성자</th><td><input class="form-control" type="text"  required name="boWriter" value="${freeboard.boWriter}" /><span class="text-danger">${errors.boWriter}</span></td></tr>
	<tr><th>아이피</th><td><input class="form-control" type="text"  required name="boIp" value="${pageContext.request.remoteAddr}" readonly/><span class="text-danger">${errors.boIp}</span></td></tr>
	<tr><th>이메일</th><td><input class="form-control" type="text" name="boMail" value="${freeboard.boMail}" /><span class="text-danger">${errors.boMail}</span></td></tr>
	<tr><th>비밀번호</th><td><input class="form-control" type="password"  required name="boPass" value="${freeboard.boPass}" /><span class="text-danger">${errors.boPass}</span></td></tr>
	<tr>
		<th>게시글 내용</th>
		<td>
			<form:textarea path="boContent"/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<input type="file" name="boFiles"/>
			<input type="file" name="boFiles"/>
			<input type="file" name="boFiles"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" class="btn btn-success"/>
		</td>
	</tr>
	
</table>
</form:form>

<script>

CKEDITOR.replace('boContent');

</script>