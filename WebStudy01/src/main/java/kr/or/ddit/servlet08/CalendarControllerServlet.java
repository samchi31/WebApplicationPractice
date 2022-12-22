package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/10/calendar.do")
public class CalendarControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		String strYear = req.getParameter("year");
		String strMonth = req.getParameter("month");
		String language = req.getParameter("language");
		
		Locale clientLocale = req.getLocale();
		if(language != null && !language.isEmpty()) {
			clientLocale = Locale.forLanguageTag(language);
		}
		
		Calendar calendar = Calendar.getInstance();
		try {
			if(strYear !=null && strMonth != null) {
				int year = Integer.parseInt(strYear);
				int month = Integer.parseInt(strMonth);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month);
			}
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		
		req.setAttribute("calendar", new CalendarWrapper(calendar, clientLocale));
		
		String viewName = "/WEB-INF/views/calendar.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
