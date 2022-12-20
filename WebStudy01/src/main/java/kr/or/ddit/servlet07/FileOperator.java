package kr.or.ddit.servlet07;

import java.nio.file.CopyOption;
import java.nio.file.Path;

@FunctionalInterface
public interface FileOperator {
	public void fileOperate(Path src, Path dest, CopyOption copyOpt);
}
