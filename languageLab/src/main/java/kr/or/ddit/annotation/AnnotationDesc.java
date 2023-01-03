package kr.or.ddit.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import kr.or.ddit.ReflectionUtils;

/**
 * annotation ? 개발자와 시스템에게 일정 정보를 제공하는 주석의 한 형태
 * 
 * marker annotation @WebServlet
 * single value annotation	@WebServlet("/member/memberInsert.do")
 * multi value annotation @WebServlet(value="/member/memberInsert.do", loadOnStartup=1)
 * 
 * custom annotation
 * 	1. @interface 키워드로 선언된 클래스 정의
 *  2. RetentionPolicy 를 통해 어노테이션의 사용 범위 설정 ( 어노테이션의 생존 시점, 런타임까지인지, 컴파일까지인지)
 *  3. Target 으로 어노테이션 적용 대상 설정
 */
public class AnnotationDesc {
	// comment
//	@Override : 컴파일러에게 정보를 제공
//	@WebServlet	: 톰캣(서버)에게 정보를 제공
	public static void main(String[] args) {
		String basePackages = "kr.or.ddit";
		List<Class<?>> classList = ReflectionUtils.getAllClassesAtBasePackages(basePackages);
//		classList.stream().forEach(System.out::println);
		Map<Class<?>, Controller> classMap = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		classMap.forEach((handlerClass, anno) -> {
			try {
				Object handlerObject = handlerClass.newInstance();
				System.out.printf("=====> %s : %s\n", handlerClass.getName(), anno);
//				ReflectionUtils.getMethodsAtClass(clz, String.class).stream().forEach(System.out::println);
				Map<Method, RequestMapping> methodMap = ReflectionUtils.getMethodsWithAnnotationAtClass(handlerClass, RequestMapping.class, String.class);
				methodMap.forEach((handlerMethod, requestMapping) -> {
					String url = requestMapping.value();
					String method = requestMapping.method();
					try {
						String logicalViewName = (String) handlerMethod.invoke(handlerObject);
						System.out.printf("url : %s, method : %s 요청 매핑 핸들러 : %s\n", url, method, handlerMethod);
						System.out.printf("핸들러 메소드에서 결정된 논리적인 뷰네임 : %s\n", logicalViewName);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					}
				});
			} catch (Exception e) {
				
			}
		});
	}
}
