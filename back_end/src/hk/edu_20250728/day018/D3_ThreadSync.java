package hk.edu_20250728.day018;

public class D3_ThreadSync {
	
	public static void main(String[] args) {
		//공유객체 생성
		ShareObject so = new D3_ThreadSync().new ShareObject();
		
		//A와 B 스레드가 동시적으로 접근..
		//동기화를 설정하기: A스레드의 작업이 종료되기 전까지는 B스레드는 대기
		//설정방법 2가지: synchronized 메서드(),
		//			   synchronized 블럭 -> synchronized(공유객체)
		Thread trA = new Thread() {
			@Override
			public void run() {
				synchronized (so) {
					so.print("공");
				}
			};
		};
		
		Thread trB = new Thread() {
			@Override
			public void run() {
				synchronized (so) {
					so.print("유");
				}
			};
		};
		
//		trA.start();
//		trB.start();
		
		//---------------------------------------------
		D3_ThreadSync d3 = new D3_ThreadSync();
		
		Thread trC = new Thread() {
			public void run() {
				d3.sbTest("한");
			};
		};
		
		Thread trD = new Thread() {
			public void run() {
				d3.sbTest("경");
			};
		};
		
		trC.start();
		trD.start();
	}
	
	//내부클래스
	class ShareObject {
//		public void print(String title) {
		public synchronized void print(String title) {
			for (int i=0; i<10; i++) {
				System.out.println(title);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//StringBuffer, Stringbuilder() 공유하는 스레드
	//StringBuffer는 자체 동기화 제공, 속도는 느림, 안정적
//	public static StringBuffer sb = new StringBuffer();
	//StringBuilder는 자체 동기화 제공X, 속도는 빠름, 안정적X
	public static StringBuilder sb = new StringBuilder();
	
	public void sbTest(String s) {
		for (int i = 0; i < 1000; i++) {
			sb.append(s);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sb.length());
	}
	
}
