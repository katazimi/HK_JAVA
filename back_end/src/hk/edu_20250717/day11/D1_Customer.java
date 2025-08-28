package hk.edu_20250717.day11;

public class D1_Customer {

	//protected 접근제한자 : 상속관계일 경우 public, 아닌 경우 default
	protected int customerID; //고객ID
	protected String customerName; //고객이름
	protected String customerGrade; //고객등급
	protected int bonusPoint; //보너스포인트
	protected double bonusRatio; //포인트 적립비율

	//default 생성자 : ID, Name 값을 나중에 추가해야함
	public D1_Customer() {
		customerGrade = "SILVER";
		bonusRatio = 0.01;
	}

	//ID / Name 바로추가 가능
	public D1_Customer(int customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
	}

	//Bonus 적립 계산후 추가
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		return price;
	}

	//로그 확인 가능
//	@Override
//	public String toString() {
//		return "D1_Custuomer [customerID=" + customerID + ", customerName=" + customerName + ", customerGrade="
//				+ customerGrade + ", bonusPoint=" + bonusPoint + ", bonusRatio=" + bonusRatio + "]";
//	}

	@Override
	public String toString() {
		return customerName + "님의 등급은 " + customerGrade +
					"이며, 보너스 포인트는 " + bonusPoint + "입니다.";
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public double getBonusRatio() {
		return bonusRatio;
	}

	public void setBonusRatio(double bonusRatio) {
		this.bonusRatio = bonusRatio;
	}


}
