package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet {
	
	MemberService service = new MemberServiceImpl();
	private static final Logger log = LoggerFactory.getLogger(MemberDeleteControllerServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1 요청 분석 (검증 해줘야함)
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();

		MemberVO member = (MemberVO)session.getAttribute("authMember");
		member.setMemPass(req.getParameter("memPass"));
		req.setAttribute("member", member);
		
		String viewName = null;
		
		Map<String, List<String>> errors = new LinkedHashMap<String, List<String>>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(member, errors, DeleteGroup.class);
		
		if(valid) {
			ServiceResult result = service.removeMember(member);
			System.out.println(member);
			switch (result) {
			case INVALIDPASSWORD:
			case NOTEXIST:
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberView";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류 , 쫌따 다시");
				viewName = "member/memberView";
				break;

			default:
				session.removeAttribute("authMember");
				viewName = "redirect:/";
				break;
			}
		} else {
			viewName = "member/memberView";
		}
		
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
	}
}
