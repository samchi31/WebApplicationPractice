package kr.or.ddit.prod.dao;

import kr.or.ddit.vo.ProdVO;

public interface ProdDAO {
	/**
	 * @param prodId
	 * @return 존재 하지 않으면 null
	 */
	public ProdVO selectProd(String prodId);
}
