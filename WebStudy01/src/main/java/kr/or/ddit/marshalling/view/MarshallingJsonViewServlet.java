package kr.or.ddit.marshalling.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.do")
public class MarshallingJsonViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Marshaling : native(DataStore.properties) -> json		
		Object target = req.getAttribute("target");
		// 1. marshalling
		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(target);
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			// 2. serialization
//			out.print(json);
			mapper.writeValue(out, target);
		}
	
	}
	
}
