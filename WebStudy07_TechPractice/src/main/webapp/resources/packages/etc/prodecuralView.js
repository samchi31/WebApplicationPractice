/**
 * 
 */

// ========Layout 에서 공통 활용할 수 있는 핸들러들임. -> template.jsp, preScript.jsp, commons.js 등으로 공통으로 처리할 수 있음.======
const $logArea = $("#logArea");
const $errorArea = $("#errorArea");
$(document).ajaxError(function(event, jqXHR, settings, errorText){
	let $iframe = $("<iframe class='w-100 h-100' scrolling = 'no'>");
	$errorArea.html($iframe);
	$iframe.contents().find("body").html(jqXHR.responseText);
});
$(document).ajaxSend(function(event, jqXHR, settings){
	$resultArea.empty();
	$errorArea.empty();
	$logArea.html($("<p>").html(settings.url+" 비동기 요청 전송"));
});
$(document).ajaxComplete(function(event, jqXHR, settings){
	$logArea.append($("<p>").html("응답 수신, 상태코드 : "+jqXHR.status));
});
// ============================================================================================================

const $resultArea = $("#resultArea");
$("#callFunc").on("click", function(event){
	// this : input tag (HTMLInputElement)
	let url = $(this).data("url");
	$.getJSON(url).done((json)=>{
		$resultArea.html(`생성된 장바구니 번호 : ${json.cartNo}`);		
	});
});
$("#callProc").on("click", function(event){
	// this : input tag (HTMLInputElement)
	let url = $(this).data("url");
	$.getJSON(url).done((json)=>{
		$resultArea.html(`구매왕 ${json.pMid }의 구매금액 ${json.pSum} `);
	});
});
$("#caseError").on("click", function(event){
	// this : input tag (HTMLInputElement)
	let url = $(this).data("url");
	$resultArea.load(url);
});