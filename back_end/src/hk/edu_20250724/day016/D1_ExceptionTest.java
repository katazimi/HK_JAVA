package hk.edu_20250724.day016;

import java.io.IOException;
import java.io.InputStreamReader;

public class D1_ExceptionTest {
	
	public static void main(String[] args) {
//		exTest1("오");
//		exTest2("일");
		try {
			userExceptionTest(12);
		} catch (D1_UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//예외처리를 안한다면..
	public static void exTest1(String s) {
		int a = 0;
		
		try {
			a = Integer.parseInt(s); //<--예외 발생하면서 중단댐
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
		System.out.println(a);
	}
	
	public static void exTest2(String s) {
		int i=0;
		String ss="";
		int[] array = {1,2,3,4,5};
		
		try {
			ss=s.substring(0,2);
			i = Integer.parseInt(s);
			int a = array[5];
		} catch (NumberFormatException e) {
			System.out.println("숫자형태인지 확인하세요.");
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("문자열의 길이를 확인하세요.");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열의 길이를 확인하세요.");
			e.printStackTrace();
		} finally {
			System.out.println("반드시 실행되어야하는 코드");
		}
		
		System.out.println("오류가 발생해도 프로그램은 종료되지 않음");
	}
	
	public static void userExceptionTest(int a) throws D1_UserException {
		//a는 1~10까지의 숫자만 받을 수 있음
		if (!(a>0 && a<11)) { //1~10의 범위를 벗어나는 숫자를 받은 경우
			throw new D1_UserException("1부터 10까지의 숫자만 입력가능");
		}
	}
	
	//java.io에 구현되어 있는 클래스들은 무조건 예외처리를 해야함
	public void exTest3() throws IOException {
		InputStreamReader in = new InputStreamReader(null);
		in.read();
	}
	
	public void exTest4() {
		try {
			exTest3();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //IOException을 직접 처리 안하고 던졌기 때문에 반드시 처리해야함
	}
}
