package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeMethodProcessor;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.mvc.annotation.resolvers.ServletRequestMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletResponseMethodArgumentResolver;

@Slf4j
public class RequestMappingHandlerAdapter implements HandlerAdapter{
	
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletRequestMethodArgumentResolver()); 
		argumentResolvers.add(new ServletResponseMethodArgumentResolver());
		argumentResolvers.add(new RequestParamMethodArgumentResolver());
		argumentResolvers.add(new ModelAttributeMethodProcessor());
		argumentResolvers.add(new RequestPartMethodArgumentResolver());
	}
	
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter param) {
		HandlerMethodArgumentResolver finded = null;
		
		for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.supportsParameter(param)) {
				finded = resolver;
				break;
			}
		}
		
		return finded;
	}

	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object handlerObject = mappingInfo.getCommandHandler();		// controller 객체
		Method handlerMethod = mappingInfo.getHandlerMethod(); 		// 핸들러 메소드
		
		int parameterCount = handlerMethod.getParameterCount();
		try {
			String viewName = null;
			if(parameterCount > 0) {
				Parameter[] parameters = handlerMethod.getParameters();
				Object[] arguments = new Object[parameterCount];
				
				for( int i = 0; i < parameterCount; i++) {
					Parameter param = parameters[i];
					HandlerMethodArgumentResolver findedResolver = findArgumentResolver(param);
					if(findedResolver==null) {
						throw new RuntimeException(String.format("%s 타입의 메소드인자는 현재 처리 가능한 resolver가 없음", param.getType()));
					} else {
						arguments[i] = findedResolver.resolveArgument(param, req, resp);						
					}
				}
				
				viewName = (String) handlerMethod.invoke(handlerObject, arguments);
				
			} else {
				viewName = (String) handlerMethod.invoke(handlerObject);				
			}
			return viewName;
		} catch (BadRequestException e) {
			log.error("handler adapter 가 handler method argument resolver 사용중 문제 발생 ", e);
			resp.sendError(400, e.getMessage());
			return null;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	

}
