package hk.edu_20250707.day04;

public class D2_MethodTest {
	//main 메서드는 코드를 간결하게 작성
	//구현된 메서드를 실행시켜주는 메서드
	public static void main(String[] args) {
		D2_MethodTest.test01();	//클래스명.메서드명()

		D2_MethodTest mt = new D2_MethodTest();
		mt.test02();
	}

	//메서드 유형
	//1. static과 non-static 유형
	public static void test01() {
		System.out.println("static 메서드");
		//test02(); -> ststic에서는 non-static 사용이 불가
		D2_MethodTest mt = new D2_MethodTest();
		mt.test02();	//객체 생성 후 사용가능
	}

	public void test02() {
		System.out.println("non-static 메서드");

	}

	//2. 반환타입 유무
	public int test03() {
		int i=0;
		//주요 코드 작성
		return i;	//반환타입을 선언했다면 반드시 return을 정의해야함.
	}

	public void test04() {
		//return 9;	반환타입이 없다면 반환하면 안됨
	}

	//3. 파라미터 유무 : 외부로부터 값을 전달받으려고
	public static int test05(int a, int b) {
		int result = 0;
		if (a>b) {
			result = a;
		}
		return result;
	}


}
