package hk.edu_20250714.day08;

//Cloneable 인터페이스
//--> 아무런 기능 명세가 되어 있지 않은 인터페이스이다.
//--> 표식 인터페이스로써 기능은 없고, 이런저런걸할 수 있다는 표시
public class D1_Person implements Cloneable{

	public String name;

	public D1_Person() {

	}

	public D1_Person(String name) {
		this.name = name;
	}

	//clone()은 Object에 구현되어 있는 메서드
	//Override란? 부모의 메서드를 자식이 재정의하는것
	@Override
	public D1_Person clone() {
		try {
			return (D1_Person) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
