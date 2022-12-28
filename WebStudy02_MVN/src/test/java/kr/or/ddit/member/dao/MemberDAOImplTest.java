package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	
	private MemberDAO dao = new MemberDAOImpl();
	private MemberVO member;
	@Before
	public void setUp() throws Exception {
		member = new MemberVO();
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("2011-11-11");
		member.setMemZip("1234");
		member.setMemAdd1("asdf");
		member.setMemAdd2("1234");
	}

//	@Test
	public void testInsertMember() {
		dao.insertMember(member);
	}

	//@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		memberList.stream().forEach(System.out::println);
	}

//	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		System.out.println(member);
//		member=dao.selectMember("1q234");
//		assertNull(member);
	}

	@Test
	public void testUpdateMember() {
		int ret = dao.updateMember(member);
	}

	//@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
