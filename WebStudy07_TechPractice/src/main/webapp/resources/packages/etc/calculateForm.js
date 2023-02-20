/**
 * 
 */
$("#calForm").on("submit", function(event){
	// this : form tag (HTMLFormElement)
	event.preventDefault();
	
	let $resultArea = $(this.dataset.target);
	$resultArea.empty();
	let url = this.action;
	let method = this.method;
	let data = $(this).serializeObject();
	data.leftOp = parseInt( data.leftOp );
	data.rightOp = parseInt( data.rightOp );
	
	//==========POST 요청 헤더에 CSRF 공격 방어를 위한 토큰을 포함시킴, template.jsp 참고========
	
	let csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	let csrfToken = $("meta[name='_csrf']").attr("content");
	let headers = {};
	headers[csrfHeaderName] = csrfToken;
	$.ajaxSetup({
		headers:headers
	});
	
	//==========================================================================
	
	$.ajax({
		url : url,
		method : method,
		prcessData:false,
		contentType:"application/json",
		data : JSON.stringify(data),
		headers:{
			"Content-Type":"application/json"
		}, // $.ajaxSetup 으로 일괄 설정 가능.
		dataType : "json",
	}).done((resp, status, jqXHR)=>{
		// this : form tag (HTMLFormElement)
		let $resultArea = $(this.dataset.target);
		$resultArea.html( resp.expression );
	}).fail((jqXHR, status, error)=>{
			console.log(jqXHR);
			console.log(status);
			console.log(error);
	});
	
	return false;
});