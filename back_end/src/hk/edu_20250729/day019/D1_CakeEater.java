package hk.edu_20250729.day019;

public class D1_CakeEater implements Runnable{

	private D1_CakePlate cake;
	
	public D1_CakeEater() {}
	
	public D1_CakeEater(D1_CakePlate cake) {
		this.cake = cake;
	}
	
	@Override
	public void run() {
		for (int i=0; i<30; i++) {
			cake.eatBread();
		}
		
	}

}
