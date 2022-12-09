package kr.or.ddit.props;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

import kr.or.ddit.servlet01.DescriptionServlet;

public class PropertiesTest {
	/*
	 * class load 할 때
	 * 상수 메모리 (가비지컬렉터 대상 아님)에 해당 class를 올림
	 * 그 다음 heap 메모리(가비지컬렉터 대상)에 변수 올림 
	 */
//	@Test
//	public void classLoaderTest() throws ClassNotFoundException, IOException {
//		ClassLoader loader = ClassLoader.getSystemClassLoader();
//		Class<?> clz = loader.loadClass("kr.or.ddit.servlet01.DescriptionServlet");
//		System.out.println(DescriptionServlet.class);
//		System.out.println(DescriptionServlet.class.equals(clz));
////		URL resourceURL = loader.getResource("kr/or/ddit/props/DataStore.properties");	// 상대경로
////		URL resourceURL = DescriptionServlet.class.getResource("/kr/or/ddit/props/DataStore.properties");	// 절대경로
//		URL resourceURL = DescriptionServlet.class.getResource("../props/DataStore.properties");	// 상대경로
//		File file = new File(resourceURL.getFile());
//		System.out.println(file.getCanonicalPath());
//		
////		FileInputStream fis = new FileInputStream(file);	// byte
//		try(
//			FileReader fr = new FileReader(file);				// char
//		){
//			int tmp = -1;
//			while((tmp=fr.read()) != -1) {
//				System.out.write(tmp);
//			}
//		}
//	}
	
	/*
	 * 데이터 입력스트림으로 읽는 절차
	 * 1. 매체(미디어)를 객체화 			ex) File, Socket, DataSource
	 * 2. 입력 스트림 개방, 매체에 연결		ex) new FileInputStream(file)
	 * 3. 데이터의 마지막(EOF, EOS, -1, null)까지 반복적인 읽기 작업
	 * 4. 개방된 스트림 종료(매체를 해제시켜 다른 스레드가 사용할 수 있도록 함)
	 */

//	Properties 파일 읽는 방법
//	1. Properties
	@Test
	public void test() throws IOException {
		System.out.println("TEST CASE");
		
		Properties properties = new Properties();
		try(
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");
		){
			properties.load(is);
			System.out.println(properties.getProperty("prop1"));
			Enumeration<Object> en = properties.keys();
			while(en.hasMoreElements()) {
				Object key = (Object) en.nextElement();
				Object value = properties.get(key);
				System.out.printf("%s : %s", key, value);
			}
		}
	}
	
//	2. ResourceBundle
//	@Test
	public void testResourceBundle() {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
		System.out.println(bundle.getString("prop1"));

	}

}
