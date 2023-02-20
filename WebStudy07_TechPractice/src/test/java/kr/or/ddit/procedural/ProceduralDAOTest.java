package kr.or.ddit.procedural;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.AbstractRootTestCase;
import kr.or.ddit.etc.dao.ProceduralDAO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProceduralDAOTest extends AbstractRootTestCase{
	
	@Inject
	private ProceduralDAO dao;
	
	private Map<String, Object> parameters;
	
	@Before
	public void before() {
		parameters = new HashMap<>();
	}

	@Test
	public void testCall_FN_CREATE_CARTNO() {
		parameters.put("pDate", new Date());
		dao.call_FN_CREATE_CARTNO(parameters);
		log.info("cart number : {}", parameters.get("cartNo"));
	}

	@Test
	public void testCall_PROC_CART01() {
		parameters.put("pPeriod", "202004");
		dao.call_PROC_CART01(parameters);
		parameters.entrySet().stream()
			.filter(e->! e.getKey().equals("pPeriod"))
			.forEach(e->{
				Object value = e.getValue();
				if(Number.class.isAssignableFrom(e.getValue().getClass())) {
					value = MessageFormat.format("{0,number,currency}", e.getValue());
				}
				log.info("{} --> {} typeof {}", e.getKey(), e.getValue().getClass(), value);
			});
			
	}
}






