package kr.or.ddit.servlet01;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ImageStreamingServlet extends HttpServlet{
	
	private String imageFolder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		imageFolder = config.getInitParameter("imageFolder");
		System.out.printf("받은 파라미터 : %s\n", imageFolder);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException
	{				
		ServletContext application = getServletContext();	// 톰캣과 communication 하기 위한 객체 , 가장먼저 생성되고 가장 오래 살아있는 객체, 가장 범위가 넓은 저장소		
		
		// MIME text 한글을 포함하기 위해서 -encoding utf-8
		String imageName = req.getParameter("image");
		if(imageName==null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String mimeType = application.getMimeType(imageName);
		resp.setContentType(mimeType);
		
		File imageFile = new File(imageFolder,imageName);
		if(!imageFile.exists()) {
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
}