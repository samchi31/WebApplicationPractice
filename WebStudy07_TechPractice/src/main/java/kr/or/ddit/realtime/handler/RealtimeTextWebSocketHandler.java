package kr.or.ddit.realtime.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.exception.ExceptionThrowingConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealtimeTextWebSocketHandler extends TextWebSocketHandler{
	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		TextMessage message = new TextMessage(String.format("%s 세션 접속", session.getId()));
		sessionList.stream()
					.filter(s->!session.equals(s))
					.forEach(ExceptionThrowingConsumer.consumerWrapping(s->{
						s.sendMessage(message);
					}));
		log.info("연결된 세션 아이디 : {}", session.getId());
		Optional.ofNullable(session.getPrincipal())
		.ifPresent(principal->{
			log.info("연결 수립 : {}", principal.getName());
		});
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		sessionList.stream()
					.filter(s->!session.equals(s))
					.forEach(ExceptionThrowingConsumer.consumerWrapping(s->{
						s.sendMessage(message);
					}));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		TextMessage message = new TextMessage(String.format("%s 세션 퇴장", session.getId()));
		sessionList.stream()
					.forEach(ExceptionThrowingConsumer.consumerWrapping(s->{
						s.sendMessage(message);
					}));
		log.info("세션 : {} 연결 종료", session.getId());
		Optional.ofNullable(session.getPrincipal())
		.ifPresent(principal->{
			log.info("연결 종료 : {}", principal.getName());
		});
	}
	
}
