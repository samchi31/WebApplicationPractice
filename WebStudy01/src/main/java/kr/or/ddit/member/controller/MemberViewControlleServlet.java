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
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControlleServlet extends HttpServlet{
	
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("who");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		
		String viewName = "/WEB-INF/views/member/memberView.jsp";
		// 5
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			String location = req.getContextPath()+viewName;
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}
