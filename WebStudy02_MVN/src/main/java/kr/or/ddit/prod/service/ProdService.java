package kr.or.ddit.prod.service;

import kr.or.ddit.vo.ProdVO;

public interface ProdService {
	/**
	 * @param prodId
	 * @return 존재하지 않는 경우, RuntimeException
	 */
	public ProdVO retrieveProd(String prodId);
}
