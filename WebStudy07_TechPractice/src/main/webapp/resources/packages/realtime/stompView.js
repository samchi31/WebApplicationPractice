/**
 * https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
 * https://stomp-js.github.io/guide/stompjs/using-stompjs-v5.html
 * https://spring.io/guides/gs/messaging-stomp-websocket/
 * 
 */
function makeStompClient(){
	let $controlBtn = this;
	
	//==========STOMP CONNECT 메시지 헤더에 CSRF 토큰을 포함시킴. template.jsp 참고========
	let csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	let csrfToken = $("meta[name='_csrf']").attr("content");
	let connectHeaders = {};
	connectHeaders[csrfHeaderName] = csrfToken;
	//==========================================================================
	const client = new StompJs.Client({
		webSocketFactory:function(){
			let url = $controlBtn.data('url');
			let protocol = location.protocol.endsWith("s:")?"wss:":"ws:";
			let host =  location.host;
			let wsURL = protocol+"//"+host+url;
			return new WebSocket(wsURL);
		}
		, connectHeaders : connectHeaders
		, debug: function (str) {
			console.log(str);
		}
		, reconnectDelay: 5000
		, heartbeatIncoming: 4000
		, heartbeatOutgoing: 4000
	});
	
	let roomId = $controlBtn.data("roomId");
	
	client.onConnect = function(frame){
		let subscribeHeaders = {};
		
		const subscriptionEcho = this.subscribe(
			`/user/queue/${roomId}/echo`
			, subscribeEchoMessage.bind(this, $messageArea)
			, subscribeHeaders
		);
		const subscriptionBroad = this.subscribe(
				`/topic/${roomId}/echo`
				, subscribeEchoMessage.bind(this, $messageArea)
				, subscribeHeaders
		);
		const subscriptionInit = this.subscribe(`/app/mockChat/${roomId}/entering`, function(frame){
			console.log(`수신한 다이렉트 SUBSCRITION FRAME : \n ${frame} 다이렉트 메시지 : ${frame.body} `);
		});
		const subscriptionChatting = this.subscribe(
				`/topic/mockChat/${roomId}/chatting`
				, subscribeChattingMessage.bind(this, $messageArea)
				, subscribeHeaders
		);
		
		client.subscribeList = [subscriptionEcho,subscriptionInit,subscriptionChatting];
	}
	client.onChangeState = function(state){
		
		if(state === StompJs.ActivationState.ACTIVE){
			$controlBtn.prop("disabled", function(){
				return $(this).hasClass("beforeConnect");
			});
		}else if(state === StompJs.ActivationState.INACTIVE){
			$controlBtn.prop("disabled", function(){
				return $(this).hasClass("afterConnect");
			});
		}
	}
	client.onStompError = function(frame){
		console.log('Broker reported error: ' + frame.headers['message']);
		console.log('Additional details: ' + frame.body);
	}
	
	client.activate();
	
	return client;
}

function publishMessage($controlBtn, destination, $messageArea){
	let client = this;
	let messageHeaders = {};
	let textIpt = $controlBtn.filter("[name][type='text']");
	let message = textIpt.val();
	textIpt.val("");
	if(!client?.connected || !message) return false;
	// 메시지 발행시 프레임 헤더를 이용해 메타데이터를 포함시킬 수 있음.
	client.publish({
		destination : destination
		, body : message
		, headers : messageHeaders
	});
	$messageArea.append(
		$("<p class='text-end mt-3'>")
			.html(
				$("<span>")
					.addClass("bg-warning mine p-2")
					.html(message)
			)
	);
}


function subscribeEchoMessage($messageArea, frame){
	let client = this;
	let message = frame.body;
	$messageArea.append(
		$("<p class='text-start mt-3'>")
			.html(
				$("<span>")
					.addClass("bg-light other p-2")
					.html(`echoed : ${message}`)
			)
	);
}

function subscribeChattingMessage($messageArea, frame){
	let client = this;
	let messageMap = JSON.parse(frame.body);
	$messageArea.append(
			$("<p class='text-start mt-3'>")
			.html(
					$("<span>")
					.addClass("bg-light other p-2")
					.html(`${messageMap.sender} : ${messageMap.message}`)
			)
	);
}

function quit(){
	let client = this;
	for(let subscription of client.subscribeList){
		subscription.unsubscribe();
	}
	client?.deactivate();
}

let $messageArea = $("#messageArea");

let $controlBtn = $(".controlBtn").on("click", function(event){
	let roomId = $controlBtn.data("roomId");
	console.log(`room id : ${roomId}`)
	
	if(event.target.id==='connBtn'){
		$controlBtn.data("stomp", makeStompClient.call($controlBtn));
		
	}else if(event.target.id==='echoBtn'){
		let client = $(this).data("stomp");
		publishMessage.call(client, $controlBtn, `/app/${roomId}/echo`, $messageArea);
		
	}else if(event.target.id==='broadBtn'){
		let client = $(this).data("stomp");
		publishMessage.call(client, $controlBtn, `/topic/${roomId}/echo`, $messageArea);
		
	}else if(event.target.id==='mockChatBtn'){
		let client = $(this).data("stomp");
		publishMessage.call(client, $controlBtn, `/app/mockChat/${roomId}/chatting`, $messageArea);
		
	}else if(event.target.id==='disconnBtn'){
		let client = $(this).data("stomp");
		quit.call(client);
	}
	
});

$(window).on("beforeunload", function(){
	let client = $controlBtn.data("stomp");
	console.log(`connected : ${client?.connected}, state : ${client?.state}`)
	quit.call(client);
});