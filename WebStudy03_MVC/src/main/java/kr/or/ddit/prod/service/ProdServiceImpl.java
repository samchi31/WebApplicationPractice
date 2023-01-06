package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {

	private ProdDAO prodDao = new ProdDAOImpl();
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDao.selectProd(prodId);
		if(prod == null) {
			throw new RuntimeException(String.format("%s는 없는 상품",prodId));
		}
		return prod;
	}

	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) {
		pagingVO.setTotalRecord(prodDao.selectTotalRecord(pagingVO));
		List<ProdVO> prodList = prodDao.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = prodDao.insertProd(prod);
		return cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());	// 없으면 runtimeException 발생
		int cnt = prodDao.updateProd(prod);
		return cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
