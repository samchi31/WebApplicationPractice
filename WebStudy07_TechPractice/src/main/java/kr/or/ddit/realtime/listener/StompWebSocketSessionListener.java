package kr.or.ddit.realtime.listener;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StompWebSocketSessionListener {
	@Inject
	private SimpMessagingTemplate messagingTemplate;
	
	@EventListener(value=SessionConnectedEvent.class)
	public void connectListener(SessionConnectedEvent event) {
		log.info("connected -->  {}", event.getMessage());
	}
	@EventListener(value=SessionDisconnectEvent.class)
	public void disConnectListener(SessionDisconnectEvent event) {
		log.info("disconnect -->  {}", event.getMessage());
	}
	
	@EventListener(value=SessionSubscribeEvent.class)
	public void subscribeListener(SessionSubscribeEvent event) {
		Message<?> message = event.getMessage();
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);
		String destination = accessor.getDestination();
		
		log.info("{}, destination --> {}", accessor.getMessageType(), destination);
		
		if(destination.contains("chat")) {
			Authentication authentication = (Authentication) accessor.getUser(); 
			String id = Optional.ofNullable(authentication)
					.filter(a-> !(a instanceof AnonymousAuthenticationToken))
					.map(Authentication::getName)
					.orElse(accessor.getSessionId());
			
			messagingTemplate.convertAndSend(destination, String.format("%s 입장", id));
		}
	}
	
	@EventListener(value=SessionUnsubscribeEvent.class)
	public void unSubscribeListener(SessionUnsubscribeEvent event) {
		Message<?> message = event.getMessage();
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);
		String destination = accessor.getDestination();
		
		log.info("{}, destination --> {}", accessor.getMessageType(), destination);
		
		if(destination.contains("chat")) {
			Authentication authentication = (Authentication) accessor.getUser(); 
			String id = Optional.ofNullable(authentication)
					.filter(a-> !(a instanceof AnonymousAuthenticationToken))
					.map(Authentication::getName)
					.orElse(accessor.getSessionId());
			if(destination!=null)					 
				messagingTemplate.convertAndSend(destination, String.format("%s 퇴장", id));
		}
	}
}
