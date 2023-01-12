package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.AbstractTestCase;
import kr.or.ddit.RootContextConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RunWith(SpringRunner.class)	// 1. spring과 junit 연동해서 junit context 구성
//@RootContextConfiguration
public class MemberDaoTest extends AbstractTestCase{
	
	@Inject
	private MemberDAO memberDAO;
	
	@Test
	public void test() {
		log.info("주입된 객체: {}", memberDAO);
	}
}
