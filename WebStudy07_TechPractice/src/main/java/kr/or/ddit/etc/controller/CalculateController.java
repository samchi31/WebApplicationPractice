package kr.or.ddit.etc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.CalculateVO;

@RestController
@RequestMapping(value="/etc/calculate", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CalculateController{
	
	@PostMapping("operate")
	public CalculateVO calculate(@RequestBody CalculateVO calculateVO){
		return calculateVO;
	}
}

















