package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestMappingInfo {
	private RequestMappingCondition requestMappingCondition;	// 어떤 요청이라는 정보
	private Object commandHandler;								// controller annotation을 가진 객체
	private Method handlerMethod;								// 
	
	public RequestMappingInfo(RequestMappingCondition requestMappingCondition, Object commandHandler,
			Method handlerMethod) {
		super();
		this.requestMappingCondition = requestMappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
}
