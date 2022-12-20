package kr.or.ddit.memo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo/*")
public class MemoControllerServlet extends HttpServlet {
	
	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 요청분석
		String accept = req.getHeader("Accept");
		
		//2 모델확보
		List<MemoVO> memoList = dao.selectMemoList();		
		
		//3 모델공유
		req.setAttribute("target", memoList);
		
		//4 뷰 선택
		String path = null;
		if(accept.contains("json")) {
			path = "/jsonView.do";
		} else if(accept.contains("html")) {
			path = "/06/memoView.jsp";
		}
		
		//5 뷰 이동
		if(path == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post(비동기)-(SC_300)Redirect-Get : PRG pattern (accept 헤더는 유지된다)		
		
		MemoVO memo = getMemoFromRequest(req);
		int res = dao.insertMemo(memo);
		
		//redirect
		String path = req.getContextPath() +"/memo";
		resp.sendRedirect(path);
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) throws IOException {
		String contentType = req.getContentType();
		MemoVO memo = null;
		if(contentType.contains("json")) {
			try(
				BufferedReader br = req.getReader();	// body content read 용 입력 스트림	
		 	){
				memo = new ObjectMapper().readValue(br, MemoVO.class);	// 역직렬화 + unmarshalling
			} 
		} else if (contentType.contains("xml")) {
			try(
				BufferedReader br = req.getReader();	// body content read 용 입력 스트림	
		 	){
				memo = new XmlMapper().readValue(br, MemoVO.class);	// 역직렬화 + unmarshalling
			} 
		} else {	// parameter로 온 경우
			memo = new MemoVO();
			memo.setWriter(req.getParameter("writer"));
			memo.setContent(req.getParameter("content"));
			memo.setDate(req.getParameter("date"));
		}
		return memo;
				
//		// 역직렬화 -> unmarshalling
//		try(
//			BufferedReader br = req.getReader();				
// 		){
//			MemoVO memo = new MemoVO();		
//			String tmp = null;
//			StringBuffer strJson = new StringBuffer();
//			while((tmp = br.readLine()) != null) {
//				strJson.append(tmp+"\n");
//			}
//			ObjectMapper mapper = new ObjectMapper();			
//			memo = mapper.readValue(strJson.toString(), MemoVO.class);	
//					
//			System.out.println(memo);
//			return memo;
//		} 
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 아니 put 요청 왤케 마니 오냐;; -> put은 표준메소드 아님 그래서 redirect 처리를 ajax 안해줌 수동으로 해줘야함
		MemoVO memo = getMemoFromRequest(req);
		System.out.println(memo);
		int ret = dao.updateMemo(memo);
		
		//redirect
		String path = req.getContextPath() +"/memo";
//		resp.sendRedirect(path);
		// 수동으로 redirect 해줄거임 그래서 그냥 forward
		req.setAttribute("location", req.getContextPath() +"/memo");
		req.getRequestDispatcher("/jsonView.do").forward(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 왜 한번 더 들어올까???
		String pathInfo = req.getPathInfo();
		if(pathInfo == null) {
			return;
		}
		int code = Integer.parseInt(pathInfo.split("/")[1]);
//		System.out.println(code);
		int ret = dao.deleteMemo(code);
		
//		//redirect
//		String path = req.getContextPath() +"/memo";
//		resp.sendRedirect(path);
		// 수동으로 redirect 해줄거임 그래서 그냥 forward
		req.setAttribute("location", req.getContextPath() +"/memo");
		req.getRequestDispatcher("/jsonView.do").forward(req, resp);
	}
}

