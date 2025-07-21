package hk.edu_20250708.day05;

public class D1_ClassTest {
	//멤버필드: 클래스에서 데이터를 저장해서 사용하는 변수개념
	public int number;	//인스턴스 변수
	public static int staticNumber;	//클래스 변수

	//기본생성자(Default 생성자): 파라미터 없음, 생략가능, 클래스의 초기화 작업
	//객체를 생성할때 한번만 실행
	public D1_ClassTest() {
		//super: 부모, this: 자기자신
		super(); //부모의 디폴트 생성자를 의미
		this.number = 5;	//this:자기자신(현재클래스)을 가리킴
		System.out.println("default 생성자 실행");
	}

	//생성자 오버로딩
	//오버로딩이란? --> 파라미터를 다르게 하여 같은 이름을 사용할 수 있게됨
	public D1_ClassTest(int n) {

	}

	public D1_ClassTest(int n, int m) {

	}

	//메서드: 인스턴스 메서드
	public void methodTest() {
		System.out.println("클래스 관련 기능을 정의단다.");
	}

	//클래스 메서드
	public static void staticMethodTest() {
		System.out.println("static(메서드영역메모리)에 생성되어 공통기능 정의");
	}

	@Override
	public String toString() {
		return "D1_ClassTest [number=" + number + "]";
	}





}
