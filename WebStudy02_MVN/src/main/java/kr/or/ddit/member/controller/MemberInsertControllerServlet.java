package kr.or.ddit.member.controller;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {

	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/member/memberForm.jsp";
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			String location = req.getContextPath()+viewName;
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1
		req.setCharacterEncoding("UTF-8");

		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
//		member.setMemId(req.getParameter("memId"));
		Map<String, String[]> parameterMap = req.getParameterMap();
//		parameterMap.entrySet().stream().forEach((entry)->{
//			String paramName = entry.getKey();
////			MemberVO.class.getDeclaredField(paramName);
//			PropertyDescriptor pd = new PropertyDescriptor(paramName, MemberVO.class);
//			Method setter = pd.getWriteMethod();
//		});
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

		ServiceResult result = service.createMember(member);

		String viewName = null;
		switch (result) {
		case PKDUPLICATED:
			req.setAttribute("message", "아이디 중복");
			viewName="/WEB-INF/views/member/memberForm.jsp";
			break;
			
		case FAIL:
			req.setAttribute("message", "서버에 문제 있음 쫌따 다시 하셈");
			viewName="/WEB-INF/views/member/memberForm.jsp";
			break;

		default:	// OK
			viewName="redirect:/";
			break;
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
}
