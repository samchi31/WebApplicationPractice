package kr.or.ddit.memo;

import kr.or.ddit.memo.controller.MemoService;

// 1. new 키워드 삭제, 싱글턴 삭제, 컨테이너 객체 생성시에만 사용
// 2. 초프에다가 spring 붙여보기

public class MemoTestView {
	public static void main(String[] args) {
		MemoService service = new MemoService();
		service.retrieveMemoList().forEach(System.out::println);
	}
}
