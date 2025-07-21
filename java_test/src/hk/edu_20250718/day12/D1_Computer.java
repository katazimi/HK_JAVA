package hk.edu_20250718.day12;

public abstract class D1_Computer {

	//모든 종류의 컴퓨터가 가진 기본 기능
	//전원 켜기/ 끄기
	public void turnOn() {
		System.out.println("전원을 켭니다.");
	}

	public void turnOff() {
		System.out.println("전원을 끕니다.");
	}

	//display 기능
	public abstract void display();
	//typing 기능
	public abstract void typing();
}