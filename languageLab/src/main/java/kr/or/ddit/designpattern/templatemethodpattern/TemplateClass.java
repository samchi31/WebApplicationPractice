package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	// template method
	public final void template() {
		// 순서 정의
		stepOne();
		stepTwo();
		stepThree();		
	}
	
	// hook method
	private void stepOne() {
		System.out.println("1단계");
	}
	
	protected abstract void stepTwo();
	
	private void stepThree() {
		System.out.println("3단계");
	}
}
