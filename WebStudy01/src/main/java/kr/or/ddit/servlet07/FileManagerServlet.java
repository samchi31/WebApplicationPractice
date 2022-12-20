package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import kr.or.ddit.enumpkg.FileOperatorType;

@WebServlet("/browsing/fileManager")
public class FileManagerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3개의 파라미터 확보 -> 파라미터 없는거 예외 처리		
		int sc = validate(req);
		
		Map<String , Object> modelMap = (Map<String , Object>)req.getAttribute("modelMap");
		if(sc==200) {
			boolean result = fileManage(modelMap);
			req.setAttribute("result", result);
			
			String viewName = "/jsonView.do";
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			resp.sendError(sc);
		}
	}

	private Boolean fileManage(Map<String , Object> modelMap) throws IOException {
		File sourceFile = (File)modelMap.get("sourceFile");
		
		File destinationFolder = (File)modelMap.get("destinationFolder");
//		File destFile = new File(destinationFolder, sourceFile.getName());
		Path destFilePath = Paths.get(destinationFolder.getCanonicalPath(), sourceFile.getName());
		
		String command = (String) modelMap.get("command");
			
//		if("COPY".equals(command)) {
//			// file 복사 
//			Files.copy(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
//		} else if ("MOVE".equals(command)) {
//			Files.move(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
//		}
		FileOperatorType.valueOf(command)
			.fileOperate(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
		return true;
	}
	
	private int validate(HttpServletRequest req) {
		int sc = 200;
		
		String srcFile = req.getParameter("srcFile");
		String destFolder = req.getParameter("destFolder");
		String command = req.getParameter("command");
		
		Map<String , Object> modelMap = new HashMap<String, Object>();
		req.setAttribute("modelMap", modelMap);
		
		if(srcFile == null || srcFile.isEmpty()
				|| destFolder == null || destFolder.isEmpty()
				|| command == null || command.isEmpty()) {
			sc = 400;
		} else {
			ServletContext application = req.getServletContext();
			String srcPath =application.getRealPath(srcFile);
			File sourceFile = new File(srcPath);
			if(!sourceFile.exists()) {
				sc = 404;
			} else if(sourceFile.isDirectory()) {
				sc = 400;
			} else {
				modelMap.put("sourceFile", sourceFile);
			}
			String destPath = application.getRealPath(destFolder);
			File destinationFolder = new File(destPath);
			if(!destinationFolder.exists()) {
				sc = 404;
			} else if(destinationFolder.isFile()) {
				sc = 400;
			} else {
				modelMap.put("destinationFolder",destinationFolder);
			}
			if(!"COPY".equals(command) && !"MOVE".equals(command)) {
				System.out.println(command);
				sc = 404;
			} else {
				modelMap.put("command",command);
			}
		}
		return sc;
	}
}
