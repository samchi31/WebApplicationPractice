package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/getServerTime")
public class GetServerTimeControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청의 조건 : 헤더(Accept)와 파라미터(locale)
		String accept = req.getHeader("Accept");
		String localeParam = req.getParameter("locale");
		
		// 파라미터 locale에 따라, 로케일 객체 변경
		Locale clientLocale = req.getLocale();
		if(localeParam!=null && !localeParam.isEmpty()) {
			clientLocale = Locale.forLanguageTag(localeParam);
		}
		
		// 2
		Date now = new Date();
		String nowStr = String.format(clientLocale, "now : %tc", now);
		
		// 3
		req.setAttribute("now", nowStr);
		req.setAttribute("message",nowStr);
		resp.setHeader("Refresh","1");		// 동작안됨 비동기요청이니까
		
		// 4 헤더 accept에 따라, viewName 변경
		String viewName = null;
		if(accept.contains("json")){
			viewName = "/jsonView.do";
		} else if (accept.toLowerCase().contains("plain")) {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, accept + " mime은 생성할 수 없음");
		}
		
		// 5
		if(viewName == null && !resp.isCommitted()) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "뷰네임은 널일 수 없음");
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}
