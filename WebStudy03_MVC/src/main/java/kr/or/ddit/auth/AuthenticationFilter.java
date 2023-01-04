package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 보호자원에 대한 요청인 경우, 신원확인(session authMember 속성)을 한 사용자인지 판단
 *
 */
@Slf4j
public class AuthenticationFilter implements Filter{
	
	private Map<String, String[]> securedResources;
	public static final String SECUREDNAME = "securedResources";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", this.getClass().getName());
		
		securedResources = new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute(SECUREDNAME, securedResources);
		String filePath = filterConfig.getInitParameter("filePath");
		try(
			InputStream is = this.getClass().getResourceAsStream(filePath);
		){
			Properties props = new Properties();
			props.load(is);
			props.keySet().stream().map(Object::toString)/*.collect(Collectors.toList())*/.forEach(key -> {
				String value = props.getProperty(key);
				securedResources.put(key, value.split(","));
				log.info(" 보호 자원 [{} : {}]", key, value);
			});
		} catch (IOException e) {
			throw new ServletException(e);
		}		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// session attribute 확인 properties 읽기 비교
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getServletPath();
		
		boolean pass = true;
		
		if(securedResources.containsKey(uri)) {
			// 보호 자원
			Object authMember = req.getSession().getAttribute("authMember");
			// 세션 있는지
			if(authMember == null) {
				pass = false;
			}
		}
		
		if(pass) {
			chain.doFilter(request, response);			
		} else {
			// 신원확인을 거치지 않은 사용자인 경우 loginform 이동, redirect
			String viewName = req.getContextPath() + "/login/loginForm.jsp";
			resp.sendRedirect(viewName);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
