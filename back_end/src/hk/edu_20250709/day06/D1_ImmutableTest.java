package hk.edu_20250709.day06;

public class D1_ImmutableTest {

	public static void main(String[] args) {
		int a = 5;
		change01(a);//int a=5 --> 메서드 파라미터 int a=a
		System.out.println("원본 a변수의 값: " + a);//원본이 변경되지 않음

		D1_ImmutableTest imTest = new D1_ImmutableTest();
		change02(imTest);
		System.out.println("원본 imTest의 값: "+imTest.bb);
	}

	public int bb=5;

	public static void change01(int a) {
		a=10;//파라미터로 받은 값을 10으로 변경
	}

	public static void change02(D1_ImmutableTest imTest) {
		imTest.bb=10;
	}
}