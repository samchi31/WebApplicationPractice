package kr.or.ddit.di;

import lombok.Data;

@Data
public class DBInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	private int initialSize;
	private int maxTotal;
	private int maxIdle;
	private long maxWait;
}
