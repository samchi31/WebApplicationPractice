package kr.or.ddit.etc.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * FN_CREATE_CARTNO 와 PROC_CART01 을 실행하기 위한 DAO
 *
 */
@Mapper
public interface ProceduralDAO {
	public void call_FN_CREATE_CARTNO(Map<String, Object> parameters);
	public void call_PROC_CART01(Map<String, Object> parameters);
}
