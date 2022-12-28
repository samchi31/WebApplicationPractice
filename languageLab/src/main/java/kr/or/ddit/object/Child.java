package kr.or.ddit.object;

public class Child extends Parent{
	@Override
	public void method() {
		System.out.println("child method execute");
	}
	
	@Override
	public void template() {
//		method();
		super.method();		// Parent.method()
	}
}
