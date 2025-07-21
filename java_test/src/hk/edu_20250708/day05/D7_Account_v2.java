package hk.edu_20250708.day05;

public class D7_Account_v2 {
	private static String[][] account = new String[100][4];
	private String accountNumber;
	private String name;
	private int beginningBalance;
	private static int numberOfAccount=0;

	public D7_Account_v2() {
		// TODO Auto-generated constructor stub
	}

	public void creatAccount(String accountNumber, String name, String beginningBalance) {
		account[numberOfAccount][0] = accountNumber;
		account[numberOfAccount][1] = name;
		account[numberOfAccount][2] = beginningBalance;
		account[numberOfAccount][3] = beginningBalance;
		numberOfAccount++;
	}

	public void accountList() {
		System.out.println("----------");
		System.out.println("계좌 목록");
		System.out.println("----------");
		for (int i=0; i<numberOfAccount; i++) {
			System.out.printf("%s\t%s\t%s\n",account[i][0],
					account[i][1], account[i][3]);
		}
	}

	public void deposit(String accountNumber, int money) {
		int index = D7_Account_v2.findAccount(accountNumber);

		if (index != -1) {
			account[index][3] = String.valueOf(Integer.parseInt(account[index][3])+money);
		}

	}

	public void	withdrawal(String accountNumber, int money) {
		int index = D7_Account_v2.findAccount(accountNumber);

		if (index != -1) {
			if (money > Integer.parseInt(account[index][3])) {
				System.out.println("계좌 잔액이 부족합니다.");
			}
			else {
				account[index][3] = String.valueOf(Integer.parseInt(account[index][3])-money);
				System.out.println("출금이 성공되었습니다.");
			}
		}
	}

	public static int findAccount(String accountNumber) {
		int index = -1;
		for (int i=0; i<numberOfAccount; i++) {
			if (accountNumber.equals(account[i][0])) {
				index = i;
			}
		}

		if (index == -1) {
			System.out.println("존재하지 않는 계좌입니다.");
		}

		return index;
	}

}
