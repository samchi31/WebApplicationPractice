package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.AbstractController;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberView.do")
public class MemberViewController implements AbstractController{
	
	MemberService service = new MemberServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("who");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		
		String viewName = "member/memberView";
		return viewName;
		// 5
//		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
	}
}
