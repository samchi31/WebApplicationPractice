package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet("/04/getGreetingMessage")
public class getMessageControllerServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청분석(line, header, body)
		// 2. 모델확보
		// 3. 모델공유(setAttribute)
		// 4. 뷰선택
		// 5. 뷰로 이동
		
		// 1
		String accept = req.getHeader("Accept");
		String locale = req.getParameter("locale");
		String first = req.getParameter("first");
		if(first!=null && first.equals("true")) {
			Enumeration<String> keys = (Enumeration<String>)getPropertiesKeys();
			Map<String, Object> target = new HashMap<String, Object>();
			int keyCnt=1;
			while (keys.hasMoreElements()) {
				String name = "key"+keyCnt;
				Object value = keys.nextElement();
				target.put(name,value);
				keyCnt++;
			}
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, target);
			}
			return;
		}
		
		Locale clientLocale = null;
		if(locale != null) {
			// ko, en : language tag, locale code
			clientLocale = Locale.forLanguageTag(locale);
		} else {
			clientLocale = req.getLocale();	// Accept-Language header로 결정됨
		}
		String name = req.getParameter("name");
		if(name==null||name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		// 2
		Object message = null;
		try {
			message = retrieveMessage(clientLocale, name);
		} catch (MissingResourceException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 3
		req.setAttribute("message", message);
		
		// 4
		String viewName = null;
		int statusCode = HttpServletResponse.SC_OK;
		if (accept.toLowerCase().contains("json")) {
			viewName = "/jsonView.do";			
		} else if (accept.toLowerCase().contains("xml")) {
			viewName = "/xmlView.do";			
		} else if (accept.toLowerCase().contains("plain")) {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		} else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		
		// 5
		if(statusCode == HttpServletResponse.SC_OK) {
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			resp.sendError(statusCode);
		}
	}
	
	private Object retrieveMessage(Locale locale, String name) {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		return bundle.getString(name);
	}
	
	private Object getPropertiesKeys() {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		return bundle.getKeys();
	}
}
