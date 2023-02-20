package kr.or.ddit.scheduling.jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimplePrintingJob {
	private int number;
	
	@Scheduled(fixedRate=1000)
	public void printNumber() {
		log.info("================>매초 출력되는 숫자 : {}, 쓰레드명 : {}", ++number, Thread.currentThread().getName());
	}
	
	
	@Scheduled(cron="*/5 * * * * *")
	public void priningDate() {
		log.info("----------------> 5초 간격으로 출력되는 시간 : {}, 쓰레드명 : {}"
					, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
					, Thread.currentThread().getName());
	}
}
