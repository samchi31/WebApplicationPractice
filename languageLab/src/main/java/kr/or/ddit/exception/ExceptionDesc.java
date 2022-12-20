package kr.or.ddit.exception;

import java.io.IOException;
import java.sql.SQLException;

/**
 *  에러나 예외 (Throwable) : 예상하지 못했던 비정상적인 처리 상황
 * 		- Error : 개발자가 직접 처리하지 않는 에러 계열
 * 		- Exception : 개발자가 직접 처리할 수 있는 예외 계열
 * 			- checked exception (Exception) : 반드시 처리해야만 하는 예외(컴파일에러발생)
 * 				ex) IOException, SQLException
 * 			- unchecked exception (RuntimeException) : 처리하지 않더라도 메소드 호출구조를 통해 JVM 에게 제어권이 전달되는 예외
 * 				ex) NullPointerException, IndexOutOfBoundException
 * 
 *  ** 예외 처리 방법
 *  직접처리(능동처리) : try(closable object)~catch~finally
 *  위임처리(수동처리) : 메소드의 호출자에게 throws 로 예외 제어권 위임
 * 
 *  ** 커스텀 예외 사용 방법
 *  1. Exception 이나 RuntimeException 의 자식 클래스 정의(예외 클래스)
 *  2. throw 예외 인스턴스 생성
 */
public class ExceptionDesc {
	public static void main(String[] args) {
//		try {
//			String data = getSampleData();
//			System.out.println(data);
//		} catch (IOException e) {
////			System.err.println(e.getMessage());
//			e.printStackTrace();
//		}
		
		try {
			System.out.println(getSampleDataWithRE());
		} catch (NullPointerException | UserNotFoundException e) {
			System.err.println(e.getMessage());
			System.out.println("정상처리 위장 가능");
		}
		
//		System.out.println(getSampleChangeException());
	}
	private static String getSampleData() throws IOException {
		String data = "SAMPLE";
		if(1==1) {
			throw new IOException("강제발생 예외");
		}
		return data;
	}
	
	private static String getSampleDataWithRE() {//throws가 없어도 있는것처럼 동작
		String data = "SAMPLERE";
		if(1==1) {
			throw new UserNotFoundException("강제발생 예외");
		}
		return data;
	}
	
	private static String getSampleChangeException() {
		String data = "SAMPLEChangeException";
		try {
			if(1==1) {
				throw new SQLException("강제발생 예외");
			}
			return data;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
