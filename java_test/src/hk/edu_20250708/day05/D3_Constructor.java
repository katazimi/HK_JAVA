package hk.edu_20250708.day05;

public class D3_Constructor {

	//티비 객체
	private int size = 0; //중요 데이터 -> private
	public String color = "검정색"; //색상

	//default 생성자: 단독으로 사용한다면 생략 가능 -> 오버로딩 사용시에는 반드시 정의해야함
	//생성자를 호출 할 때는 반드시 첫줄에 작성해야함
	//but super()와 this() 둘다 생성자를 호출하기 때문에 동시에 사용은 불가
	public D3_Constructor() {
		this(60); //반복 코드를 줄이기 위해 this() 사용하기도 함
		this.size = 60;
	}

	public D3_Constructor(int size) {
		super();
		this.size = size;
	}

	public D3_Constructor(int size, String color) {
		this.size = 60;
		this.color = color;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int pw) {

		this.size = size;
	}
}
