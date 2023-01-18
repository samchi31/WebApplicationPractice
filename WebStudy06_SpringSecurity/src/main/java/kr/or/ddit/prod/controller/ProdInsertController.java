package kr.or.ddit.prod.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {

	private final ProdService service;	
//	private final OthersDAO othersDAO;
	
//	@Inject
//	private WebApplicationContext context;	// container
//	private File saveFolder;
//	@PostConstruct
//	public void init() throws IOException {
//		String saveFolderURL = "/resources/prodImages";
////		ServletContext application = req.getServletContext();
//		
//		// spring container는 그 자체로 resourceloader가 된다
//		Resource saveFolderRes = context.getResource(saveFolderURL);
//		
////		File saveFolder = new File(saveFolderPath);
//		saveFolder = saveFolderRes.getFile();
//		if(!saveFolder.exists()) {
//			saveFolder.mkdirs();
//		}
//	}
	
//	@ModelAttribute("lprodList")
//	public List<Map<String, Object>> lprodList() {
//		return othersDAO.selectLprodList();
//	}
//	
//	@ModelAttribute("buyerList")
//	public List<BuyerVO> buyerList() {
//		return othersDAO.selectBuyerList(null);
//	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@GetMapping
	public String prodForm() {	
		return "prod/prodForm";
	}
	
	@PostMapping
	public String insertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod	
		, Errors errors	// 타겟 객체 다음에 선언해준다
		, Model model
	) throws IOException {
				
//		prod.saveTo(saveFolder);
						
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
				case FAIL:			
					model.addAttribute("message", "서버 오류. 이따가 다시");
					viewName = "prod/prodForm";
					break;				
				default:
					viewName = "redirect:/prod/" + prod.getProdId();
					break;
			}			
		} else {
			viewName = "prod/prodForm";
		}
		
		return viewName;
	}
}
