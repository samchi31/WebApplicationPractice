package kr.or.ddit.realtime.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompMessageController {
	@MessageMapping("/mine/noti")
	@SendToUser
	public String repeater(@Payload String payload) {
		System.err.println("=========================>"+payload);
		return payload;
	}

	
	/**
	 *  클라이언트는 /user/queue/echo 을 구독하고, 메시지는 /app/echo 으로 발행함.
	 *  서버는 해당 메시지를 수신하여, 나의 /user/queue/echo destination 으로 전송함.
	 * @param message
	 * @param sender
	 * @return
	 */
	@MessageMapping("{roomId}/echo")
	@SendToUser // /queue/echo destination 이 생략됨.
	// @SendTo // /topic/echo 구독자 전부에게 전송
	public String messageEcho(@DestinationVariable String roomId, @Payload String message) {
		log.info("{}에서 수신한 메시지  : {}", roomId, message);
		return String.format("서버로부터 나에게만 반송된 에코 메시지 [%s]", message);
	}
	
	/**
	 *  클라이언트는 /topic/mockChat/chatting 을 구독하고, 메시지는 /app/mockChat/chatting 으로 발행함.
	 *  서브는 해당 메시지를 수신하여, /topic/mockChat/chatting 구독자들에게 전송함.
	 * @param message
	 * @param sender
	 * @return
	 */
	@MessageMapping("/mockChat/{roomId}/chatting")
	@SendTo 
	public Map<String, Object> messageBroadcast(@DestinationVariable String roomId, @Payload String message, Authentication authentication) {
		log.info("{}에서 {} 로부터 수신한 메시지  : {}", roomId, authentication.getName(), message);
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("message", String.format("서버로부터 전처리된 후 모두에게 전송된 채팅 메시지 [%s]", message));
		messageMap.put("sender", authentication.getName());
		return messageMap;
	}
	
	
	/**
	 *  클라이언트는 /app/mockChat/entering 을 구독함.
	 *  서버는 /app/mockChat/entering 에 대한 구독 메시지를 수신하고, 메시지 브로커를 거치지않은 다이렉트 메시지를 직접 구독자에게 전송한다.
	 * @param accessor
	 * @return
	 */
	@SubscribeMapping("/mockChat/{roomId}/entering")
	public Map<String, Object> subscribeMessage(@DestinationVariable String roomId, SimpMessageHeaderAccessor accessor) {
		log.info("{} 에서 message header : {}, user : {}", roomId, accessor.getMessageHeaders(), accessor.getUser());
		Map<String, Object> directMessage = new HashMap<>();
		directMessage.put("greeting", String.format("%s 님 어솹셈.", accessor.getUser().getName()));
		directMessage.put("newId", "서버에서 새로 만든 구독 아이디");
		return directMessage;
	}
}
