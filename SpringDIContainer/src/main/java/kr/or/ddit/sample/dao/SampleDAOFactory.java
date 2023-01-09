package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampleDAO() {
//		return new SampleDAOImpl_Oracle();
		return new SampleDAOImpl_Postgre();
	}
}
