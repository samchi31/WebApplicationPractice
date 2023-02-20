package kr.or.ddit.uiplugin.gridstack;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ui.BootstrapPaginationRender;
import kr.or.ddit.uiplugin.dao.BuyerDAO;
import kr.or.ddit.uiplugin.dao.ProdDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/uiplugin/gridStack")
public class GridStackController {
	
	@Inject
	private ProdDAO prodDAO;
	
	@Inject
	private BuyerDAO buyerDAO;
	
	@RequestMapping
	public String view() {
		return "uiplugin/gridStackView";
	}
	
	@RequestMapping("prodList")
	public String jsonData(@RequestParam(name="page", required=false, defaultValue="1") int currentPage, Model model) {
		// 시간 관계상 로직 레이어를 생략했음.
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setCurrentPage(currentPage);
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		model.addAttribute("pagingVO", pagingVO);
		if(!pagingVO.getDataList().isEmpty())
			model.addAttribute("pagingHTML", new BootstrapPaginationRender().renderPagination(pagingVO));
		return "jsonView";
	}

	@RequestMapping(value="buyerList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String json(@RequestParam(name="page", required=false, defaultValue="1") int currentPage, Model model) {
		// 시간 관계상 로직 레이어를 생략했음.
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		int totalRecord = buyerDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		model.addAttribute("pagingVO", pagingVO);
		if(!pagingVO.getDataList().isEmpty())
			model.addAttribute("pagingHTML", new BootstrapPaginationRender().renderPagination(pagingVO));
		return "jsonView";
	}
}
