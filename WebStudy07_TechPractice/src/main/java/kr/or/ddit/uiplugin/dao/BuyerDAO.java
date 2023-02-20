package kr.or.ddit.uiplugin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface BuyerDAO {
	public int selectTotalRecord(PagingVO<BuyerVO> pagingVO);
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
}
