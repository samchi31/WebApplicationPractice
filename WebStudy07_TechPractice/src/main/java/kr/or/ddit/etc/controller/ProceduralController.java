package kr.or.ddit.etc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.etc.dao.ProceduralDAO;

@RestController
@RequestMapping(value="/etc/procedural", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProceduralController {
	
	@Inject
	private ProceduralDAO dao;
	
	@RequestMapping("callFunction")
	public Map<String, Object> callFunction() {
		Map<String, Object> callByReference = new HashMap<>();
		
		callByReference.put("pDate", new Date());
		
		dao.call_FN_CREATE_CARTNO(callByReference);
		
		return callByReference;
	}
	
	@RequestMapping("callProcedure")
	public Map<String, Object> callProcedure() {
		Map<String, Object> callByReference = new HashMap<>();
		
		callByReference.put("pPeriod", "202004");
		
		dao.call_PROC_CART01(callByReference);
		
		return callByReference;
	}
}
