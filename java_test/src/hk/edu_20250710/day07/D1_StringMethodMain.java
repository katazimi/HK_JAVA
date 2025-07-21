package hk.edu_20250710.day07;

public class D1_StringMethodMain {

	public static void main(String[] args) {
		D1_StringMethodTest smt = new D1_StringMethodTest();
		String s = smt.sTest01("ABCDEF", 2);
		System.out.println(s);

		smt.sTest02("ABCDEF");

		System.out.println(smt.sTest03("ABCD"));
		smt.sTest04();
		smt.sTest05("안녕하세요");
		System.out.println("--------------------------");
		smt.search("카카오");

	}

}
