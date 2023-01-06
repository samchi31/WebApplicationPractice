package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
/**
 * 상품 상세 조회 시 해당 거래처의 모든 정보 함께 조회함
 * 상품 상세 조회 시 구매자 리스트 (구매자 id, 회원이름, 회원 전화번호, 이메일 ,마일리지) 함께 조회
 * 분류명도 함께 조회
 *
 */
@Controller
public class ProdViewController{
	
	private ProdService prodService = new ProdServiceImpl();
	
	@RequestMapping("/prod/prodView.do")
	public String doGet(HttpServletRequest req
			, @RequestParam("what") String prodId ) throws ServletException, IOException {
		
		ProdVO prod = prodService.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		String viewName = "prod/prodView";
		return viewName;
	}
}
