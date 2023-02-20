package kr.or.ddit.ftp;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Test;

import kr.or.ddit.AbstractRootTestCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FTPClientPoolTest extends AbstractRootTestCase{

	@Resource(name="ftpClientPool")
	private GenericObjectPool<FTPClient> ftpClientPool;
	
	@Test
	public void test() throws Exception {
		FTPClient client = ftpClientPool.borrowObject();
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpClientPool.returnObject(client);
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
	}

}
