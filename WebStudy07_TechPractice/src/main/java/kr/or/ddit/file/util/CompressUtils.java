package kr.or.ddit.file.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompressUtils {
	
	/**
	 * 참고 : <a href="https://commons.apache.org/proper/commons-compress/examples.html">압축 예제</a>
	 * @param files
	 * @param os
	 * @return TODO
	 * @throws IOException 
	 */
	public static long compress(File[] files, OutputStream os) throws IOException {
		try(
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(os);
		){
			for(File file : files) {
				
				ArchiveEntry archiveEntry = zaos.createArchiveEntry(file, file.getName());
				
				zaos.putArchiveEntry(archiveEntry);
				FileUtils.copyFile(file, zaos);
				
				zaos.closeArchiveEntry();				
			}
			zaos.finish();
			return zaos.getBytesWritten();
		}
	}
	
	
	public static long compressFolder(File root, OutputStream os) throws IOException {
		Path base = root.toPath();
		try(
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(os);
		){
			Files.walkFileTree(base, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					if(! base.equals(dir) ) {
						
						String entryName = base.relativize(dir).toString();
						ArchiveEntry archiveEntry = zaos.createArchiveEntry(dir, entryName);
						zaos.putArchiveEntry(archiveEntry);
						zaos.closeArchiveEntry();
						
						log.info("압축 대상 폴더에 상대적인 경로(entry name) : {}", entryName);
					}
					return super.preVisitDirectory(dir, attrs);
				}
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String entryName = base.relativize(file).toString();
					ArchiveEntry archiveEntry = zaos.createArchiveEntry(file, entryName);
					zaos.putArchiveEntry(archiveEntry);
					Files.copy(file, zaos);
					zaos.closeArchiveEntry();
					log.info("현재 압축 파일의 entry name : {}", entryName);
					return super.visitFile(file, attrs);
				}
			});			
			zaos.finish();
			return zaos.getBytesWritten();
		}
	}
	
	public static ResponseEntity<StreamingResponseBody> compressAndGenerateResponseEntity(File[] files, String name) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		long written = compress(files, baos);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", name))
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(written)
				.body(new StreamingResponseBody() {
						@Override
						public void writeTo(OutputStream os) throws IOException {
							try {
								IOUtils.copy(new ByteArrayResource(baos.toByteArray()).getInputStream(), os);
								os.flush();
							}finally {
								os.close();
							}
						}
				});
	}
	
	public static ResponseEntity<StreamingResponseBody> compressAndGenerateResponseEntity(File dir) throws Exception{
		String name = dir.getName()+".zip";
		return compressAndGenerateResponseEntity(dir, name);
	}
	
	public static ResponseEntity<StreamingResponseBody> compressAndGenerateResponseEntity(File dir, String name) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		long written = compressFolder(dir, baos);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attatchment; filename=\"%s\"", name))
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(written)
				.body(new StreamingResponseBody() {
					@Override
					public void writeTo(OutputStream os) throws IOException {
						try {
							IOUtils.copy(new ByteArrayResource(baos.toByteArray()).getInputStream(), os);
							os.flush();
						}finally {
							os.close();
						}
					}
				});
	}
}
