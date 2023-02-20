package kr.or.ddit.uiplugin.datatable.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.uiplugin.dao.OthersDAO;
import kr.or.ddit.uiplugin.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/uiplugin/dataTable")
public class DataTableController {
	@Inject
	private ProdDAO prodDAO;
	
	@RequestMapping
	public String view() {
		return "uiplugin/dataTableView";
	}
	
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("searchPanes")
	public Map<String, Map<String, Object>> lprodList(Model model) {
		Map<String, Object> options = new HashMap<>();
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList().stream()
												.map(lp->{
													Map<String, Object> map = new HashMap<>();
													map.put("label", lp.get("lprodNm"));
													map.put("value", lp.get("lprodGu"));
													return map;
												}).collect(Collectors.toList());
		List<Map<String, Object>> buyerList = othersDAO.selectBuyerList(null).stream()
													.map(by->{
														Map<String, Object> map = new HashMap<>();
														map.put("label", by.getBuyerName());
														map.put("value", by.getBuyerId());
														return map;
													}).collect(Collectors.toList());
		
		options.put("prodLgu", lprodList);
		options.put("prodBuyer", buyerList);
		
		return Collections.singletonMap("options", options);
	}
	
	@RequestMapping("prodSource")
	public String jsonData(Model model) {
		List<ProdVO> prodList = prodDAO.selectProdList(null);
		model.addAttribute("data", prodList);
		return "jsonView";
	}
}
