package hk.edu_20250709.day06;

//덧셈기능의 클래스
public class D4_CalculatorA {
	//계산할 값 2개를 저장할 멤버필드
	public int num1;
	public int num2;
	//계산결과를 저장할 멤버필드
	private int result;

	//default 생성자
	public D4_CalculatorA() {
//		num1 = 10;
//		num2 = 5;
		this(10,5);
	}

	//생성자 오버로딩
	public D4_CalculatorA(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
		result = 15;
	}

	public int getResult() {
		return result;
	}

	//기능 정의: 덧셈
	public void a() {
		this.result = this.num1 + this.num2;
	}
}

