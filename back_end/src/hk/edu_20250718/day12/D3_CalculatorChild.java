package hk.edu_20250718.day12;

public class D3_CalculatorChild implements D3_ICalc {

	@Override
	public int add(int num1, int num2) {
		return num1-num2;
	}

	@Override
	public int minus(int num1, int num2) {

		return num1-num2;
	}

	@Override
	public int multiplication(int num1, int num2) {

		return num1*num2;
	}

	@Override
	public int divide(int num1, int num2) {

		return num1/num2;
	}

}
