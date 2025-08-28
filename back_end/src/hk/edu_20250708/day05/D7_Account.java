package hk.edu_20250708.day05;

public class D7_Account {
	private int balance;
	final static int MIN_BALANCE = 0;
	final static int MAX_BALANCE = 1000000;

	public D7_Account() {
		balance = 0;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalnce(int balance) {
		if (balance>=MIN_BALANCE && balance <= MAX_BALANCE) {
			this.balance = balance;
		}
	}

	public static void main(String[] args) {
		D7_Account account = new D7_Account();

		account.setBalnce(10000);
		System.out.println("현재 잔고: " + account.balance);

		account.setBalnce(-100);
		System.out.println("현재 잔고: " + account.balance);

		account.setBalnce(2000000);
		System.out.println("현재 잔고: " + account.balance);

		account.setBalnce(300000);
		System.out.println("현재 잔고: " + account.balance);
	}
}
