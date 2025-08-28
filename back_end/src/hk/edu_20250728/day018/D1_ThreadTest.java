package hk.edu_20250728.day018;

public class D1_ThreadTest {
	
	public static void main(String[] args) {
		//"안", "녕"을 번걸아 가며 출력하기
		
		for (int i=0; i<5; i++) {
			System.out.println("안");
		}
		for (int i=0; i<5; i++) {
			System.out.println("녕");
		}
		
		System.out.println("===============");
		
		//작업단위 하나
		Thread t1 = new Thread() { //익명클래스 작성 방식
			@Override
			public void run() {
				for (int i=0; i<5; i++) {
					try {
						System.out.println("안");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread() { //익명클래스 작성 방식
			@Override
			public void run() {
				for (int i=0; i<5; i++) {
					try {
						System.out.println("녕");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		t1.start();
		t2.start();
		
		Thread t3 = new D1_ThreadTest().new ThreadObj();
		t3.start();
		
	}
	//내부클래스
	class ThreadObj extends Thread {
		@Override
		public void run() {
			for (int i=0; i<5; i++) 
				System.out.println("ThreadTest: " + i);
		}
	}
}
