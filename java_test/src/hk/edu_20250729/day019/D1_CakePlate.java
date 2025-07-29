package hk.edu_20250729.day019;

public class D1_CakePlate {
	
	private int breadCount;

	public synchronized void eatBread() {
		if (breadCount < 1) {
			System.out.println("빵이 모자르므로 기다려야함");
			try {
				wait(); //일시정지
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		breadCount--;
		System.out.println("빵을 1개 먹음. 총 " + breadCount + "개 남음");
		this.notifyAll(); //모든 스레드를 실행대기
	}

	public synchronized void makeBread() {
		if (breadCount>=30) {
			System.out.println("빵이 남아요~");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		breadCount++;
		System.out.println("빵을 1개 더 만듦 총 : " + breadCount + "개");
		this.notify();
	}
	
}
