package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
@Controller
public class MypageControllerServlet{
	
	@Inject
	private MemberService service;
	
	@RequestMapping("/mypage.do")
	public String doGet(
			Model model
			, @AuthenticationPrincipal MemberVOWrapper user	//HMAR가 이해할수 있어야한다
			
			) throws ServletException, IOException {
//		MemberVOWrapper principal = (MemberVOWrapper)req.getUserPrincipal();
//		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		MemberVO authMember = user.getRealMember();
		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
//		req.setAttribute("member", member);		
		model.addAttribute("member", member);
		
		String viewName = "member/memberView";	// logical view name
		
		return viewName;
	}
}
