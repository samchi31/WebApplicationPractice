package kr.or.ddit.view;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewPreparerComponent
public class CommonViewPreparer implements ViewPreparer, BeanNameAware{
	@Override
	public void setBeanName(String name) {
		log.info("등록된  bean name : {}", name);
		
	}
	
	private Map<String, List<MenuVO>> menuResources;
	
	// 이걸 inmemory 방식으로 생성 및 관리하는 게 맞는건가?
	public CommonViewPreparer() {
		super();
		menuResources = new LinkedHashMap<>();
		menuResources.put(
			"/file/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("single/multiple download")
				.menuURL("/file")
				.build()
				, MenuVO.builder()
				.menuText("FTP download")
				.menuURL("/file/ftp")
				.build()
			)
		);
		menuResources.put(
			"/reporting/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("PDFBox")
				.menuURL("/reporting/pdfBox")
				.build()
				, MenuVO.builder()
				.menuText("iText")
				.menuURL("/reporting/iText")
				.build()
				, MenuVO.builder()
				.menuText("jXLS")
				.menuURL("/reporting/jxls")
				.build()
				, MenuVO.builder()
				.menuText("EL 기반 템플릿")
				.menuURL("/reporting/template")
				.build()
			)
		);
		menuResources.put(
			"/uiplugin/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("FancyTree")
				.menuURL("/uiplugin/fancyTree")
				.build()
				, MenuVO.builder()
				.menuText("FullCalendar")
				.menuURL("/uiplugin/fullCalendar")
				.build()
				, MenuVO.builder()
				.menuText("DataTable")
				.menuURL("/uiplugin/dataTable")
				.build()
				, MenuVO.builder()
				.menuText("Portlet(GridStack)")
				.menuURL("/uiplugin/gridStack")
				.build()
			)
		);
		menuResources.put(
			"/realtime/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("WebSocket")
				.menuURL("/realtime/websocket")
				.build()
				, MenuVO.builder()
				.menuText("STOMP")
				.menuURL("/realtime/stomp")
				.build()
			)
		);
	}

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = atts.getRequest();
		log.info("request uri : {}" , req.getRequestURI());
		
		Optional<String> keyOption = menuResources.keySet().stream()
										.filter(key->{
											AntPathRequestMatcher matcher = new AntPathRequestMatcher(key);
											return matcher.matches(req);
										}).findFirst();
		if(keyOption.isPresent()) {
			List<MenuVO> menuList = menuResources.get(keyOption.get());
			tilesContext.getContext(Request.REQUEST_SCOPE).put("menuList", menuList);
		}
				  
	}
	

}












