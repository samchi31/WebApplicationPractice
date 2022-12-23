package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * Factory Object[Method] Pattern
 * 	: 필요 객체의 생성을 전담하는 객체를 별도 운영하는 구조
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	
	private static DataSource ds;
	
	static {	// 메모리에 로딩 될 때 한 번 실행 됨
		String path = "/kr/or/ddit/db/dbInfo.Properties";
		
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream(path);
		) {
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initailSize")));	// 커넥션 최대 갯수
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle"))); 		// 다룰 수 있는 컬렉션 최대 갯수, InitialSize 와 같아야 한다 
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal")));	// 커넥션 최대 갯수 보다 요구가 더 들어오면 하나 더 만듬, 반납된게 있으면 그 다음 요구자에게 제공
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait"))); // 반납된거 없으면 2초 기다림, 그 후에도 없으면 exception 발생
			
			ds = bds;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() throws SQLException {
		
//		Connection conn = DriverManager.getConnection(url, user, password);
//		return conn;
		return ds.getConnection();
	}
}
