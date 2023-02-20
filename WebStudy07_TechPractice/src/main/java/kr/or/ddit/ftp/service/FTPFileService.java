package kr.or.ddit.ftp.service;

import java.io.File;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.web.multipart.MultipartFile;

public interface FTPFileService {
	/**
	 * FTP 서버상의 특정 디렉토리에 대한 탐색
	 * @param path 탐색 위치
	 * @return
	 * @throws Exception
	 */
	public List<FTPFile> traversing(String path) throws Exception;
	/**
	 * FTP 서버로의 파일 전송
	 * @param path 저장 위치
	 * @param file 전송할 파일
	 * @return
	 * @throws Exception
	 */
	public boolean sendFile(String path, MultipartFile file) throws Exception;
	/**
	 * FTP 서버로부터 파일/폴더 수신
	 * @param path
	 * @return 해당 파일/폴더가 저장된 임시 폴더
	 * @throws Exception
	 */
	public File receiveFile(String path) throws Exception;
	/**
	 * FTP 서버로부터 여러개의 파일/폴더 수신
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public File receiveFiles(String[] paths) throws Exception;
}
