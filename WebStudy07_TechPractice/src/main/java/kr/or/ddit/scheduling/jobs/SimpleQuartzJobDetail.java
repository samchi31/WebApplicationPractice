package kr.or.ddit.scheduling.jobs;

import lombok.extern.slf4j.Slf4j;

/**
 * https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/htmlsingle/#scheduling-quartz
 *
 */
@Slf4j
public class SimpleQuartzJobDetail{
	
	private int number;
	
	public void printNumberUseQuartz() {
		log.info("----------7초 간격으로  quartz 기반으로 출력하는 숫자 : {}, 쓰레드명 : {}", ++number, Thread.currentThread().getName());
	}
}
