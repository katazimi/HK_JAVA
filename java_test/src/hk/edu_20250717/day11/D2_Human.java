package hk.edu_20250717.day11;

public class D2_Human extends D2_Animal{

	@Override
	public void move() {
		System.out.println("사람은 두발로 걷습니다.");
	}

	public void eat() {
		System.out.println("다 잘먹어요");
	}
}
