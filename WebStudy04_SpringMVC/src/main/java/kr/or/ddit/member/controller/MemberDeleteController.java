package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController {
	
	@Inject
	MemberService service;
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(value="memPass", required=true) String memPass
			, @SessionAttribute(value="authMember", required=true) MemberVO authMember	// 꺼낼때만 사용가능
			, HttpSession session
			, RedirectAttributes redirectAttributes
	) throws ServletException, IOException {
		
		String memId = authMember.getMemId();
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		
		String viewName = null;
		
		ServiceResult result = service.removeMember(inputData);
		switch (result) {
		case INVALIDPASSWORD:
			redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
			viewName = "redirect:/mypage.do";
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "서버 오류 , 쫌따 다시");
			viewName = "redirect:/mypage.do";
			break;

		default:
			session.invalidate();
			viewName = "redirect:/";
			break;
		}
		
		return viewName;
	}
}
