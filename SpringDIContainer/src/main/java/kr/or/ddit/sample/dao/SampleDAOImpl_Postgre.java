package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Postgre implements SampleDAO {

	private Map<String, String> dummyDB;
	
	public void init1() {
		log.info("{} 객체 초기화", getClass().getSimpleName());
	}
	public void destroy2() {
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}
	
	
	public SampleDAOImpl_Postgre() {
		super();
		log.info("{} 객체 생성 setter 주입으로 dummyDB 주입", getClass().getSimpleName());
//		dummyDB = new HashMap<>();
//		int idx=0;
//		dummyDB.put("PK_"+ ++idx, "postgre 레코드 " + idx);
//		dummyDB.put("PK_"+ ++idx, "postgre 레코드 " + idx);
//		dummyDB.put("PK_"+ ++idx, "postgre 레코드 " + idx);
	}

	public void setDummyDB(Map<String, String> dummyDB) {
		this.dummyDB = dummyDB;
	}
	
	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey);
	}

}
