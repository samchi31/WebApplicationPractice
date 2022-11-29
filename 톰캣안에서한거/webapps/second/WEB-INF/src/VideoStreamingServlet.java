package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.time.*;
import java.util.*;

public class VideoStreamingServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException
	{				
		// MIME text 한글을 포함하기 위해서 -encoding utf-8
		resp.setContentType("video/mp4");
		String videoFolder = "d:/contents/movies";
		String videoName = "The_Power_of_Teamwork.mp4";
		File videoFile = new File(videoFolder,videoName);
		FileInputStream fis = new FileInputStream(videoFile);
		OutputStream os = resp.getOutputStream();
		
		int tmp = -1;
		while((tmp = fis.read()) != -1){
			os.write(tmp);
		}
		fis.close();
		os.close();
	}
}