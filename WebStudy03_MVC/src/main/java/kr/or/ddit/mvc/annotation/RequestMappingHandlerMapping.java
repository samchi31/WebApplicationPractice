package kr.or.ddit.mvc.annotation;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping {

	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public RequestMappingHandlerMapping(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		scanBasePackage(basePackages);
	}
	
	private void scanBasePackage(String[] basePackages) {
		ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages)
			.forEach((handlerClass, controller) -> {
				try {
					Object commandHandler = handlerClass.newInstance();
					ReflectionUtils.getMethodsWithAnnotationAtClass(
						handlerClass, RequestMapping.class, String.class
						/*, HttpServletRequest.class, HttpServletResponse.class*/
					).forEach((handlerMethod, requestMapping) -> {
						RequestMappingCondition mappingCondition = new RequestMappingCondition(requestMapping.value(), requestMapping.method());
						RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
						handlerMap.put(mappingCondition, mappingInfo);
						log.info("수집된 핸들러 정보 : {}", mappingInfo);
					});
				} catch (Exception e) {
					log.error("핸들러 클래스 스캔 중 문제 발생", e);
				}
			});;
	}

	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest request) {
		
		String url = request.getServletPath();
		RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());
		RequestMappingCondition mappingCondition = new RequestMappingCondition(url, method);	
		
		// 없을 때 오류는?
		
		return handlerMap.get(mappingCondition);
	}

}
