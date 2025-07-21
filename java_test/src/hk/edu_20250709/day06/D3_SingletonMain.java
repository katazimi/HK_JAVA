package hk.edu_20250709.day06;

public class D3_SingletonMain {

	public static void main(String[] args) {
		//참조타입끼리 형변환
		D3_SingletonMain sm = new D3_SingletonMain();
		Object obj = sm; //Object가 부모이므로 더 큰개념 -> 자동 형변환
//		obj.test();//형변환이 되어서 test() 사용이 불가능
		D3_SingletonMain afterSM = sm;
		afterSM.test(); //자신타입으로 다운캐스팅하면 자신의 메서드 사용이 가능함

		//기본타입과 참조타입 형변환
		int a = 10;
		Object obj2 = a; //참조타입 <-- 기본타입
		Integer ii = a;
//		Integer iii = new Integer(a);
		Object obj3 = ii; //참조타입끼리 형변환
		int b = (int)obj3;
		System.out.println(b);

//		----------------------------------------------------------------------------------

//		D3_SingletonTest st = new D3_SingletonTest();//실행못함
		//new를 못하면 객체를 어떻게 생성? 기능은 어떻게 사용?
		D3_SingletonTest st = D3_SingletonTest.getInstance();
	}

	public void test() {
		System.out.println("안녕하세요");
	}
}
