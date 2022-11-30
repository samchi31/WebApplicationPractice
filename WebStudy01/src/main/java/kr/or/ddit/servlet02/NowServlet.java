package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now.do")
public class NowServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date now = new Date();
		req.setAttribute("now", now);
		
		String viewPath = "/01/sample.tmpl";	// tmpl 서블릿으로 간다
		req.getRequestDispatcher(viewPath).forward(req,resp);
	}
}
