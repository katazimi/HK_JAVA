package hk.edu_20250728.day018;

public class D2_RunnableTest implements Runnable{

	@Override
	public void run() {
		for (int i=0; i<5; i++) {
			System.out.println("나는 Runnable을 구현한 스레드야");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
