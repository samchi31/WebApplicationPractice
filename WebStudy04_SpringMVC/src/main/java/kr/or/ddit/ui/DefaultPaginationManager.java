package kr.or.ddit.ui;

import java.util.Map;

public class DefaultPaginationManager implements PaginationManager {

	private Map<String, PaginationRenderer> rendererMap;

	
	DefaultPaginationManager(Map<String, PaginationRenderer> rendererMap) {
		super();
		this.rendererMap = rendererMap;
	}


	@Override
	public PaginationRenderer renderType(String type) {
		PaginationRenderer renderer = rendererMap.get(type);
		if(renderer == null) {
			renderer = new DefaultPaginationRenderer();
		}
		return renderer;
	}

}
