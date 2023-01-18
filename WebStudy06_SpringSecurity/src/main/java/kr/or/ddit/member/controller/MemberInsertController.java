package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Backend controller(command handler) --> POJO(Plain Old Java Object)
 *
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {

	@Inject
	private MemberService service;
	
	@ModelAttribute("member")	// handler method를 호출하기 전에 이걸 먼저 호출
	public MemberVO member() {
		log.info("@ModelAttribute 메소드 실행 -> member 속성  생성");
		return new MemberVO();
	}

//	@RequestMapping("/member/memberInsert.do")	// 모든 메서드를 전부 받는다
	@GetMapping		// get 메서드만 받는다
	public String memberForm(/*@ModelAttribute("member") MemberVO member*/) {	// req.setAttribute와 같다, 위에 따로 생성가능
		return "member/memberForm";
	}

//	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	@PostMapping	// 주소가 중복됨 -> RequestMapping으로 빼둠
	public String memberInsert(
			HttpServletRequest req
			, @Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
//			, @RequestPart(value="memImage", required=false) MultipartFile memImage
//			, HttpSession session
	) throws ServletException, IOException{		

//		member.setMemImage(memImage);
		
		String viewName = null;
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);		
//		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);	// annotation이 처리
		boolean valid = !errors.hasErrors();
		
		if(!valid) {
			viewName="member/memberForm";
		} else {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName="member/memberForm";
				break;
				
			case FAIL:
				req.setAttribute("message", "서버에 문제 있음 쫌따 다시 하셈");
				viewName="member/memberForm";
				break;

			default:	// OK
//				MemberVO modifiedMember = service.retrieveMember(member.getMemId());
//				session.setAttribute("authMember", modifiedMember);
				viewName="redirect:/mypage.do";
				break;
			}
		}
		
		return viewName;
	}
}
