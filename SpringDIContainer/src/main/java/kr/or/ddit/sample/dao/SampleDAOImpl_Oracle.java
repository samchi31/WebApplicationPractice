package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("daoOracle")
public class SampleDAOImpl_Oracle implements SampleDAO {

	private Map<String, String> dummyDB;
	
	public void init1() {
		log.info("{} 객체 초기화", getClass().getSimpleName());
	}
	public void destroy2() {
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}
	
	public SampleDAOImpl_Oracle(Map<String, String> dummyDB) {
		super();
		log.info("{} 객체 생성, 생성자 주입 dummyDB 주입 ", getClass().getSimpleName());
		this.dummyDB = dummyDB;
//		dummyDB = new HashMap<>();
//		int idx=0;
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 " + idx);
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 " + idx);
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 " + idx);
	}

	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey);
	}

}
