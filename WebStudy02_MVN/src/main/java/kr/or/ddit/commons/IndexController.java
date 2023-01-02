package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.AbstractController;
//@WebServlet("/index.do")
public class IndexController implements AbstractController{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("contentPage", "/WEB-INF/views/index.jsp");
//		String viewName = "/WEB-INF/views/layout.jsp";
//		req.getRequestDispatcher(viewName).forward(req, resp);
		return "layout";
	}
}
