package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.vo.CalculateVO;

@WebServlet("/04/calculate")
public class CalculateServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName = "/WEB-INF/views/03/calculateForm.jsp";		
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CalculateVO calculateVO = null;
		try(
			ServletInputStream is = req.getInputStream();
		){
			calculateVO = new ObjectMapper().readValue(is, CalculateVO.class);
		}
		
		// 아래는 다른 서블릿 코드에서 자주 중복 된다... -> 스프링 프레임워크 사용 시 간단해짐
		req.setAttribute("expression", calculateVO.getExpression());
		req.setAttribute("message", calculateVO.getExpression());
//		System.out.println(calculateVO.getExpression());
		String accept = req.getHeader("Accept");
		String viewName = null;
		if (accept.toLowerCase().contains("json")) {
			viewName = "/jsonView.do";
		} else if (accept.toLowerCase().contains("xml")) {
			viewName = "/xmlView.do";
		} else {	
			viewName = "/WEB-INF/views/04/plainView.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
