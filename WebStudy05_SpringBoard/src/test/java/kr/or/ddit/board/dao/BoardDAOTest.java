package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class BoardDAOTest {
	
	@Inject
	private BoardDAO boardDAO;
	
	private PagingVO<BoardVO> pagingVO;
	private BoardVO board;
	
	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		board= new BoardVO();
		board.setBoNo(123);
		board.setBoTitle("테스트1");
		board.setBoWriter("update테스트");
		board.setBoIp("127.0.0.1");
		board.setBoMail("asdf@qewr.zxcv");
		board.setBoContent("내용테스트");
	}
	
	@Test
	public void test() {
		log.info("주입된 객체: {}", boardDAO);
	}

	@Test
	public void testInsertBoard() {
		int cnt = boardDAO.insertBoard(board);
		log.info("{}",cnt);
	}

	@Test
	public void testSelectBoardList() {
		List<BoardVO> dataList = boardDAO.selectBoardList(pagingVO);
		assertNotEquals(0, dataList.size());
	}

	@Test
	public void testSelectTotalRecord() {
		log.info("{}", boardDAO.selectTotalRecord(pagingVO));
	}

	@Test
	public void testSelectBoard() {
		BoardVO boardVO = boardDAO.selectBoard(112);
		log.info("{}",boardVO);
		assertNotNull(boardVO);
		boardVO.getAttatchList().stream().forEach(System.out::println);
	}

	@Test
	public void testUpdateBoard() {
		int rowcnt = boardDAO.updateBoard(board);
		assertEquals(1, rowcnt);
	}

//	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

}
