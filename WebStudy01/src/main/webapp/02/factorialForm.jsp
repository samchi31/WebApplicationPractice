<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
// 	$(document).ready(function(){})
// 	$(documnet).on('ready',function(){})
	$(function(){
		let resultArea = $('#resultArea');	// 비동기 통신할 때마다 id를 찾아 travelsing? 하므로 부하가 심해진다 -> 미리 변수로 선언
		
		$("form[name]").on('submit', function(event){	// click 이벤트 후 submit 이벤트 실행 , submit 이벤트를 수정 후 서버요청은 중단시킴
			event.preventDefault();	// submit의 동기 요청을 중단
// 			console.log(this);
// 			console.log($(this));
// 			console.log($(this)[0]);
// 			console.log($(this).get(0));

			let url = this.action;
			let method = this.method;	// 없으면 기본값 get
			let data = $(this).serialize();
			console.log(data);
			
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "html", // json인 경우 unmashaling된 html 코드가 resp로
				success : function(resp) {
					console.log(resp);
					resultArea.html(resp);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
			
			return false;	// submit의 동기 요청을 중단
		});
	});
</script>
</head>
<body>
<form name="facForm" action="<%=request.getContextPath() %>/02/factorial.do">
	<input type="number" name="number"/>
	<input type="submit" value="전송"/>
	<input type="reset" value="취소"/>
	<input type="button" value="버튼"/>	
</form>
<div id="resultArea">

</div>
</body>
</html>