<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<table class="table table-bordered">
	<tbody>
		<tr><th>글번호</th><td>${board.boNo}</td></tr>
		<tr><th>제목</th><td>${board.boTitle}</td></tr>
		<tr><th>작성자</th><td>${board.boWriter}</td></tr>
		<tr><th>이메일</th><td>${board.boMail}</td></tr>
		<tr><th>내용</th><td>${board.boContent}</td></tr>
		<tr><th>작성일</th><td>${board.boDate}</td></tr>
		<tr><th>조회수</th><td>${board.boHit}</td></tr>
		
		<c:if test="${not empty board.attatchList }">
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
						${attatch.attFilename } ${vs.last?"" : "," }
					</c:forEach>
				</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		
	</tfoot>
</table>