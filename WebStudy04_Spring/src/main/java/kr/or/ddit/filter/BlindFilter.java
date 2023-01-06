package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlindFilter implements Filter{
	
	private Map<String, String> blindMap;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", this.getClass().getName());
		blindMap = new LinkedHashMap<>();
		blindMap.put("127.0.0.1", "나니까 블라인드");
		blindMap.put("0:0:0:0:0:0:0:1", "나니까 블라인드");
		blindMap.put("192.168.35.24", "나니까 블라인드");
		blindMap.put("192.168.35.89", "옆자리 블라인드");
		blindMap.put("192.168.35.23", "옆자리 블라인드");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 클라이언트 ip adress -> 판단 -> 아니면 서비스 이용가능 -> 대상자면 blind 사유 메세지 알려주면서 blind
		log.info("blind filter 동작 시작");
		log.info(request.getRemoteAddr());	// 다른사람
//		log.info(request.getLocalAddr());	// 나
		String ipAddress = request.getRemoteAddr();
		if(blindMap.containsKey(ipAddress)) {
			request.setAttribute("message", String.format("당신은 %s 라는 이유로 처리됐슴다",blindMap.get(ipAddress)));
			request.getRequestDispatcher("/WEB-INF/views/commons/messageView.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
		log.info("blind filter 동작 종료");
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", this.getClass().getName());
	}
	
}
