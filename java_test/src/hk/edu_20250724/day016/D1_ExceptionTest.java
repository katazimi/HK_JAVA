package hk.edu_20250724.day016;

public class D1_ExceptionTest {
	
	public static void main(String[] args) {
//		exTest1("오");
		exTest2("일");
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
}
