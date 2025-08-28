package hk.edu_20250709.day06;

public class D5_StringCompare {
	public static void main(String[] args) {
		//리터럴과 리터럴 비교
		String s1 = "java";
		String s2 = "java";

		System.out.println(s1 == s2);	//주소로 비교
		System.out.println(s1.equals(s2));	//해시코드로 비교

		//객체와 객체 비교
		String obj1 = new String("java");
		String obj2 = new String("java");

		System.out.println(obj1 == obj2);	//주소로 비교
		System.out.println(obj1.equals(obj2));	//해시코드로 비교

		//객체와 리터럴 비교
		String s3 = "java";
		String obj3 = new String("java");

		System.out.println(s3 == obj3);	//주소로 비교
		System.out.println(s3.equals(obj3));	//해시코드로 비교

		//String의 특징 : immutable한 성격 --> 값이 변경되지 않는 성질
		String s = "java"; //원본
		String ss = s; //복사본
		ss = "자바";
		System.out.println(s);

		s.replace("j", "o");
		s=s.replace("j", "o");
		System.out.println(s);

	}
}
