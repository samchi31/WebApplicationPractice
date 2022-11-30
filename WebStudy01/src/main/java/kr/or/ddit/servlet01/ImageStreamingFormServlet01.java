package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/01/imageForm.do"/* , initParams= {@WebInitParam(name="imageFolder",value="d:/contents/images")} */)
public class ImageStreamingFormServlet01 extends HttpServlet {

	private ServletContext application;
	private String imageFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");	// mime text 설정 시 출력 스트림을 열기 전에 먼저 설정해야한다
		
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles( (dir,name)->{
			String mime = application.getMimeType(name);
			return mime !=null && mime.startsWith("image/");
		});
		StringBuffer content = new StringBuffer();
		
		content.append("<html>\n");
		content.append("<body>\n");
		content.append(String.format("<form action='%s/imageStreaming.do'>\n",req.getContextPath()));
		content.append("<select name='image'>\n");
		String pattern = "<option>%s</option>\n";
		for (File tmp : imageFiles) {
			content.append(String.format(pattern, tmp.getName()));
		}
		content.append("</select>\n");
		content.append("<input type='submit' value='전송'/>\n");
		content.append("</form>\n");
		content.append("</body>\n");
		content.append("</html>");
		
		//try with resource => 알아서 close 해줌 finally 필요가 없다
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		}
	}
}
