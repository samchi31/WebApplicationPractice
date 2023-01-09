package kr.or.ddit.di;

import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertiesContextTestView {
	public static void main(String[] args) {
//		System.getProperties().forEach((k,v) -> {
//			System.out.printf("%s : %s\n", k, v);
//		});
//		System.getenv().forEach((k, v) -> {
//			System.err.printf("%s : %s\n", k, v);
//		});
		
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/Properties-Context.xml");
		DBInfoVO dbInfoVO1 = context.getBean("dbInfo1",DBInfoVO.class);
		System.out.println(dbInfoVO1);
		DBInfoVO dbInfoVO2 = context.getBean("dbInfo2",DBInfoVO.class);
		System.out.println(dbInfoVO2);
		Properties dbInfo = context.getBean("dbInfo",Properties.class);
		System.out.println(dbInfo);
	}
}
