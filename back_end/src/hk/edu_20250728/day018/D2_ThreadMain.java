package hk.edu_20250728.day018;

public class D2_ThreadMain {

	public static void main(String[] args) {
		//스레드를 생성하는 방법은 2가지가 있음
		
		//1. Runnable 구현
		Runnable runObj = new D2_RunnableTest();
		Thread tr = new Thread(runObj);
		tr.setPriority(10); //작업순위 1~10
		tr.start();
		
		//2. Thread 클래스 상속
		Thread thr = new D2_ThreadInheritance();
		thr.setPriority(Thread.MIN_PRIORITY); //작업순위
		thr.start();
		
		//메인스레드
		for (int i=0; i<5; i++) {
			System.out.println("나는 메인 스레드야");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
