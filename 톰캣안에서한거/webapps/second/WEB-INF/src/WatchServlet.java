package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.time.*;
import java.util.*;

public class WatchServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException
	{
		StringBuffer content = new StringBuffer();
		content.append("<html>\n");
		content.append("<body>\n");
		content.append(String.format("<h4>current context name : %s</h4>\n", req.getContextPath()));
		content.append(String.format("<h4>last recieved server time : %tc</h4>\n", new Date()));
		content.append("<h4 id='id_h4'></h4>\n");
		content.append("<script>\n");
		//content.append("function f_clock(){\n");
		//content.append("let today = new Date();\n");
		//content.append("document.querySelector('#id_h4').innerHTML = 'current client time : '+today;\n");
		//content.append("setTimeout(f_clock,1000);\n");
		//content.append("};\n");
		//content.append("f_clock();\n");
		content.append("setInterval(function(){\n");
		content.append("let today = new Date();\n");
		content.append("document.querySelector('#id_h4').innerHTML = 'current client time : '+today;\n");
		content.append("},1000);\n");
		content.append("</script>\n");
		content.append("</body>\n");
		content.append("</html>");
		
		// MIME text 한글을 포함하기 위해서 -encoding utf-8
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		////LocalTime now = LocalTime.now();
		//Date now = new Date(); 
		//out.println("<html>");
		//out.println("<body>");
		//out.println(String.format("<h4>current context name : %s</h4>", req.getContextPath()));
		//out.println(String.format("<h4>last recieved server time : %tc</h4>", now));
		//// out.println(String.format("<h4>current client time : %tc</h4>", now));
		//out.println("<h4 id='id_h4'></h4>");
		//out.println("<script>");
		//out.println("let today = new Date();");
		//out.println("document.querySelector('#id_h4').innerHTML = '<h4>current client time : '+today");
		//out.println("</script>");
		//out.println("</body>");
		//out.println("</html>");
		out.println(content.toString());
		out.close();
	}
}