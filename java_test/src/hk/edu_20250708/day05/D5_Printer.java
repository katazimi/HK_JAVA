package hk.edu_20250708.day05;

public class D5_Printer {

	public static void println() {

	}

	public static void println(int i) {
		System.out.println(i);
	}

	public static void println(boolean bool) {
		System.out.println(bool);
	}

	public static void println(double d) {
		System.out.println(d);
	}

	public static void println(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D5_Printer printer = new D5_Printer();
//		printer.println(10);
//		printer.println(true);
//		printer.println(5.7);
//		printer.println("홍길동");
		D5_Printer.println(10);
		D5_Printer.println(true);
		D5_Printer.println(5.7);
		D5_Printer.println("홍길동");

	}
}
