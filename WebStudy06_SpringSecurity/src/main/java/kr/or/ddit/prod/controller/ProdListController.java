package kr.or.ddit.prod.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.ui.PaginationRenderer;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/prod")
@Controller
public class ProdListController{
	@Resource(name="bootstrapPaginationRenderer")	// inject 받으려는 객체의 구현체가 여러개인 경우 @Inject는 위험
	private PaginationRenderer renderer;
	
	private final ProdService service;
//	private final OthersDAO othersDAO;
//	
////	private void addAttribute(Model model) {
////		model.addAttribute("lprodList", othersDAO.selectLprodList());
////		model.addAttribute("buyerList", othersDAO.selectBuyerList(null));
////	}
//	
//	@ModelAttribute("lprodList")
//	public List<Map<String, Object>> lprodList() {
//		return othersDAO.selectLprodList();
//	}
//	
//	@ModelAttribute("buyerList")
//	public List<BuyerVO> buyerList() {
//		return othersDAO.selectBuyerList(null);
//	}
	
	@GetMapping
	public String listUI(Model model) {
//		addAttribute(model);
		return "prod/prodList";	
	}
	
	/*
	headers 는 모호함 
	consumes : content-type (client 에서 json)
	produces : accept (client header도 json , 응답도 json)
	*/
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)		// accept 헤더 json일 때
	public String listData(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage	
		, @ModelAttribute("detailCondition") ProdVO detailCondition
		, Model model
	) throws ServletException {
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("detailCondition",detailCondition);
		model.addAttribute("pagingHTML", renderer.renderPagination(pagingVO));
		
		return "jsonView";	// servlet-context.xml에 등록한 id -> adapter에서 d.s으로 넘김 -> d.s 가 v.r로 넘김(suffix, prefix가 다른 v.r가 필요하다)
	}
	
//	@RequestMapping("/prod/prodList.do")
//	public String prodList(
//		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//		, @ModelAttribute("detailCondition") ProdVO detailCondition
//		, Model model
//		, @RequestHeader String accept
//	) throws ServletException {
//		
//		String viewName = null;
//		if(accept.contains("html")) {
//			viewName = listUI(model);
//		} else if(accept.contains("json")) {
//			viewName = listData(currentPage, detailCondition, model);
//		}		
//		
//		return viewName;
//	}
	
}
