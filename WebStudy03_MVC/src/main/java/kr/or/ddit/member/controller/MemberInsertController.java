package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Backend controller(command handler) --> POJO(Plain Old Java Object)
 *
 */
@Slf4j
@Controller
public class MemberInsertController {

	private MemberService service = new MemberServiceImpl();
	private static final Logger log = LoggerFactory.getLogger(MemberInsertController.class);
	

	@RequestMapping("/member/memberInsert.do")
	public String memberForm() {
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String memberInsert(
			HttpServletRequest req
			, @ModelAttribute("member") MemberVO member
			, @RequestPart(value="memImage", required=false) MultipartFile memImage
			, HttpSession session
	) throws ServletException, IOException{
		
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartHttpServletRequest wrapperReq = (MultipartHttpServletRequest)req;
//			// memImage -> memImg			
//			MultipartFile memImage = wrapperReq.getFile("memImage");
			member.setMemImage(memImage);
//			if(memImage != null && !memImage.isEmpty()) {
//				member.setMemImg(memImage.getBytes());
//			}
//		}
		
		String viewName = null;
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		
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
				MemberVO modifiedMember = service.retrieveMember(member.getMemId());
				session.setAttribute("authMember", modifiedMember);
				viewName="redirect:/mypage.do";
				break;
			}
		}
		
		return viewName;
	}
}
