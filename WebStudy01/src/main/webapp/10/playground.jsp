<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>네이버 크로울링 결과</h4>
SOP(Same Origin Policy) : 현재 페이지 출처와 요청의 출처가 같아야 함
 -> proxy 기법
 
<form action="<c:url value='/10/proxyTarget.jsp' />" id="crawlerForm">
	<input type="url" name="target" placeholder="https://www.naver.com" />
	<input type="checkbox" name="source" value="true" />소스로보기
	<input type="submit" value="가져오기" />
</form> 
<div id="container">


</div>

<script type="text/javascript">

let container = $("#container");
// $.ajax({
// 	url : "<c:url value='/10/proxyTarget.jsp' />",
// 	method : "get",
// 	dataType : "html",
// 	success : function(resp) {
// // 		let content = $(resp).find("div:first").html();
// // 		container.html(content);
// 	},
// 	error : function(jqXHR, status, error) {
// 		console.log(jqXHR);
// 		console.log(status);
// 		console.log(error);
// 	}
// });

let target = $('[name=target]');
let source = $('[name=source]');
$('#crawlerForm').on('submit',function(event){
	event.preventDefault();
	
	container.load(this.action, $(this).serialize());	// html, success 시 출력만 할 떄
// 	$.ajax({
// 		url : this.action,
// 		data : $(this).serialize(),
// 		method : this.method,
// 		dataType : "html",
// 		success : function(resp) {
// // 			console.log(resp);
// 			container.html(resp);
// 		},
// 		error : function(jqXHR, status, error) {
// 			console.log(jqXHR);
// 			console.log(status);
// 			console.log(error);
// 		}
// 	});
	return false;
});

</script>
</body>
</html>