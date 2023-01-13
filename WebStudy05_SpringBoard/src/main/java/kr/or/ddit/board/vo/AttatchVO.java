package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of="attNo")
@NoArgsConstructor
public class AttatchVO implements Serializable{
	
	private MultipartFile realFile;
	
	public AttatchVO(MultipartFile realFile) {
		super();
		this.realFile = realFile;
		this.attFilename = realFile.getOriginalFilename();
		this.attSavename = UUID.randomUUID().toString();
		this.attMime = realFile.getContentType();
		this.attFilesize = realFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
	}
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private Long attFilesize;
	private String attFancysize;
	private Integer attDownload;
}
