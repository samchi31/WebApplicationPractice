package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpSession} 타입의 핸들러 메소드 인자 해결
 *
 */
public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

	// 객체의 타입을 결정
	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		boolean support = HttpServletRequest.class.equals(parameterType)
							||	HttpSession.class.equals(parameterType);
		
		return support;
	}

	// 결정된 타입의 객체를 반환
	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object argumentObject = null;
		
		if(HttpServletRequest.class.equals(parameterType)) {
			argumentObject = req;
		} else {
			argumentObject = req.getSession();
		}
		
		return argumentObject;
	}

}
