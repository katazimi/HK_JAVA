package hk.edu_20250729.day019;

public class D1_CakeMain {
	
	public static void main(String[] args) {
		//스레드들이 공유할 객체 생성
		D1_CakePlate cake = new D1_CakePlate();
		
		//스레드 객체
		D1_CakeEater eater = new D1_CakeEater(cake);
		D1_CakeMaker maker = new D1_CakeMaker(cake);
		
		Thread t1 = new Thread(eater);
		Thread t2 = new Thread(maker);
		
		t2.setPriority(10); //스레드의 우선순위 설정
		t1.setPriority(1);
		
		t1.start();
		t2.start();
	}
}
