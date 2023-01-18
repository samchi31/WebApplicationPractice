package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.AbstractTestCase;
import kr.or.ddit.RootContextConfiguration;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceTest extends AbstractTestCase{
	
	@Inject
	private MemberService memService;
	
	private MemberVO member;
	@Before
	public void init() {
		member = new MemberVO();
//		member.
	}
	
	@Test
	public void test() {
		log.info("{}", memService);
	}
	
	@Test
	public void createMember() {
		ServiceResult result = memService.createMember(member);
		log.info("{}", result);
	}
	
	public void testretrieveMemberList() {
//		memService.retrieveMemberList(pagingVO);
	}
	@Test
	public void testretrieveMember() {
		memService.retrieveMember("z003");
	}
	
	public void testmodifyMember() {
		memService.modifyMember(member);
	}
	
	public void testremoveMember() {
		memService.removeMember(member);
	}
}
