package hk.edu_20250709.day06;

public class D3_SingletonTest {

	//싱글톤 패턴: 객체를 한번만 생성해서 사용하자 --> 메모리를 효율적으로 쓰자
	private static D3_SingletonTest st;
	private D3_SingletonTest() {}	//private 접근제한자 사용 --> new 사용이 불가

	public static D3_SingletonTest getInstance() {
		if (st == null) {
			st = new D3_SingletonTest();
		}
		return st;
	}
}
