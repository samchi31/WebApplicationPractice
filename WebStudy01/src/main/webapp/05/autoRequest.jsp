<%@page import="java.util.Locale"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- refresh header를 client side로 설정하는 또다른 방법 -->
<!-- <meta http-equiv="Refresh" content="5;url=http://www.naver.com"> -->
<title>05/autoRequest.jsp</title>
<style type="text/css">
.disabled{
	display : none;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h4>Refresh 헤더 활용</h4>
<%--
	// server side에서 설정방법
	// Refresh : 전제 화면 전체에 lock을 건다 (동기요청 , 클라이언트 상태 유지 안됨)
	response.setIntHeader("Refresh", 1/*seconds*/);
--%>

<pre>
	서버의 갱신 데이터 확보 (동기요청구조)
	1. Refresh Response header
	2. meta tag
	3. reload
</pre>

<h4>현재 서버의 시간 : <span id="timeArea" ><%-- <%=new Date() %> --%></span></h4>
<!-- <input type="text" placeholder="기록필드" /> -->
<button class="controlBtn " data-control-type="START">시작</button>
<button class="controlBtn disabled" data-control-type="STOP" >멈춤</button>

<input type="radio" name="dataType" data-data-type="json"/>JSON
<input type="radio" name="dataType" data-data-type="text"/>PLAIN

<input type="radio" name="locale" value="<%=Locale.KOREAN.toLanguageTag() %>" checked/>한국어
<input type="radio" name="locale" value="<%=Locale.ENGLISH.toLanguageTag() %>" />영어
<input type="radio" name="locale" value="<%=Locale.JAPANESE.toLanguageTag() %>" />일어

<script type="text/javascript">
// refresh header를 설정하는 또다른 방법
// 	setTimeout(() => {
// 		location.reload();
// 	}, 1000);
	
	let timeArea = $('#timeArea');
	
	let radioBtns = $('[type="radio"]');
	let dataTypes = radioBtns.filter("[name=dataType]");
	let locales = radioBtns.filter("[name=locale]");
	
	let successes = {
			json : function(resp){
				console.log(resp.now);
				timeArea.html(resp.now);
			},
			text : function(plain){
				console.log(plain);
				timeArea.html(plain);
			}
	};
	
	let sendRequest = function(){
		let locale = locales.filter(":checked").val();
		let sendData = {};
		if(locale){
			sendData.locale = locale;
		}
		let dataType = dataTypes.filter(":checked").data("dataType");
		if(!dataType){
			dataType = 'json';
			dataTypes.filter("[data-data-type=json]").prop("checked",true);
		}
		$.ajax({
			url : "${pageContext.request.contextPath }/05/getServerTime",
			data : sendData,
			dataType : dataType,
			success : successes[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	};
 	
 	// 1단계 : 컨트롤 버튼에 대한 클릭 이벤트
 	// 		컨트롤 버튼 타입이 START면 시계작동
 	// 		컨트롤 버튼 타입이 STOP면 시계멈춤
 	let controlBtns = $('.controlBtn').on('click',function(event){
 		// $(this).prop("disabled",true);
 		controlBtns.toggleClass("disabled");
		let controlType = $(this).data('controlType');
		if(controlType == 'START' ) {
			let jobId = setInterval(sendRequest, 1000);
			timeArea.data('jobId', jobId);
		} else if(controlType == 'STOP' ){
			let jobId = timeArea.data('jobId');
			if(jobId){
				clearInterval(jobId);
				timeArea.data('jobId', null);
			}
		}
	});
 	// 2단계 : dataType 라디오 버튼의 선택 조건에 따라 비동기 요청 헤더(Accept) 설정
 	// 3단계 : locale 라디오 버튼의 선택 값에 따라 비동기 요청의 locale 파라미터가 결정됨
</script>
</body>
</html>