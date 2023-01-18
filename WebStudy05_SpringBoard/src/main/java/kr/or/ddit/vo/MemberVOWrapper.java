package kr.or.ddit.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	private MemberVO realMember;

	public MemberVOWrapper(MemberVO realMemberVO) {
		super(realMemberVO.getMemId(), realMemberVO.getMemPass()
				, AuthorityUtils.createAuthorityList(realMemberVO.getMemRole()));
		this.realMember = realMemberVO;
	}
	
	public MemberVO getRealMember() {
		return realMember;
	}
}
