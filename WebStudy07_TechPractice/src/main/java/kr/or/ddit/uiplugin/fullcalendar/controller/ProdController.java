package kr.or.ddit.uiplugin.fullcalendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/prod/{prodId}")
public class ProdController {
	@GetMapping(produces="text/html;charset=UTF-8")
	@ResponseBody
	public String prodView(@PathVariable String prodId) {
		return String.format("<html><body><h1>상품(%s)상세페이지</h1></body></html>", prodId);
	}
}
