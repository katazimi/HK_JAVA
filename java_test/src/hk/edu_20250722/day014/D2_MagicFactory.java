package hk.edu_20250722.day014;

import java.util.Scanner;

public class D2_MagicFactory {
	
	//Singleton Pattern
	private static D2_MagicFactory magic;
	private D2_IMagic iMagic;
	private D2_MagicFactory() {
		
	}
	
	public static D2_MagicFactory getInstance() {
		if (magic == null)
			magic = new D2_MagicFactory();
		return  magic;
	}
	
	public D2_IMagic factory() {
		System.out.print("원하는 마방진을 입력하세요: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		if (num<3) {
			System.out.println("3이상의 숫자를 입력하세요.");
		}
		else if (num%2 == 1) {
			iMagic = new D2_OddMagicSquare(num);
		}
		else if (num%4 == 0) {
			iMagic = new D2_EvenMagicSquare_v2(num);
		}
		else if (num%4 == 2) {
			iMagic = new D2_SixMagicSquare2(num);
		}
		
		return iMagic;
	}
}
