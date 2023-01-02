package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.AbstractController;

//@WebServlet("/login/logout.do")
public class LogoutControllerServlet implements AbstractController{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		session.removeAttribute("authMember");
		session.invalidate(); 	// 알아서 세션 속성 지움	
		
		//login과 같은 인증과 관련된 모든 요청은 post
		
		
		return "redirect:/";
//		String viewName = "redirect:/";
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			String location = req.getContextPath()+viewName;
//			resp.sendRedirect(location);
//		} else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}
		
	}
}
