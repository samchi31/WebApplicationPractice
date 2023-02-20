/**
 * 
 */
	let $msgArea = $('#msgArea');
	let $controlBtn = $(".controlBtn").on("click", function(){
		let url = this.dataset.commandUrl;
		$msgArea.load(url);
	});
	
	//==========STOMP CONNECT 메시지 헤더에 CSRF 토큰을 포함시킴. template.jsp 참고========
	let csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
	let csrfToken = $("meta[name='_csrf']").attr("content");
	let connectHeaders = {};
	connectHeaders[csrfHeaderName] = csrfToken;
	//==========================================================================
	
	const client = new StompJs.Client({
		brokerURL:(function(){
			let url = $controlBtn.data("wsUrl");
			let protocol = location.protocol.endsWith("s:")?"wss:":"ws:";
			let host =  location.host;
			let wsURL = protocol+"//"+host+url;
			return wsURL;
		})()
		, debug : function(text){
			console.log(text);
		}
		, connectHeaders : connectHeaders
		, onConnect : function(frame){
			const subscriptionSystem = this.subscribe(
					"/topic/system/noti"
					, function(frame){
						console.log(frame);
						let message = frame.body;
						$msgArea.append($("<p class='text-start mt-3'>").html(`전체 topic 으로 받은 데이터 : ${message}`));
					}
			);
			const subscriptionUser = this.subscribe(
					"/user/queue/noti"
					, function(frame){
						console.log(frame);
						let message = frame.body;
						$msgArea.append($("<p class='text-start mt-3'>").html(`개인 메시지로 받은 데이터 : ${message}`));
					}
			);
			
			client.subscribeList = [subscriptionSystem, subscriptionUser];
		}
	});
	
	client.activate();

	$(window).on("beforeunload", function(){
		let client = this;
		for(let subscription of client.subscribeList){
			subscription.unsubscribe();
		}
		client?.deactivate();
	});