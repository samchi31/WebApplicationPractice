package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	@Test
	public void test() {
		ProdVO prodVO = dao.selectProd("P101000001");
		assertNotNull(prodVO);
		log.info("buyer : {}", prodVO.getBuyer());
		prodVO.getMemberSet().stream().forEach(user -> {
			log.info("구매자 : {}", user);
		});
	}

}
