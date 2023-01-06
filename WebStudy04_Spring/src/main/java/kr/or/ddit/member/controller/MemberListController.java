package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class MemberListController {
	
	MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute SearchVO simpleCondition
		, HttpServletRequest req
	) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		
//		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
		
//		SearchVO simpleCondition = new SearchVO(searchType, searchWord);
		
//		int currentPage = 1;
//		if(StringUtils.isNumeric(pageParam)) {	// int 로 parse 가능한지
//			currentPage = Integer.parseInt(pageParam);
//		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(4,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		List<MemberVO> list = service.retrieveMemberList(pagingVO);
		req.setAttribute("pagingVO", pagingVO);
		log.info("paging data : {}", pagingVO);
		
		return "member/memberList";
	}
}
