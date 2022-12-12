package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/04/calculate")
public class CalculateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/03/calculateForm.jsp";		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		
		req.setAttribute("leftOp",req.getParameter("leftOp"));
		req.setAttribute("rightOp",req.getParameter("rightOp"));
		req.setAttribute("operator",req.getParameter("operator"));
				
		// 뷰 선택
		String path = null;
		if(accept.startsWith("*/*") || accept.toLowerCase().contains("html")) {
			path = "/WEB-INF/views/03/calculateForm.jsp";
		} else if (accept.toLowerCase().contains("json")) {
			path = "/jsonView.do";
		} else if (accept.toLowerCase().contains("xml")) {
			path = "/xmlView.do";
		}		
		// 뷰 이동
		req.getRequestDispatcher(path).forward(req, resp);
	
	}
}
