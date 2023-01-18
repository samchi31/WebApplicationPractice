package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateConroller {
	
	private final MemberService service;
	
	@GetMapping
	public String updateForm(
//			MemberVOWrapper principal
			@SessionAttribute("authMember") MemberVO authMember
			, Model model
	) throws ServletException, IOException {
		MemberVO member = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", member);
		return "member/memberForm";
	}
	
	@PostMapping
	public String updateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member	// 현재 공유된 객체가 있으면 가져오고 없으면 binding해서 가져옴
			, BindingResult errors
			, Model model
			, HttpSession session // session setAttribute 시에는 사용해야함
	) throws IOException {		
		
		String viewName = null;		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류 , 쫌따 다시");
				viewName = "member/memberForm";
				break;

			default:
				viewName = "redirect:/mypage.do";
				break;
			}
		} else {
			viewName = "member/memberForm";
		}
		return viewName;
	}
}
