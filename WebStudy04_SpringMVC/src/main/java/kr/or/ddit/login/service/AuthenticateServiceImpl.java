package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	
	/*결합력을 낮추기 위해 spring bean 사용*/
//	private MemberDAO dao = new MemberDAOImpl();
	
	private MemberDAO dao; 	// bean으로 등록되어 있다는 전제조건이 필요
	@Inject		// 생성자로 주입
	public AuthenticateServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}
	
	@Resource(name="passwordEncoder")		// 특정 하나 bean 만 (name = id)
	private PasswordEncoder encoder;	// 수동으로 bean에 등록
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member.getMemId());
		if(savedMember == null || savedMember.isMemDelete()) {
			throw new UserNotFoundException(String.format("%s 사용자 없음", member.getMemId()));
		}
		String inputPass = member.getMemPass();
		String savedPass = savedMember.getMemPass();
		ServiceResult result = null;
		
		if(/*savedPass.equals(inputPass)*/encoder.matches(inputPass, savedPass)) {
//			member.setMemName(savedMember.getMemName());
			try {
				BeanUtils.copyProperties(member, savedMember);
				result = ServiceResult.OK;
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

}
