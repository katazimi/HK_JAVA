package hk.edu_20250716.day10;

public class D1_ChildMain {

	public static void main(String[] args) {
		D1_Child child = new D1_Child();

		int a = child.a; //부모의 멤버필드 a
		child.childMethod(); //자식메서드 사용
		child.parentMethod(); //부모의 메서드 사용
		System.out.println();

		D1_Parent parent = new D1_Child();
//		D1_Parent pp = new D3_Lotto(); 클래스간에 아무런 관계가 없음

		int aa = parent.a;
		parent.parentMethod(); //부모의 메서드를 호출하면 자식이 호출됨
//		parent.childMethod(); //(X) 설계도에 공개된 메서드만 사용가능
		System.out.println();

		//test(new D1_Child())
		//test(new D1_child2())
		test(new D1_Child());

		System.out.println(child.toString());
	}

	//자바의 다형성
	public static void test(D1_Parent obj) {
		obj.parentMethod();	//obj에 어떤 자식객체를 받느냐에 따라
							//parentMethod()가 다르게 실행된다(다형성)

		//자식객체에 정의된 childMethod()를 사용하려면 형변환을 해야함
		if (obj instanceof D1_Child) {
			D1_Child ch = (D1_Child)obj; //자식타입으로 형변환
			ch.childMethod();			 //자식 메서드 사용가능
										 //설계도에 공개된 메서드만 사용 가능
		}
	}
}
