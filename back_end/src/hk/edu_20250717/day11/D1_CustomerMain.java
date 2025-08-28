package hk.edu_20250717.day11;

public class D1_CustomerMain {

	public static void main(String[] args) {
		//부모 타입 -> 부모 타입
		D1_Customer customerLee = new D1_Customer();
		customerLee.customerID = 10001;
		customerLee.customerName = "이순신";
		customerLee.bonusPoint = 1000;
		int price = customerLee.calcPrice(15000);
		System.out.println(customerLee);

		//자식 타입 -> 자식 타입
		D1_VIPCustomer customerKim = new D1_VIPCustomer();
		customerKim.customerID = 10002;
		customerKim.customerName = "김유신";
		customerKim.bonusPoint = 10000;
		int price2 = customerKim.calcPrice(15000);
		System.out.println(customerKim);

		D1_Customer customerLee2 = new D1_Customer(10001,"이순신");
		customerLee.customerID = 10001;
		int price3 = customerLee2.calcPrice(15000);
		System.out.println(customerLee.toString() + price);

		D1_VIPCustomer customerKim2 = new D1_VIPCustomer(10002,"김유신",20001);
		customerKim.customerID = 10002;
		int price4 = customerKim2.calcPrice(15000);
		System.out.println(customerKim.toString() + price2);
	}
}
