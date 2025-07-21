package hk.edu_20250718.day12;

public class D2_InterfaceChild implements D2_InterfaceTest{

	@Override
	public int test1() {

		return 1;
	}

	@Override
	public int test2() {

		return 2;
	}

	@Override
	public int test3() {

		return 3;
	}

	public int test10() {
		System.out.println("새로 추가된 기능");
		return 4;
	}

}