package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import kr.or.ddit.reflect.ReflectionTest;
import kr.or.ddit.vo.MemberVO;

/**
 * Reflection (java.reflect)
 * 	: 객체의 타입, 객체의 속성(상태, 행동) 정보들을 역으로 추적하는 작업
 * 
 *	javabean 규약으로 만들어진 객체라는 전제를 깔고 행한다
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		Object dataObj = ReflectionTest.getObject();
		System.out.println(dataObj);
		Class<?> objType = dataObj.getClass();
		System.out.println(objType.getName());		// Qualified Name
		
		Field[] fields = objType.getDeclaredFields();	// getFields() : public 필드만 들고옴 -> 하지만 필드는 대부분 private
//		Arrays.stream(fields).forEach(System.out::println);
		Method[] methods = objType.getDeclaredMethods();
//		Arrays.stream(methods).forEach(System.out::println);
	
		try {
			Object newObj = objType.newInstance();	// 기본 생성자가 있는지 없는지 모르지만 호출하여 인스턴스 생성 -> IllegalAccessException 
			Arrays.stream(fields).forEach(fld ->{
//				fld.setAccessible(true);	// private -> public으로 풀어버림 => 좋은 방법이 아니다
				String fldName = fld.getName(); 	// mem_id 필드명 가져오기
				try {
					PropertyDescriptor pd = new PropertyDescriptor(fldName, objType);	// javaBean 객체를 대상으로 사용 가능한
					Method getter = pd.getReadMethod(); 
					Method setter = pd.getWriteMethod();
					// getter
//					Object fldValue = fld.get(dataObj);
					Object fldValue = getter.invoke(dataObj);
					// setter
//					fld.set(newObj, fldValue);
					setter.invoke(newObj, fldValue);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			System.out.println(newObj);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
