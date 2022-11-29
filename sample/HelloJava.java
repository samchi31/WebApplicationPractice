package kr.or.ddit;
import kr.or.ddit.service.DataService;

public class HelloJava{
	public static void main(String[] args){
		DataService service = new DataService();
		String data = service.getData();
		System.out.println("hello java, "+data); 
	}
}