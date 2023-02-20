package kr.or.ddit.exception;

import java.util.function.Consumer;

@FunctionalInterface
public interface ExceptionThrowingConsumer<T,E extends Exception>{
	public void acceptThrowException(T s) throws E;
	
	public static <T,E extends Exception> Consumer<T> consumerWrapping(ExceptionThrowingConsumer<T,E> consumer) {
		return s->{
			try {
				consumer.acceptThrowException(s);
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}
