package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	private PagingVO<ProdVO> pagingVO;
	private ProdVO prod = new ProdVO(); 
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		
		prod.setProdName("test");
		prod.setProdBuyer("P10101");
		prod.setProdCost(100);
		prod.setProdPrice(100);
		prod.setProdSale(90);
		prod.setProdOutline("daoTest");
		prod.setProdImg("asdf");
		prod.setProdTotalstock(1);
		prod.setProdProperstock(1);
		prod.setProdLgu("P401");
	}
	
	//@Test
	public void test() {
		ProdVO prodVO = dao.selectProd("P101000001");
		assertNotNull(prodVO);
		log.info("buyer : {}", prodVO.getBuyer());
		prodVO.getMemberSet().stream().forEach(user -> {
			log.info("구매자 : {}", user);
		});
	}
	
//	@Test
	public void testSelectTotal() {
		int tr = dao.selectTotalRecord(pagingVO);
		assertNotEquals(0,	tr);
	}
	
//	@Test
	public void testSelectProdList() {
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		assertEquals(10, prodList.size());
		log.info("prodList : {}",prodList);
	}
	
	@Test
	public void testInsert() {
		int cnt = dao.insertProd(prod);
		assertEquals(1, cnt);
	}
}
