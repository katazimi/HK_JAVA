package hk.edu_20250708.day05;

import java.util.Scanner;

public class D7_BankApplication {

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		Scanner sc = new Scanner(System.in);
		D7_Account_v2 account = new D7_Account_v2();
		int choice;

		while (true) {
			System.out.println("------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("------------------------------------------");
			System.out.print("선택> ");
			choice = Integer.parseInt(sc.nextLine());

			if (choice == 1) {
				System.out.println("----------");
				System.out.println("계좌생성");
				System.out.println("----------");

				System.out.print("계좌번호: ");
				String accountNumber = sc.nextLine();


				System.out.print("계좌주: ");
				String Name = sc.nextLine();


				System.out.print("초기입금액: ");
				String beginningBalance = sc.nextLine();
				sc.nextLine();


				account.creatAccount(accountNumber, Name, beginningBalance);
			}
			else if (choice == 2) {
				account.accountList();
			}
			else if (choice == 3) {
				System.out.println("----------");
				System.out.println("예금");
				System.out.println("----------");
				System.out.print("계좌번호: ");
				String accountNumber = sc.nextLine();
				System.out.print("예금액: ");
				int money = Integer.parseInt(sc.nextLine());
				account.deposit(accountNumber, money);
			}
			else if (choice == 4) {
				System.out.println("----------");
				System.out.println("출금");
				System.out.println("----------");
				System.out.print("계좌번호: ");
				String accountNumber = sc.nextLine();
				System.out.print("출금액: ");
				int money = Integer.parseInt(sc.nextLine());
				account.withdrawal(accountNumber, money);
			}
			else if (choice == 5) {
				System.out.println("프로그램 종료");
				break;
			}
		}
	}

}
