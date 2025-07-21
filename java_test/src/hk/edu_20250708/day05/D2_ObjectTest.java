package hk.edu_20250708.day05;

public class D2_ObjectTest {

	public static void main(String[] args) {
		String str = new String("Object");
		String str2 = "Object";
		System.out.println(str +" : " + str2);
		System.out.println(str.getClass());

		D2_ObjectTest ot = new D2_ObjectTest();
		System.out.println(ot.getClass());

		//toString() : 문자열 반환
		//target이 객체일 경우 "위치@hashcode" 반환
		System.out.println(ot.toString());

		//target이 기본타입일 경우 값을 문자열로 반환
		int a=10;
		//a.toString(); a는 참조타입이 아님.
		Integer i2 = 10;//Integer는 Wrapper 클래스
		System.out.println(i2.toString());

		//hashcode
		System.out.println(ot.hashCode());

		//equals(): 참조타입을 비교할때 사 --> 해시코드로 비교한다.
		System.out.println(ot.equals(i2));//일반 타입끼리 비교는 의미 없음

		String s = new String("a");
		String s2 = "a";
		System.out.println(s == s2);	//동등연산자는 주소 값을 비교
		System.out.println(s.equals(s2));	//equals의 경우는 hashcode를 비교

	}


}
