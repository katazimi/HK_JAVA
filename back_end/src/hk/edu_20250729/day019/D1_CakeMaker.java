package hk.edu_20250729.day019;

public class D1_CakeMaker implements Runnable{
	
	private D1_CakePlate cake;
	
	public D1_CakeMaker() {}
	
	public D1_CakeMaker(D1_CakePlate cake) {
		this.cake = cake;
	}
	
	@Override
	public void run() {
		for (int i=0; i<30; i++) {
			cake.makeBread();
		}
	}
}
