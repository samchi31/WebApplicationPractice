package kr.or.ddit.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(of="menuCode")
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class MenuVO {
	private String menuCode;
	private String menuText;
	private String menuURL;
	private String menuColor;
}
