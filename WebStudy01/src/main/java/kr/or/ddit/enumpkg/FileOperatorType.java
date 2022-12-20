package kr.or.ddit.enumpkg;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import kr.or.ddit.servlet07.FileOperator;

public enum FileOperatorType {
	COPY( (src, dest, opt) -> {try {
		Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		e.printStackTrace();
	}} ),
	MOVE( (src, dest, opt) -> {try {
		Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		e.printStackTrace();
	}} );
	
	private FileOperator fileOperator;
	private FileOperatorType(FileOperator fileOperator) {
		this.fileOperator = fileOperator;
	}
	public void fileOperate(Path src, Path dest, CopyOption opt) {
		fileOperator.fileOperate(src, dest, opt);
	}
}
