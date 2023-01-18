package kr.or.ddit.ui;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.ddit.vo.PagingVO;

public class PaginationTag extends TagSupport{
	private String type;
	public void setType(String type) {
		this.type = type;
	}
	
	private PagingVO<?> pagingVO;
	public void setPagingVO(PagingVO<?> pagingVO) {
		this.pagingVO = pagingVO;
	}
	
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		ServletContext servletContext = pageContext.getServletContext();
		// service와 dao에서 request 필요할 때 사용
		WebApplicationContext springContainer = RequestContextUtils.findWebApplicationContext(request, servletContext);
		
		PaginationManager manager = springContainer.getBean(PaginationManager.class);
		
		PaginationRenderer renderer = manager.renderType(type);
		
		JspWriter out = pageContext.getOut();
		try {
			out.println(renderer.renderPagination(pagingVO));
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}
		
	}
}
