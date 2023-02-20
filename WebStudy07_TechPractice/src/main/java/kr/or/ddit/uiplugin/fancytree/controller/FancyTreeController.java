package kr.or.ddit.uiplugin.fancytree.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.uiplugin.dao.BuyerDAO;
import kr.or.ddit.uiplugin.fancytree.BuyerFancyTreeNode;
import kr.or.ddit.uiplugin.fancytree.FancyTreeNode;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/uiplugin/fancyTree")
public class FancyTreeController {
	
	@Inject
	private BuyerDAO buyerDAO;

	@RequestMapping
	public String view() {
		return "uiplugin/fancyTreeView";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FancyTreeNode<BuyerVO>> json() {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(null);
		return buyerList.stream()
						.map(BuyerFancyTreeNode::new)
						.collect(Collectors.toList());
	}
}
