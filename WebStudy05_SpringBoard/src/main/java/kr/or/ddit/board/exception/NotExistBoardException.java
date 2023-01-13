package kr.or.ddit.board.exception;

public class NotExistBoardException extends RuntimeException{

	public NotExistBoardException(String message) {
		super(String.format("%d 번의 글은 존재하지 않습니다", message));
	}
	
}
