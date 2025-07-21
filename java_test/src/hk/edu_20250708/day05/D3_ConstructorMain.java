package hk.edu_20250708.day05;

public class D3_ConstructorMain {

	public static void main(String[] args) {
		D3_Constructor tv = new D3_Constructor(70,"빨간색");
		tv.color = "파란색";
		System.out.println(tv.color+" : "+tv.getSize());

		D3_Constructor tv2 = new D3_Constructor();
		tv2.color = "노란색";
		System.out.println(tv2.color+" : "+tv2.getSize());

		D3_Constructor tv3 = new D3_Constructor(90);
		System.out.println(tv3.color+" : "+tv3.getSize());
	}

}
