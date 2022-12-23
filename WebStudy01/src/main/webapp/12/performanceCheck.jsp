<%@page import="java.sql.SQLException"%>
<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/performanceCheck.jsp</title>
</head>
<body>
<h4>성능 고려사항</h4>
<pre>
	응답 소요 시간 : latency time(99.999%) + processing time
	case 1 - 9ms 		con 1 실행 1 
	case 2 - 819ms	    con 100  실행 100  // connection 네트워크 지연 시간 때문 -> connection 미리 생성 : pooling
	case 3 - 22ms       con 1 실행 100
	case 4 - 30ms		con 100  실행 100	// database connection pooling 사용
	
	<%
		long startTime = System.currentTimeMillis();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM tbl_memo order by code");
		for(int i=0; i<100; i++){
		
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
			} catch(SQLException e){
				throw new RuntimeException(e);
			}
		
		}
		long endTime = System.currentTimeMillis();
	%>
	소요시간 : <%=endTime-startTime %>ms
</pre>
</body>
</html>