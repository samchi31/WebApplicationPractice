package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl extends AbstractJDBCDAO implements MemberDAO {

	private Map<String, Statement> statementmap;
	
	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	@Override
//	public <T> T resultBindingForOneRecord(ResultSet rs, Class<T> resultType) {
//		try {
//			T resultObject = resultType.newInstance();			
//			
//			MemberVO vo = (MemberVO) resultObject;
//			
//			vo.setMemId(rs.getString("MEM_ID"));
//			vo.setMemName(rs.getString("MEM_NAME"));
//			vo.setMemAdd1(rs.getString("MEM_ADD1"));
//			vo.setMemHp(rs.getString("MEM_HP"));
//			vo.setMemJob(rs.getString("mem_mail"));
//			vo.setMemMileage(rs.getInt("MEM_MILEAGE"));			
//			
//			return resultObject;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} 
//		
//	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		// 1. 쿼리 작성
		sql.append("SELECT mem_id, mem_name, mem_mail, mem_hp, mem_add1, mem_mileage ");
		sql.append("FROM member ");
		
		return selectList(sql.toString(), MemberVO.class);
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();
		// 1. 쿼리 작성
		sql.append("SELECT ");
		sql.append("    mem_id,    mem_pass,    mem_name, ");
		sql.append("    mem_regno1,    mem_regno2,    to_char(mem_bir, 'YYYY-MM-DD')  mem_bir, ");
		sql.append("    mem_zip,    mem_add1,    mem_add2, ");
		sql.append("    mem_hometel,    mem_comtel,    mem_hp, ");
		sql.append("    mem_mail,   mem_job,    mem_like, ");
		sql.append("    mem_memorial,    to_char(mem_memorialday, 'YYYY-MM-DD') mem_memorialday,    mem_mileage, ");
		sql.append("    mem_delete ");
		sql.append("FROM    member ");
		sql.append("where mem_id = ? ");

		return selectOne(sql.toString(), MemberVO.class, memId);
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
