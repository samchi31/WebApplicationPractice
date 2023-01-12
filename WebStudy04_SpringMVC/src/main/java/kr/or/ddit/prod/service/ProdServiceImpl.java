package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdServiceImpl implements ProdService {

	private final ProdDAO prodDao;
	
	@Inject
	private WebApplicationContext context;	// container
	private File saveFolder;
	@PostConstruct
	public void init() throws IOException {
		String saveFolderURL = "/resources/prodImages";
		// spring container는 그 자체로 resourceloader가 된다
		Resource saveFolderRes = context.getResource(saveFolderURL);
		saveFolder = saveFolderRes.getFile();
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	private void processProdImage(ProdVO prod) {
		try {
			if(1==1) throw new RuntimeException("트랜잭션 관리 여부 확인 에러");
			prod.saveTo(saveFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
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

	@Inject
	private SqlSessionFactory SqlSessionFactory;
	@Override
	public ServiceResult createProd(ProdVO prod) {
		//session open
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			int cnt = prodDao.insertProd(prod, sqlSession); // 트랜잭션 롤백하면됨
			processProdImage(prod);	// 원복하기 힘듬
			sqlSession.commit();	
			return cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());	// 없으면 runtimeException 발생
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			int cnt = prodDao.updateProd(prod, sqlSession); // 트랜잭션 롤백하면됨
			processProdImage(prod);	// 원복하기 힘듬
			sqlSession.commit();	
			return cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
	}

}
