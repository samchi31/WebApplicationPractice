package kr.or.ddit.ftp;

import org.apache.commons.net.ftp.FTPFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.uiplugin.fancytree.FancyTreeNodeAdapter;

public class FTPFileWrapper extends FancyTreeNodeAdapter<FTPFile> {

	public FTPFileWrapper(FTPFile data) {
		super(data);
	}

	@Override
	public String getTitle() {
		return getData().getName();
	}

	@Override
	public String getKey() {
		return getData().getName();
	}

	@Override
	public boolean isFolder() {
		return getData().isDirectory();
	}

	@JsonIgnore
	@Override
	public FTPFile getData() {
		return super.getData();
	}

}
