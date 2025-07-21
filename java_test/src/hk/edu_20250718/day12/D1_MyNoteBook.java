package hk.edu_20250718.day12;

public class D1_MyNoteBook extends D1_NoteBook{

	//하위에서 모두 구현을 하게 되면 상위의 클래스들의 기능을 모두 사용할 수 있음
	@Override
	public void typing() {
		System.out.println("MyNoteBook Typing 기능입니다.");
	}

}