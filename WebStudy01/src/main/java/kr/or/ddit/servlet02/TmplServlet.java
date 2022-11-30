package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.tmpl")
public class TmplServlet extends HttpServlet {	//템플릿엔진
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application= getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		StringBuffer tmplSrc = readTemplate(req, resp);
		
		if(tmplSrc == null) {
			System.err.println("템플릿 소스를 읽을 수 없음");
		}
		
//		Map<String, Object> scope = new HashMap<>();
//		scope.put("now",new Date());
		
		String html = evaluateVariables(tmplSrc, req);
		
		PrintWriter out = resp.getWriter();
		out.println(html);
	}
	
	private String evaluateVariables(StringBuffer tmplSrc, /*Map<String, Object> scope*/HttpServletRequest req) {
		String evalPattern = "#([a-zA-Z0-9_]+)#";	// () : group 한개
		Pattern regex = Pattern.compile(evalPattern);
		Matcher matcher = regex.matcher(tmplSrc);
		
		StringBuffer finalHtml = new StringBuffer();
		
		while(matcher.find()) {
			String varName = matcher.group(1); // now 값 반환
//			Object value = scope.get(varName);
			Object value = req.getAttribute(varName);
			matcher.appendReplacement(finalHtml, Objects.toString(value, ""));	// value.toString() -> nullpointexception을 발생, Objects.toString() -> "null" 리턴
		}
		matcher.appendTail(finalHtml);
		return finalHtml.toString();
	}

	private StringBuffer readTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String tmplPath = req.getServletPath(); 
		String realPath = application.getRealPath(tmplPath);
		
		File tmplFile = new File(realPath);
		
		if(!tmplFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, tmplPath+"를 찾을수 없음");
			return null;
		}
		
		FileReader reader = new FileReader(tmplFile);
		BufferedReader br = new BufferedReader(reader);
		String tmp = null;
		StringBuffer tmplSrc = new StringBuffer();
		while((tmp = br.readLine()) != null) {
			tmplSrc.append(tmp+"\n");
		}
		
		return tmplSrc;
	}
}
