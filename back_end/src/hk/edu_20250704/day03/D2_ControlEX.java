package hk.edu_20250704.day03;

import java.util.Random;
import java.util.Scanner;

public class D2_ControlEX {

	public static void main(String[] args) {
		//구구단 출력하기

		//2단 출력하는 코드
		for (int i=1; i<10; i++) {
			//System.out.println("2 x "+i+" = "+2*i);
			System.out.printf("%d x %d = %d\n",2,i,2*i);
		}

		//2~9단 출력
		for (int i=2; i<10; i++) {
			System.out.println("----------");
			for (int j=1; j<10; j++) {
				System.out.printf("%d x %d = %d\n",i,j,i*j);
			}
			System.out.println("----------");
		}

		//2~9단 출력할때 짝수 단만 출력
		System.out.println("짝수단만 출력");
		for (int i=2; i<10; i++) {
			if (i%2==0 ) {
				System.out.println("----------");
				for (int j=1; j<10; j++) {
					System.out.printf("%d x %d = %d\n",i,j,i*j);
				}
				System.out.println("----------");
			}
		}
		//2~9단 출력할때 홀수 단만 출력
		System.out.println("홀수단만 출력");
		for (int i=2; i<10; i++) {
			if (i%2!=0) {
				System.out.println("----------");
				for (int j=1; j<10; j++) {
					System.out.printf("%d x %d = %d\n",i,j,i*j);
				}
				System.out.println("----------");
			}
		}

		//1~100까지의 총합
		int sum1=0;
		for (int i=1; i<101; i++) {
			sum1+=i;
		}
		System.out.printf("1부터 100까지의 합 : %d\n",sum1);

		//1~100 중에 4의 배수의 총합을 출력
		int sum2=0;
		for (int i=1; i<101; i++) {
			if (i%4==0) {
				sum2+=i;
			}
		}
		System.out.printf("1부터 100중 4의 배수의 합 : %d\n",sum2);

		//주사위 두개의 합이 5이면 실행을 멈추고, 5가 아니라면 계속 실행되도록 함
		Random r = new Random();
		int dice1;
		int dice2;
		while(true) {
			dice1 = (int)(Math.random() * 6) + 1;
			dice2 = r.nextInt(6) + 1;
			System.out.printf("%d + %d = %d\n",dice1,dice2,dice1+dice2);

			if (dice1 + dice2 == 5) {
				break;
			}
		}

		//140p 7번문제
		Scanner sc = new Scanner(System.in);
		int balance = 0; //잔고
		int choice; //선택
		int deposit; //예금
		int withdraw; //출금

		while(true) {
			System.out.println("------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("------------------------------");
			System.out.print("선택> ");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				System.out.print("예금액> ");
				deposit = Integer.parseInt(sc.nextLine());
				balance += deposit;
				continue;
			case 2:
				System.out.print("출금액> ");
				withdraw = sc.nextInt();
				if (withdraw > balance) {
					System.out.println("잔액이 부족합니다.");
					continue;
				}
				balance -= withdraw;
				continue;
			case 3:
				System.out.println("잔고> " + balance);
				continue;
			case 4:
				System.out.println("프로그램 종료");
				break;
			}
			break;
		}

	}

}
