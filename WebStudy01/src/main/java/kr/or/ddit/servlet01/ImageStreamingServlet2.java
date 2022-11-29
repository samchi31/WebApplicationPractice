package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ImageStreamingServlet2 extends HttpServlet {

	private String imageFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		imageFolder = config.getInitParameter("imageFolder");
		System.out.printf("받은 파라미터 : %s\n", imageFolder);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		ServletContext application = getServletContext(); // 톰캣과 communication 하기 위한 객체 , 가장먼저 생성되고 가장 오래 살아있는 객체, 가장 범위가 넓은 저장소

		String imageName = req.getParameter("image");
		if(imageName == null || imageName.isEmpty()) {
			printList(req,resp);
			return;
		}
		String mimeType = application.getMimeType(imageName);
		resp.setContentType(mimeType);

		File imageFile = new File(imageFolder, imageName);
		if (!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		FileInputStream fis = null;
		OutputStream os = null;
		try {			
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			int tmp = -1;
			while((tmp = fis.read()) != -1){
				os.write(tmp);
			}
		} finally {
			if(fis!=null) fis.close();
			if(os!=null) os.close();
		}
		
	}

	public void printList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		File fileFolder = new File(imageFolder);
		if (!fileFolder.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		File[] imgFiles = fileFolder.listFiles();
		if (imgFiles.length <= 0) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		PrintWriter out = resp.getWriter();

		StringBuffer content = new StringBuffer();
		content.append("<html>\n");
		content.append("<body>\n");
		content.append("<table border='1'>\n");

		for (int i = 0; i < imgFiles.length; i++) {
			content.append("<tr>\n");
			content.append("<td>\n");
			content.append(String.format("<a href='http://localhost/WebStudy01/imageStreaming.do?image=%s'>%s</a>\n",
					imgFiles[i].getName(), imgFiles[i].getName()));
			content.append("</td>\n");
			content.append("</tr>\n");
		}

		content.append("</table>\n");
		content.append("</body>\n");
		content.append("</html>\n");

		resp.setContentType("text/html;charset=UTF-8");
		out.println(content);
		out.close();
	}
}