package kr.or.ddit.uiplugin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Mapper
public interface ProdDAO {
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
}
