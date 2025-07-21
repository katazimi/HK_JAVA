package hk.edu_20250718.day12;

public class D3_CalculatorMain {

	public static void main(String[] args) {
		D3_ICalc calc = new D3_CalculatorChild();

		System.out.println(calc.add(10, 20));
		System.out.println(calc.divide(20,10));

	}

}
