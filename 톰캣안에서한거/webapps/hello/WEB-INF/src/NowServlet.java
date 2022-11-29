package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.time.*;
import java.util.*;

// localhost/hello/now => current server time: HH:MM:SS

public class NowServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException
	{
		// MIME text
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//LocalTime now = LocalTime.now();
		Date now = new Date();
		String msg = String.format("current server time : %tc", now);
		out.println(msg);
		out.close();
	}
}