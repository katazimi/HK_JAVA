package hk.edu_20250710.day07;

import java.util.Scanner;

public class D2_CalculatorMain {

	public static void main(String[] args) {
		D2_Calculator cal = new D2_Calculator();
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("계산 값을 입력하세요(+,-*,/ 연산만 가능)입력은 \"5+10\"");
			//예: "5+10"입력값 받는 코드 작성
			String s = sc.nextLine();

			//입력받은 값 s의 패턴: "숫자[+-/*]숫자" 확인 ---> 정규화표현식
			if(check(s) != null) {
				cal.calcu(s);
			}else {
				//"9"를 입력하면 "계산 프로그램을 종료해요~~" 출력하고 끝내기 코드 작성
				System.out.println("계산식 입력을 다시 확인해주세요(종료하려면 9를 입력하세요).");
				String quit = sc.nextLine();
				if (quit.equals("9")) {
					System.out.println("계산 프로그램을 종료해요~~");
					break;
				}
			}
		}
	}

	//정규화 표현식 확인
	public static String check(String s) {
		String s2;
		if (s.indexOf("+") != -1 && s.indexOf("+") != 0 && s.indexOf("+") != s.length()-1) {
			s2 = "+";
			try {
				Integer.parseInt(s.substring(0, s.indexOf("+")));
				Integer.parseInt(s.substring(s.indexOf("+") + 1));
			} catch (Exception NumberFormatException) {
				return null;
			}
		}
		else if (s.indexOf("-") != -1 && s.indexOf("-") != 0 && s.indexOf("-") != s.length()-1) {
			s2 = "-";
			try {
				Integer.parseInt(s.substring(0, s.indexOf("-")));
				Integer.parseInt(s.substring(s.indexOf("-") + 1));
			} catch (Exception NumberFormatException) {
				return null;
			}
		}
		else if (s.indexOf("*") != -1 && s.indexOf("*") != 0 && s.indexOf("*") != s.length()-1) {
			s2 = "*";
			try {
				Integer.parseInt(s.substring(0, s.indexOf("*")));
				Integer.parseInt(s.substring(s.indexOf("*") + 1));
			} catch (Exception NumberFormatException) {
				return null;
			}
		}
		else if (s.indexOf("/") != -1 && s.indexOf("/") != 0 && s.indexOf("/") != s.length()-1) {
			s2 = "/";
			try {
				Integer.parseInt(s.substring(0, s.indexOf("/")));
				Integer.parseInt(s.substring(s.indexOf("/") + 1));
			} catch (Exception NumberFormatException) {
				return null;
			}
		} else {
			return null;
		}
		return s2;


	}
}
