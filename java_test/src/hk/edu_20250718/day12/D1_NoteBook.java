package hk.edu_20250718.day12;

public abstract class D1_NoteBook extends D1_Computer{

	@Override
	public void display() {
		System.out.println("NoteBook display기능입니다.");
	}

	//typing기능을 여기서 구현할 상황이 아니라서 추상메서드로 남겨둠..
	//--> 현재 클래스도 추상 클래스로 정의 해야함 상속강요
	@Override
	public abstract void typing();

}