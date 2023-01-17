package kr.or.ddit.sample.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
//	@Autowired
	private SampleService service;
	
	@Inject
	public void setService(SampleService service) {
		this.service = service;
		log.info("주입된 service : {}", service);
	}
}
