package kr.or.ddit.prod.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProdInsertController {

	private ProdService service = new ProdServiceImpl();
	
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodForm(HttpServletRequest req) {	
		addAttribute(req);
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String insertProcess(
		@ModelAttribute("prod") ProdVO prod
		, HttpServletRequest req
		, @RequestPart("prodImage") MultipartFile prodImage
	) throws IOException {
		addAttribute(req);
		
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartHttpServletRequest wrapperReq = (MultipartHttpServletRequest)req;
//			// prodImage -> prodImg
//			// 저장 , metadata 추출, db 저장 (prodImg)
//			
//			MultipartFile prodImage = wrapperReq.getFile("prodImage");	
		
//			if(prodImage != null && !prodImage.isEmpty()) {
//				// 1. 저장
//				String saveFolderURL = "/resources/prodImages";
//				ServletContext application = req.getServletContext();
//				String saveFolderPath = application.getRealPath(saveFolderURL);
//				File saveFolder = new File(saveFolderPath);
//				if(!saveFolder.exists()) {
//					saveFolder.mkdirs();
//				}
//				
//				// 2. metadata 추출
//				String saveFilename = UUID.randomUUID().toString();
//				prodImage.transferTo(new File(saveFolder, saveFilename));
//				
//				// 3. db 저장
//				prod.setProdImg(saveFilename);
//				
//			}
//		}
//		
		prod.setProdImage(prodImage); // null, blank, mimetype 체크
		// 1. 저장
		String saveFolderURL = "/resources/prodImages";
		ServletContext application = req.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}		
		prod.saveTo(saveFolder);
				
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
				case FAIL:			
					req.setAttribute("message", "서버 오류. 이따가 다시");
					viewName = "prod/prodForm";
					break;				
				default:
					viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
					break;
			}			
		} else {
			viewName = "prod/prodForm";
		}
		
		return viewName;
	}
}
