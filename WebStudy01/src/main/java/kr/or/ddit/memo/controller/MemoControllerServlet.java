package kr.or.ddit.memo.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo")
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
		// Post(비동기)-(SC300)Redirect-Get : PRG pattern (accept 헤더는 유지된다)
		
		String accept = req.getHeader("Accept");
		
		MemoVO memo = getMemoFromRequest(req);
		int res = dao.insertMemo(memo);
		
		//redirect
		String path = req.getContextPath() +"/memo";
		resp.sendRedirect(path);
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) {
		MemoVO memo = new MemoVO();
		
		Map<String,String> map = new HashMap<String, String>();
		
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()) {
//			System.out.println(parameterNames.nextElement());
			String name = parameterNames.nextElement();
			String value = req.getParameter(name);
			map.put(name,value);
		}
		
		memo.setWriter(map.get("writer"));
		memo.setDate(map.get("date"));
		memo.setContent(map.get("content"));

		return memo;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}

