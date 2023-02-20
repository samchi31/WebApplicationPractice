package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.security.AuthMember;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	@Inject
	private MemberService service;
	
	@Inject
	private CompositeLogoutHandler logoutHandler;
	
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(
		@RequestParam(value="memPass", required=true) String memPass
		, @AuthMember MemberVO authMember
		, RedirectAttributes redirectAttributes
		, HttpServletRequest req
		, HttpServletResponse resp
	) throws ServletException, IOException {
//		1.
		String memId = authMember.getMemId();
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		String viewName = null;
		
		ServiceResult result = service.removeMember(inputData);
		switch (result) {
		case INVALIDPASSWORD:
			redirectAttributes.addFlashAttribute("message", "비번 오류");
			viewName = "redirect:/mypage.do";
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			viewName = "redirect:/mypage.do";
			break;

		default:
//			session.invalidate();
			Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
			logoutHandler.logout(req, resp, currentAuthentication);
			viewName = "redirect:/";
			break;
		}
		
		return viewName;
	}
}

































