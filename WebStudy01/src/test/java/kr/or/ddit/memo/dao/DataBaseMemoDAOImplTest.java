package kr.or.ddit.memo.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemoVO;

public class DataBaseMemoDAOImplTest {
	
	private MemoDAO dao = DataBaseMemoDAOImpl.getInstance();
	private MemoVO memo;
	@Before
	public void setUp() throws Exception {
		memo = new MemoVO();
		memo.setWriter("작성자1");
		memo.setContent("내용1");
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS",LocalDateTime.now());
		memo.setDate(date);
	}

	@Test
	public void test1() {
		List<MemoVO> memoList = dao.selectMemoList();
		memoList.stream().forEach(System.out::println);
	}
	
	@Test
	public void testInsertMemo() {
		dao.insertMemo(memo);
	}

}
