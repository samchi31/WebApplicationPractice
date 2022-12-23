package kr.or.ddit.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemoVO;

public class DataBaseMemoDAOImpl implements MemoDAO {
	
	private static MemoDAO instance;
	private DataBaseMemoDAOImpl() {
		super();
	}
	public static MemoDAO getInstance() {	// 싱글톤 -> 객체 풀링
		if(instance == null) instance = new DataBaseMemoDAOImpl();
		return instance;
	}

	@Override
	public List<MemoVO> selectMemoList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM tbl_memo order by code");

		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			List<MemoVO> list = new ArrayList<>();	
			while(rs.next()){
				MemoVO vo = new MemoVO();
				vo.setCode(Integer.parseInt(rs.getString("code")));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getString("date"));
				list.add(vo);
			}
			return list;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_MEMO (CODE, WRITER, CONTENT, \"DATE\") VALUES (memo_seq.nextval, ?, ?, ?)  ");

		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, memo.getWriter());
			pstmt.setString(2, memo.getContent());
			pstmt.setString(3, memo.getDate());
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tbl_memo ");
		sql.append("SET  writer =?, content =?, \"DATE\" =? ");
		sql.append("WHERE code =? ");

		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, memo.getWriter());
			pstmt.setString(2, memo.getContent());
			pstmt.setString(3, memo.getDate());
			pstmt.setInt(4, memo.getCode());
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMemo(int code) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM tbl_memo ");
		sql.append("WHERE code = ? ");

		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, code);			
			int cnt = pstmt.executeUpdate();
			return cnt;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
