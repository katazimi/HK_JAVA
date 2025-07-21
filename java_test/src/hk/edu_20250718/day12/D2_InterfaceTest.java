package hk.edu_20250718.day12;

public interface D2_InterfaceTest {

	//멤버필드 선언
	public int B=50;//일반적인 필드를 선언해도 자동으로 상수가 됨
	public static final int A=20;

	//추상메서드
	public int test1();
	public abstract int test2();
	public int test3();

	//default메서드
	public default void test4() {
		test5();//내부에서 접근이 가능함
		System.out.println("인터페이스에서 기능을 구현한 메서드");
	}
	//private메서드: 현재 interface를 구현한 객체에서 사용을 못한다.
	//              interface 내부에서만 사용이 가능한 메서드
	private void test5() {
		System.out.println("인터페이스 내부에서만 사용가능");
	}

	//static 메서드: 독립적으로 기능을 제공할때
	//             D2_InterfaceTest.test6() 호출 가능함
	public static void test6() {
		System.out.println("인터페이스만으로 실행 가능");
	}
}