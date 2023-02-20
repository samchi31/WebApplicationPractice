<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<h4>WebSocket + SockJS-client</h4>
<pre>
	websocket-context.xml, RealtimeTextWebSocketHandler, websocketView.js 참고
	여러개의 브라우저로 동시 테스트함. 
</pre>
<div class="row mt-3">
	<div class="col-auto">
		<input class="btn btn-primary controlBtn beforeConnect" type="button" value="connect" id="connBtn"
			data-url="${pageContext.request.contextPath }/realtime/full/sockJS"
		/>
		<input class="btn btn-danger controlBtn afterConnect" type="button" value="disconnect" id="disconnBtn" disabled />
	</div>
	<div class="col-auto">
		<input type="text" class="form-control controlBtn afterConnect" name="text" disabled id="textIpt"/>
	</div>
	<div class="col-auto">
		<input class="btn btn-success controlBtn afterConnect" type="button" value="SEND" id="sendBtn" disabled />
	</div>
</div>  
<div id="messageArea" style="width: 400px;">
</div>
<script src="${pageContext.request.contextPath }/resources/js/sockjs-client/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/packages/realtime/websocketView.js"></script>