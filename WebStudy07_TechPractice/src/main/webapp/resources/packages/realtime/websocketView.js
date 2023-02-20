/**
 * https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
 * https://www.npmjs.com/package/sockjs-client
 * 
 */
function connect(){
	let sockjs = new SockJS(this.dataset.url);
	sockjs.onopen=function(event){
		console.log(`${this.url} 연결 수립`);
		$controlBtn.prop("disabled", function(){
			return $(this).hasClass("beforeConnect");
		});
	}
	sockjs.onclose=function(){
		console.log(`${this.url} 연결 종료`);
		$controlBtn.prop("disabled", function(){
			return $(this).hasClass("afterConnect");
		});
	}
	sockjs.onmessage=receiveMessage.bind(sockjs, $messageArea);
	return sockjs;
}
function sendMessage(textIpt, $messageArea){
	let sockjs = this;
	let message = textIpt.val();
	if(sockjs.readyState!==SockJS.OPEN || !message) return false;
	
	sockjs.send(message);
	$messageArea.append(
		$("<p class='text-end mt-3'>")
			.html(
				$("<span>")
					.addClass("bg-warning mine p-2")
					.html(message)
			)
	);
	textIpt.val("");
}
function receiveMessage($messageArea, event){
	let sockjs = this;
	console.log(event); // 수신 메시지의 출처나 부가 정보가 없음. 때문에 메시지 자체를 일정한 형식으로 설계해야함.
	$messageArea.append(
		$("<p class='text-start mt-3'>")
			.html(
				$("<span>")
					.addClass("bg-light other p-2")
					.html(event.data)
			)
	);
}

let $messageArea = $("#messageArea");

let $controlBtn = $(".controlBtn").on("click", function(event){
	if(event.target.id==='connBtn'){
		$controlBtn.data("sockjs", connect.call(this));
	}else if(event.target.id==='sendBtn'){
		let sockjs = $(this).data("sockjs");
		sendMessage.call(sockjs, $("#textIpt"), $messageArea);
		
	}else if(event.target.id==='disconnBtn'){
		let sockjs = $(this).data("sockjs");
		if(sockjs?.readyState < SockJS.CLOSING) sockjs?.close();			
	}		
});

$(window).on("beforeunload", function(){
	let sockjs = $controlBtn.data('sockjs');
	if(sockjs?.readyState < SockJS.CLOSING) sockjs?.close();
});