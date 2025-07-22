package hk.edu_20250717.day11;

import hk.edu_20250721.day013.D1_EvenMagicSquare_v2;
import hk.edu_20250722.day014.D1_SixMagicSquare;

public class D3_MagicSquareMain {

	public static void main(String[] args) {
		D3_OddMagicSquare oddMagic=new D3_OddMagicSquare(3);
		oddMagic.make();
//		int [][] magic=oddMagic.magic;
//		for (int i = 0; i < magic.length; i++) {
//			for (int j = 0; j < magic.length; j++) {
//				System.out.print(magic[i][j]+"\t");				
//			}
//			System.out.println();
//		}
		oddMagic.magicPrint();
		
		System.out.println("=========짝수마방진(4배수)=============");
		D1_EvenMagicSquare_v2 evenMagic=new D1_EvenMagicSquare_v2(10);
		evenMagic.magicSquarePrint();
		evenMagic.sumPrint();
		
		System.out.println("=========짝수마방진(4의 배수를 제외한 짝수)=============");
		D1_SixMagicSquare sixMagic = new D1_SixMagicSquare(14);
		sixMagic.make();
	}
}
