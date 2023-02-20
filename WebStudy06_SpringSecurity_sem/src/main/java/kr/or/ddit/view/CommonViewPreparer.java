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
			"/board/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("게시글 작성")
				.menuURL("/board/boardInsert.do")
				.build()
				, MenuVO.builder()
				.menuText("게시글목록")
				.menuURL("/board/boardList.do")
				.build()
			)
		);
		menuResources.put(
			"/member/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("회원목록")
				.menuURL("/member/memberList.do")
				.build()
			)
		);
		menuResources.put(
			"/prod/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("상품목록")
				.menuURL("/prod")
				.build()
				, MenuVO.builder()
				 .menuText("상품등록")
				 .menuURL("/prod/prodInsert.do")
				.build()
			)
		);
		menuResources.put(
			"/buyer/**"
			, Arrays.asList(
				MenuVO.builder()
				.menuText("거래처목록")
				.menuURL("/buyer/buyerList.do")
				.build()
				, MenuVO.builder()
				.menuText("거래처등록")
				.menuURL("/buyer/buyerInsert.do")
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












