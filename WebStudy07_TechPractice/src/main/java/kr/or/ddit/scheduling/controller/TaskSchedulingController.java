package kr.or.ddit.scheduling.controller;

import java.util.Optional;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.ddit.scheduling.jobs.AsyncSimpleExecuteJob;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/scheduling")
public class TaskSchedulingController {
	@Resource(name="quartzScheduler")
	private Scheduler quartzScheduler;
	
	@Resource(name="scheduler")
	private ThreadPoolTaskScheduler taskScheduler;
	
	@Inject
	private AsyncSimpleExecuteJob asyncJob;
	
	@Inject
	private SimpMessagingTemplate messagingTemplate;
	
	@RequestMapping("async")
	public String view(Model model, Authentication authentication) throws SchedulerException {
		String user = Optional.ofNullable(authentication).map(Authentication::getName)
				.orElse("anonymous");
		ListenableFuture<?> future = asyncJob.execute(user);
		future.addCallback(result->{
			log.info("지연 뒤에 받아온 최종 데이터 : {}", result);
			messagingTemplate.convertAndSend("/topic/system/noti", result);
			if(authentication!=null) {
				messagingTemplate.convertAndSendToUser(user, "/queue/noti", result);
			}
		}, e->{
			log.error(e.getMessage(), e);
		});
		
		return "scheduling/asyncView";
	}
	
	@RequestMapping(value="pause", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String pauseSchedulingJobs() throws SchedulerException {
		taskScheduler.shutdown(); 
		quartzScheduler.pauseAll();
		return "스프링 스케쥴러를 셧다운하고, Quartz 스케쥴러를 일시 중지함";
	}
	
	@RequestMapping(value="resume", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String resumeSchedulingJobs() throws SchedulerException {
		quartzScheduler.resumeAll();
		return "Quartz 스케쥴러를 재구동함";
	}
}








