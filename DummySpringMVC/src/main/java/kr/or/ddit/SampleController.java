package kr.or.ddit;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
	@RequestMapping(value="/sample", method=RequestMethod.GET)
	public String sample(HttpServletRequest req) {
		req.setAttribute("now", new Date());
		return "sample/view";
	}
}
