package hk.edu_20250709.day06;

//뺄셈기능
public class D4_CalculatorB {
	public int num1;
	public int num2;
	private int result;
	
	public D4_CalculatorB() {
		this(10,5);
	}
	
	public D4_CalculatorB(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getResult() {
		return result;
	}
	
	public void a() {
		result = num1-num2;
	}
}
