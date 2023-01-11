package kr.or.ddit.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
//	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	@PostMapping("/login/logout.do")
	public String process(HttpSession session) {
//		HttpSession session = req.getSession();
//		session.removeAttribute("authMember");
		session.invalidate(); 	// 알아서 세션 속성 지움	
		
		//login과 같은 인증과 관련된 모든 요청은 post		
		
		return "redirect:/";		
	}
}
