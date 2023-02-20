package kr.or.ddit.file.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class CompressUtilsTest {

	@Test
	public void testCompress() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompressFolder() throws IOException {
		File folder = new File("D:\\00.medias");
		File zipFile = new File("d:/media.zip");
		try(
			FileOutputStream fos = new FileOutputStream(zipFile);	
		){
			CompressUtils.compressFolder(folder, fos);
		}
	}

}
