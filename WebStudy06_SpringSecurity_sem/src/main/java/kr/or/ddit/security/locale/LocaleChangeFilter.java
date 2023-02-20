package kr.or.ddit.security.locale;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

public class LocaleChangeFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.findWebApplicationContext(request.getServletContext());
		if(context.containsBean("localeResolver")) {
			LocaleResolver resolver = context.getBean("localeResolver", LocaleResolver.class);
			LocaleContextHolder.setLocale(resolver.resolveLocale(request));
		}else {
			LocaleContextHolder.setLocale(request.getLocale());
		}
		
		try {
			filterChain.doFilter(request, response);
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
}
