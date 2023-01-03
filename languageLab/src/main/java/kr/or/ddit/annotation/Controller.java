package kr.or.ddit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)		// tracing의 대상이 되려면 runtime으로
@Target(ElementType.TYPE)
public @interface Controller {

}
