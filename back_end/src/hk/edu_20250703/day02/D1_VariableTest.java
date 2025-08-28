package hk.edu_20250703.day02;

public class D1_VariableTest {

	public static void main(String[] args) {

		//기본타입의 특징
		//1.정수타입
		//  기본형은 int
		byte b=1;// -128 ~ 127 숫자를 표현
//		     b=128;//128은 표현범위를 벗어남
		short sh = 128;//2byte의 크기
		int i = 500000000;//int 4byte의 크기
		long l = 5000000000L;// 리터럴 값에 L이라고 붙여주면 long타입으로 정의됨

		//2.실수타입
		//  기본형은 double
		double d = 15.8;
		float f = (float)10.2;
		float ff = 10.2f;
		float fff=(float)(d+f);// double+float -> double + double =double

		//3.다른 타입끼리 연산
		int ii=(int)(i+d);//double+double---> int형변환
		double dd=i+d;// 500000000.222
		int iii=(int)dd;// 500000000 실수-->정수로변환하면 소수점 제거됨

		//4.정수끼리 연산
		byte b1=10;
		byte b2=20;
		byte b3=(byte)(b1+b2);//byte끼리 연산해도 int형으로 반환

		String grade = "B";

		int score1 = 0;
		switch (grade) {
		case "A" -> {score1 = 100;}
		case "B" -> {int result = 100-20; score1 = result;}
		default -> {score1 = 60;}
		}
		System.out.println(score1);

		/*int sum=0;
		for (int k=1; k<101; i++) {
			if (k%3==0)
				sum+=k;
		}
		System.out.println(sum);*/

		for (int x=1; x<11; x++) {
			for (int y=1; y<11; y++) {
				if (4*x + 5*y == 60) {
					System.out.printf("(%d,%d)\t",x,y);
				}
			}
		}
		System.out.println("종");

		for (int x=0; x<5; x++) {
			for (int y=0; y<x+1; y++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
