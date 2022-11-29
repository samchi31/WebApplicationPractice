package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.time.*;
import java.util.*;

public class ImageStreamingServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException
	{				
		// MIME text 한글을 포함하기 위해서 -encoding utf-8
		resp.setContentType("image/jpeg");
		String imageFolder = "d:/contents/images";
		String imageName = "cute8.jpg";
		File imageFile = new File(imageFolder,imageName);
		FileInputStream fis = new FileInputStream(imageFile);
		OutputStream os = resp.getOutputStream();
		
		int tmp = -1;
		while((tmp = fis.read()) != -1){
			os.write(tmp);
		}
		fis.close();
		os.close();
	}
}