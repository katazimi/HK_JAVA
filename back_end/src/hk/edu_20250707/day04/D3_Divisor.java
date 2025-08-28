package hk.edu_20250707.day04;

import java.util.Scanner;

//문제1. 약수를 구하는 프로그램을 구현하시오
public class D3_Divisor {

	//default 생성자 : 파라미터 없음 ---> (생략 가능)
	public D3_Divisor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("약수를 구하고 싶은 숫자를 입력: ");
			int num = sc.nextInt();
			D3_Divisor di = new D3_Divisor();
			di.divisor(num);
		}
	}

	public void divisor(int n) {
		for (int i=1; i<n+1; i++) {
			if (n%i==0) {
				System.out.print((i==n)?i:i+", "); //삼항연산자 활용
			}
		}
	}

}
