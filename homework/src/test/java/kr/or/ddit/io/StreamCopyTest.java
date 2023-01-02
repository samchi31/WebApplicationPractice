package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StreamCopyTest {
	
	private File targetFile;
	private File destFile;
	
	@Before
	public void setUp() {
		targetFile = new File("D:/contents/movies/target.mp4");
		destFile = new File("d:/target.mp4");
	}
	
	//@Test
	public void copyTest1() throws IOException {
		try(
			FileInputStream fis = new FileInputStream(targetFile);
			FileOutputStream fos = new FileOutputStream(destFile);
		){
			//EOF,EOS : null, -1
			int tmp = -1;
			while((tmp = fis.read()) != -1) {
				fos.write(tmp);
			}
		} 
	}
	
	//@Test
	public void copyTest2() throws IOException {
		try(
			FileInputStream fis = new FileInputStream(targetFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
		){
			byte[] buffer = new byte[1024];
			int length = -1;
			int count = 1;
			while((length = bis.read(buffer)) != -1) {
				if(count++ ==1) {
					// 첫번째 반복
					Arrays.fill(buffer, (byte)0); // 앞부분 짤림
				}
				fos.write(buffer, 0, length);
			}
		} 
	}
	
	@Test
	public void test() throws IOException {
		
		try(
			FileInputStream fis = new FileInputStream(targetFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
		){
			int tmp = 0;
			
			while((tmp = bis.read()) != -1) {
				bos.write(tmp);
			}
			
			bos.flush();		
		}
	}
}
