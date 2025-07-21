package hk.edu_20250709.day06;

public class D4_CalculatorCompare {
	//은닉화(캡슐화)
	private int result;//연산결과

	public void calculator(int num1, int num2, String cal) {
		//분기형태로 실행
		//전달 받는 파라미터 중 타입이 String.
		//-->문자열 비교하려면 equals() 사용

		if (cal.equals("+")) {
			D4_CalculatorA calA = new D4_CalculatorA(num1, num2);
			calA.a();
			result = calA.getResult();
		}
		else if (cal.equals("-")) {
			D4_CalculatorB calB = new D4_CalculatorB(num1, num2);
			calB.a();
			result = calB.getResult();
		}
		else if (cal.equals("*")) {
			D4_CalculatorC calC = new D4_CalculatorC(num1, num2);
			calC.a();
			result = calC.getResult();
		}
		else if (cal.equals("/")) {
			D4_CalculatorD calD = new D4_CalculatorD(num1, num2);
			calD.a();
			result = calD.getResult();
		}
		else {
			System.out.println("입력된 연산자는 지원하지않습니다.");
		}
	}

	public int getResult() {
		return result;
	}

	public static void main(String[] args) {
		D4_CalculatorCompare calcu =new D4_CalculatorCompare();
		int num1 = 50;
		int num2 = 50;
		String cal = "+";

		calcu.calculator(num1, num2, cal);
		System.out.printf("%d %s %d = %s",num1,cal,num2,calcu.getResult());
	}
}
