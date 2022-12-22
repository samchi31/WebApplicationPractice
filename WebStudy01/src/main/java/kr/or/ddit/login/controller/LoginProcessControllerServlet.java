package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.wrapper.CookieHttpServletRequestWrapper;
import kr.or.ddit.vo.MemberVO;


/**
 * 1. 검증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동
 * 2. 인증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동
 * 		- 비밀번호 오류 상태를 가정하고, 메시지 전달 -> alert 함수로 메시지 출력
 * 		- 이전에 입력받은 아이디의 상태를 유지 
 * 3. 인증 완료 시 웰컴 페이지로 이동함
 */
@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	
	private Boolean authenticate(MemberVO member) {
		return member.getMemId().equals(member.getMemPass());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1
		HttpSession session = req.getSession();
		if(session.isNew()) {	// post로 새로운 세션? 세션 하이재킹
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"로그인 폼이 없는데 로그인이 어캐됨");
			return;
		}
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemPass(memPass);
		
		boolean valid = validate(member);
		String viewName = null;
		
		if(valid) {
			// 2
			// 4
			if(authenticate(member)) {
				setCookie(req, resp);	// 로그인 성공 시 쿠키 설정
				session.setAttribute("authMember", member);
				viewName = "redirect:/";
			} else {
				session.setAttribute("validId", memId);
				session.setAttribute("message", "비밀번호 오류");
				viewName = "redirect:/login/loginForm.jsp";
			}
		} else {
			session.setAttribute("message", "아이디나 비밀번호 누락");
			viewName = "redirect:/login/loginForm.jsp";
		}
		
		// 5
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			String location = req.getContextPath()+viewName;
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}

	private boolean validate(MemberVO member) {
		boolean valid = true;
		
		if(StringUtils.isBlank(member.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			valid = false;
		}
		
		return valid;
	}
	
	private void setCookie(HttpServletRequest req, HttpServletResponse resp) {
		// 아이디기억하기(5일) 체크박스 없이 하면 기존에 있던 쿠키도 삭제
		String memId = req.getParameter("memId");
		String saveId = req.getParameter("saveId");	// on , null
		if(saveId != null && saveId.equals("on")) {
			Cookie idCookie = new Cookie("memId", memId);
			idCookie.setMaxAge(60*60*24*5); //5일
			resp.addCookie(idCookie);
		} else {
			Cookie idCookie = new Cookie("memId", memId);
			idCookie.setMaxAge(0);
			resp.addCookie(idCookie);
		}
	}
}
