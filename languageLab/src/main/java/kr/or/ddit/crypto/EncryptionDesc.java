package kr.or.ddit.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * encode / decode
 * 	encoding(부호화) : 전송이나 저장을 위해 매체(media)가 이해할 수 있는 방식으로 데이터의 표현 방식을 바꾸는 작업
 * 						ex) Base64, URLEncoding(percent encoding)
 * encrypt / decrypt
 * 	encrypting(암호화) : 권한 없는 사용자가 snipping 하거나 spooping 하는 걸 막기 위해 데이터 표현을 바꾸는 작업
 * 	- 단방향 암호화(hash 함수) : 암호화된 이후 평문 복원이 불가능한 방식(비밀번호 암호화에 활용)
 * 								 다양한 길이의 입력 데이터에 만들어지는 결과 데이터가 길이가 동일한 경우
 * 	- 양방향 암호화 : 암호문에서 원래 평문으로 복호화가 가능한 방식
 * 		- 대칭키 방식 : 하나의 비밀키로 암호화와 복호화에 모두 사용
 * 			ex) AES(128, 256)	(ebook)
 * 		- 비대칭키 방식 : 공개키와 개인키 한쌍의 키로 암호화와 복호화에 다른 키를 사용하는 방식
 * 			ex) RSA(2048)	(전자서명)
 * 
 */
public class EncryptionDesc {
	public static void main(String[] args) throws Exception{
		String plain = "java";
//		encryptAESTest(plain);
		
		// 양방향 비대칭키
		KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
		pairGen.initialize(2048);
		KeyPair keyPair = pairGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate(); 	// 개인키
		PublicKey publicKey = keyPair.getPublic(); 		// 공개키 - 네트워크에 공유
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);	// 부호화는 개인키
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
		byte[] decoded = Base64.getDecoder().decode(encoded);		// =encrypted
		cipher.init(Cipher.DECRYPT_MODE, publicKey); 				// 복호화는 공개키
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String(decrypted));
	}
	
	// 양방향 대칭키
	private static void encryptAESTest(String plain) throws Exception {
		String ivValueText = "초기화벡터";								// CBC 방식일 경우 첫번째 블럭은 xor 연산할 블럭이 없음
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest(ivValueText.getBytes());
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");		// AES : 블럭 암호화 방식
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(/*128*/256);
		SecretKey key = keyGen.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		
		System.out.println(encoded);
		
		byte[] decoded = Base64.getDecoder().decode(encoded); 		// =encrypted
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);				// 암호화, 복호화 키가 같다
		byte[] decrypted = cipher.doFinal(decoded); 			
		System.out.println(new String(decrypted));
	}
	
	// 단방향
	private static String encryptSha512(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encrypted = md.digest(input);
//			System.out.println(encrypted.length);
			String encoded = Base64.getEncoder().encodeToString(encrypted);
//			System.out.println(encoded);
			return encoded;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void encodeTest() throws UnsupportedEncodingException {
		String plain = "원문데이터";
		String base64Encoded = Base64.getEncoder().encodeToString(plain.getBytes());
		System.out.println(base64Encoded);
		URLEncoder.encode(plain, "UTF-8");
		String urlEncoded = URLEncoder.encode(plain, "UTF-8");
		System.out.println(urlEncoded);
		
		System.out.println(new String(Base64.getDecoder().decode(base64Encoded)));
		System.out.println(URLDecoder.decode(urlEncoded, "UTF-8"));
	}
}
