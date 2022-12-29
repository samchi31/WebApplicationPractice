package kr.or.ddit.prod.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MyBatisUtils;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO{
	
	private SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
	
	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectProd(prodId);
		}	
	}

}
