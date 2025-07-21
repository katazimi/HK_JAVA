package hk.edu_20250717.day11;

public class D1_VIPCustomer extends D1_Customer{

	//부모의 멤버필드를 이용하면 되기 때문에 새로 작성할 필요가 없음!
//	private int customerID; //고객ID
//	private String customerName; //고객이름
//	private String customerGrade; //고객등급
//	private int bonusPoint; //보너스포인트
//	private double bonusRatio; //포인트 적립비율

	private int agentID; //담당 상담원 ID
	private double saleRatio; //할인율

	public D1_VIPCustomer() {
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
	}

	public D1_VIPCustomer(int customerID, String customerName, int agentID) {
		//부모의 생성자를 통해서 ID, Name 멤버필드 초기화
		super(customerID, customerName);

		//부모의 멤버필드에 접근해서 초기화
		super.customerID = customerID;
		super.customerName = customerName;
		super.customerGrade = "VIP";
		super.bonusRatio = 0.05;
		this.saleRatio = 0.1;
		this.agentID = agentID;
	}

	//부모의 calcPrice()와 자식의 calcPrice는 내용이 다르게 구현되어야 하기때문에
	//부모의 메서드를 바로 쓸수 없고, 새로 정의해야함
	@Override
	public int calcPrice(int price) {
		bonusPoint += price  * bonusRatio;
		return price - (int)(price*saleRatio);
	}

	@Override
	public String toString() {
		return customerName + "님의 등급은 " + customerGrade +
					"이며, 보너스 포인트는 " + bonusPoint + "입니다.";
	}




}
