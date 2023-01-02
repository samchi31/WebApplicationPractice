package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class ValidationUtils {
	private static Validator validator;
	
	static {
		String messageBaseName = "kr.or.ddit.msgs.errorMessage";
		ValidatorFactory factory = Validation.byDefaultProvider()
			        .configure()
			        .messageInterpolator(
			                new ResourceBundleMessageInterpolator(
			                        new PlatformResourceBundleLocator( messageBaseName )
			                )
			        )
			        .buildValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, List<String>> errors, Class<?>...groups) {
		boolean valid = true;
		// 검증 오류들
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(target, groups);
		// 비어있으면 검증 통과
		valid = constraintViolations.isEmpty();
		
		if(!valid) {
			constraintViolations.stream().forEach(single -> {
				String propertyName = single.getPropertyPath().toString();
				String errorMessage = single.getMessage();
				// 키가 있으면 추가, 없으면 새로 추가
				List<String> errorMessages = Optional.ofNullable(errors.get(propertyName))
					.orElse(new ArrayList<String>());
				errorMessages.add(errorMessage);
				errors.put(propertyName, errorMessages);
			});
		}
		
		return valid;
	}
}
