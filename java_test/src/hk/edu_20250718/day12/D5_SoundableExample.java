package hk.edu_20250718.day12;

public class D5_SoundableExample {

	public static void printSound(D5_Soundable soundable) {
		System.out.println(soundable.sound());
	}

	public static void main(String[] args) {
		printSound(new D5_Cat());
		printSound(new D5_Dog());
	}
}
