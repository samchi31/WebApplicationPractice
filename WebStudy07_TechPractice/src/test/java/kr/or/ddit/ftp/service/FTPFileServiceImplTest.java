package kr.or.ddit.ftp.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.AbstractRootTestCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FTPFileServiceImplTest extends AbstractRootTestCase{
	@Inject
	WebApplicationContext context;
	
	@Inject
	public FTPFileService ftpService;
	@Resource(name="ftpClientPool")
	private GenericObjectPool<FTPClient> ftpClientPool;
	
	@Test
	public void testTraversing() throws Exception {
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/group01");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/cat1.jpg");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
	}
	
	@Test(expected=IOException.class)
	public void testTraversingException() throws Exception {
		ftpService.traversing("/notExist");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
	}

	@Test
	public void testSendFile() throws Exception {
		org.springframework.core.io.Resource testFile = context.getResource("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		MockMultipartFile file = new MockMultipartFile("test", testFile.getFilename(), "", testFile.getInputStream());
		ftpService.sendFile("/", file);
	}
	
	@Test(expected=IOException.class)
	public void testSendFileException() throws Exception {
		org.springframework.core.io.Resource testFile = context.getResource("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		MockMultipartFile file = new MockMultipartFile("test", testFile.getFilename(), "", testFile.getInputStream());
		ftpService.sendFile("/notExist", file);
	}

	@Test
	public void testReceiveFile() throws Exception {
		 File saveFile = ftpService.receiveFile("/group01/images/cute4.jpg");
		 log.info("저장 위치 : {}", saveFile.getCanonicalPath());
		 Arrays.stream(saveFile.listFiles()).forEach(System.out::println);
	}
	
	@Test(expected=IOException.class)
	public void testReceiveFileException() throws Exception {
		ftpService.receiveFile("/notExist");
	}

	
	@Test
	public void testReceiveFiles() throws Exception{
		String[] paths = {
			"/cat1.jpg", "/group01/cute4.jpg", "/group01/images/cute4.jpg"	
		};
		File saveFile = ftpService.receiveFiles(paths);
		System.out.println(saveFile);
	}
}
