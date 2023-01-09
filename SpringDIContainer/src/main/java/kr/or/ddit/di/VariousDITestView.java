package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VariousDITestView {
	public static void main(String[] args) {
		/*컨테이너 객체생성
		필요없으면 자동 소멸
		등록된 bean들은 모두 라이프사이클 가짐 (log)
		등록한 vo 빈 메인메소드에서 주입받기, property 확인*/
		
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/VariousDI-Context.xml");
		context.registerShutdownHook();
		VariousDIVO vo1 = context.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo2 = context.getBean("vo2",VariousDIVO.class);
		log.info("주입된 객체 : {}", vo1);
		log.info("주입된 객체 : {}", vo2);
	}
}
