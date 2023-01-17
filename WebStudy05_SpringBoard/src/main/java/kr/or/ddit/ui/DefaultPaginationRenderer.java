package kr.or.ddit.ui;

import kr.or.ddit.vo.PagingVO;

public class DefaultPaginationRenderer implements PaginationRenderer {

	private final String APTTERN = "<a class='paging' href='#' data-page='%d'>%s</a>";
	
	@Override
	public String renderPagination(PagingVO<?> pagingVO) {
		StringBuffer html = new StringBuffer();
		
		int startPage = pagingVO.getStartPage();
		int blockSize = pagingVO.getBlockSize();
		int endPage = pagingVO.getEndPage();
		int currentPage = pagingVO.getCurrentPage();
		int totalPage = pagingVO.getTotalPage();
		
		if(startPage > blockSize) {
			html.append(String.format(APTTERN, startPage - blockSize, "이전"));
		}
		
		endPage = endPage > totalPage ? totalPage : endPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page == currentPage) {
				html.append("<a href='#'>"+page+"</a>");
			} else {
				html.append(String.format(APTTERN, page, page+""));				
			}
		}
		
		if(endPage < totalPage) {
			html.append(String.format(APTTERN, endPage + 1, "다음"));
		}
		
		return html.toString();
	}
	
}
