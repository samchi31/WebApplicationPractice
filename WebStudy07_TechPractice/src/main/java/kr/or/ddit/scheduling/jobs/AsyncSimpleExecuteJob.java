package kr.or.ddit.scheduling.jobs;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncSimpleExecuteJob {
	@Async
	public ListenableFuture<String> execute(String sessionId) {
		try {
			int second=1;
			for(; second<=10; second++) {
				Thread.sleep(1000);
				log.info("처리가 {}초 지연되고 있음.", second);
			}
			return AsyncResult.forValue(second+"초 뒤에 만들어진 결과-"+sessionId);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
