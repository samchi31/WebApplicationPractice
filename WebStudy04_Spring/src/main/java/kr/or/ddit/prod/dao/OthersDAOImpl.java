package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MyBatisUtils;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements OthersDAO {
	
	private SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
			return mapperProxy.selectLprodList();
		}	
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyerLgu) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
			return mapperProxy.selectBuyerList(buyerLgu);
		}	
	}

}
