package hk.edu_20250722.day014;

public class D2_MagicSquareMain {

	public static void main(String[] args) {
//		D2_IMagic magic = new D2_OddMagicSquare(5);
//		magic.make();
//		magic.magicPrint();
//		
//		D2_IMagic magic4 = new D2_EvenMagicSquare_v2(8);
//		magic4.make();
//		magic4.magicPrint();
//		
//		D2_IMagic magic6 = new D2_SixMagicSquare2(6);
//		magic6.make();
//		magic6.magicPrint();
		
		//singleton pattern 이라 메서드를 통해 객체를 얻어옴
		//다형성 구현: IMagic 타입으로 여러 객체를 참조하며, make(), print()를 통해 다양한 형태로 표현
//		D2_MagicFactory fac = D2_MagicFactory.getInstance();
//		D2_IMagic magic = fac.factory();
//		magic.make();
//		magic.magicPrint();
		
		D2_MagicFactory fac = D2_MagicFactory.getInstance();
		D2_IMagic magic = fac.factory();
		if (magic == null)
			System.out.println("다시 입력하세요");
		else
			D2_MagicUtil.magicRun(magic);
		
	}

}
