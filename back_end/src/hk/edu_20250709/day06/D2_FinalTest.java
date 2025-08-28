package hk.edu_20250709.day06;

import java.util.Arrays;

public class D2_FinalTest {

	//멤버필드에 상수 정의해서 사용
	public static final int NUM=100;

	//참조타입 상수 선언
	public static final int[] ARRAYNUM = {1,2,3,4,5};

	public final int NUM2;//값을 정의하지 않을경우 생성자에서 정의

	public D2_FinalTest(int num) {
//		NUM = num;
		NUM2 = num;
	}

	public static void main(String[] args) {
		int a = 5;
			a = 15; //값 변경이 가능 --> 변수

		//상수 선언
		final int B = 10;
//				  B = 30; 값을 변경할 수 없음 --> 상수

		//메서드에 파라미터를 통해 값을 변경할 수 있다.
		System.out.println(test01(10));
		System.out.println(test01(20));

		D2_FinalTest ft = new D2_FinalTest(50);
		D2_FinalTest ft2 = new D2_FinalTest(30);

		ARRAYNUM[0] =10;//참조타입의 경우 주소값 변경만 안되는 것이지 내용은 변경이 가능
		System.out.println(Arrays.toString(ARRAYNUM));

		int test[] = {1,2,3,4,5};
//		ARRAYNUM = test; //주소 변경은 불가
	}

	//메서드에서 선언: 권장하지 않음
	public static int test01(int val) {
		final int AA = val;
		return AA;
	}
}
