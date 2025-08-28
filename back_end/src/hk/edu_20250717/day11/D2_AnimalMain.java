 package hk.edu_20250717.day11;

public class D2_AnimalMain {

	public static void main(String[] args) {
		D2_Animal human = new D2_Human();
		human.move();
//		human.eat(); //부모타입으로 생성하여 자식타입의 메서드는 사용이 불가함
		D2_Human humanEat = (D2_Human)human;
		humanEat.eat();
		System.out.println("------------------");

		//다형성 발생원리 3가지
		//1. 부모의 타입으로 자식을 생성한다.
		D2_Animal AnimalH=new D2_Human();
		D2_Animal AnimalT=new D2_Tiger();
		D2_Animal AnimalE=new D2_Eagle();

		//2. 부모의 타입으로 자식을 참조한다.
		D2_Tiger tiger = new D2_Tiger();
		D2_Animal animalTT = tiger;

		moveAnimal(tiger);
		moveAnimal(new D2_Eagle());
		//3.
	}

	//자식타입의 서로 다른 여러 객체들을 참조할 수 있다.(파라미터에 부모타입으로 했을때)
	public static void moveAnimal(D2_Animal animal) {
		//메서드 오버라이딩 : 부모의 메서드를 재정의
		animal.move(); //부모타입으로 여러 형태를 나타낼수 있음 -> 다형성!!
	}

	//다형성을 활용하지 않으면 아래와 같이 전부 오버로딩해야함
	public static void moveAnimal(D2_Human human) {
		human.move();
	}
	public static void moveAnimal(D2_Tiger tiger) {
		tiger.move();
	}
	public static void moveAnimal(D2_Eagle eagle) {
		eagle.move();
	}

	public static void moveAnimal(Object obj) {
		if (obj instanceof D2_Human) {
			D2_Human human = (D2_Human) obj;
			human.move();
			human.eat();
		}
		else if (obj instanceof D2_Tiger) {
			D2_Tiger tiger = (D2_Tiger) obj;
			tiger.move();
			tiger.eat();
		}
	}
}
