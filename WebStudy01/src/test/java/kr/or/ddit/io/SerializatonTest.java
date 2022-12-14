package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemoVO;

/**
 * Serialization(직렬화)
 * : 전송이나 저장을 위해 객체의 상태를 바이트 배열로 변환하는 과정
 * implements Serializable한 Object만 가능하다 
 */
public class SerializatonTest {
	
//	private Map<String, Object> writeData;
	private MemoVO writeData;
	private File writeFile;
	
	@Before
	public void setUp() {
//		writeData = new HashMap<>();
//		writeData.put("code1", new Integer(23));
//		writeData.put("code2", "TEXT");
//		writeData.put("code3", new Boolean(true));
		
		writeData = new MemoVO();
		writeData.setCode(1);
		writeData.setWriter("작성자");
		writeData.setContent("내용");
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS",LocalDateTime.now());
		writeData.setDate(date);
		
		writeFile = new File("d:/sample.dat");
	}
	
	//@Test
	public void serializeTest() throws IOException {
		try(
			FileOutputStream fos = new FileOutputStream(writeFile);
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
		){
			oos.writeObject(writeData);
		}
	}
	
	@Test
	public void deserializeTest() throws IOException, ClassNotFoundException {
		try(
			FileInputStream fis = new FileInputStream(writeFile);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
		){
			Object obj = ois.readObject();
//			Map<String, Object> map = (Map<String, Object>)obj;
			MemoVO memo = (MemoVO) obj;
			System.out.println(memo);
		}
	}
	
}
