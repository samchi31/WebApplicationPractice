package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String process(HttpSession session) {
//		HttpSession session = req.getSession();
//		session.removeAttribute("authMember");
		session.invalidate(); 	// 알아서 세션 속성 지움	
		
		//login과 같은 인증과 관련된 모든 요청은 post		
		
		return "redirect:/";		
	}
}
