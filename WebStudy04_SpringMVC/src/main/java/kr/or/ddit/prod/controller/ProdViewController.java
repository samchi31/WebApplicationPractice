package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * 상품 상세 조회 시 해당 거래처의 모든 정보 함께 조회함
 * 상품 상세 조회 시 구매자 리스트 (구매자 id, 회원이름, 회원 전화번호, 이메일 ,마일리지) 함께 조회
 * 분류명도 함께 조회
 *
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class ProdViewController{
	
	private final ProdService prodService;
	
	@RequestMapping("/prod/{prodId}")
	public String doGet(
			Model model
			, @PathVariable String prodId // @RequestParam 처럼 value가 같으면 생략가능 
	) throws ServletException, IOException {
		
		ProdVO prod = prodService.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		String viewName = "prod/prodView";
		return viewName;
	}
	
	/*
	 * RESTful URI 구조(비동기 처리 활용)

	UR(자원)I(식별자) : 명사

	http method : 동사

	parameter -> path varialbe

	/prod (GET) 전체 상품 조회
	/prod (DELETE) 전체 상품 삭제
	/prod/P101000001 (DELETE) 상품 하나 삭제
	/prod/P101000001 (PUT) 상품 하나 수정
	
	/prod (POST) 신규 상품 등록
	/prod/form (GET) 상품 등록 폼
	 */
}
