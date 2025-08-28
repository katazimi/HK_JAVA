package hk.edu_20250718.day12;

public class D1_DeskTop extends D1_Computer{

	//오버라이딩
	@Override
	public void display() {
		System.out.println("DeskTop용 Display입니다.");

	}

	@Override
	public void typing() {
		System.out.println("DeskTop용 Typing입니다.");

	}

}