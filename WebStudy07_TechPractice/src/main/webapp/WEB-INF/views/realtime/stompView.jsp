<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<h4>WebSocket + SockJS-client + STOMP </h4>
<h4>dummy chatting room id : chatRoom305</h4>
<pre>
	websocket-context.xml, StompMessageController, StompWebSocketSessionListener, stompView.js 참고
	1. 로그인하지 않은 상태에서 여러개의 브라우저로 테스트
	2. 세개의 브라우저에 각각 (a001/java ,  b001/java , c001/java) 계정으로 로그인한 상태에서 테스트함.
</pre>
<div class="row mt-3">
	<div class="col-auto">
		<input class="btn btn-primary controlBtn beforeConnect" type="button" value="connect" id="connBtn"
			data-room-id="chatRoom305"
			data-url="${pageContext.request.contextPath }/realtime/full/stomp/chatRoom305"
		/>
		<input class="btn btn-danger controlBtn  afterConnect" type="button" value="disconnect" id="disconnBtn" disabled />
	</div>
	<div class="col-auto">
		<input type="text" class="form-control controlBtn  afterConnect" name="text" disabled id="textIpt"/>
	</div>
	<div class="col-auto">
		<input class="btn btn-success controlBtn  afterConnect" type="button" value="ECHO" id="echoBtn" disabled />
	</div>
	<div class="col-auto">
		<input class="btn btn-success controlBtn  afterConnect" type="button" value="BROADCAST" id="broadBtn" disabled />
	</div>
	<security:authorize access="isAuthenticated()">
		<div class="col-auto">
			<input class="btn btn-success controlBtn  afterConnect" type="button" value="MockChatting" id="mockChatBtn" disabled />
		</div>
	</security:authorize>
</div>  
<div id="messageArea" class="col-6">
</div>
<script src="${pageContext.request.contextPath }/resources/js/stompjs/stomp.umd.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/realtime/stompView.js"></script>