package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PasswordEncoderTest {

	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	String password = "java-";
	String mem_pass = "{bcrypt}$2a$10$ERZ.3MgA7Ys2mZenzX/ZeO7FIycaJnu6zRLCVTCeupBF6dn2HHy/K";
	
	public void encodeTest() {
		String encoded = encoder.encode(password); // encrypt 후 encode 까지 해줌
		log.info("mem_pass : {}", encoded);
	}
		
	@Test
	public void matchTest() {
		log.info("match result : {}", encoder.matches(password, mem_pass));	// 단방향 이므로 복호화 불가능 
	}
}
