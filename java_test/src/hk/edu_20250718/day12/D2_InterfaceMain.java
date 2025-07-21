package hk.edu_20250718.day12;

public class D2_InterfaceMain {

	public static void main(String[] args) {
		D2_InterfaceTest it=new D2_InterfaceChild();
		it.test2();

		//인라인 방식: 현재 위치에서 바로 사용할때 유용하며,
		//           재활용성은 없다.
		D2_InterfaceTest it2=new D2_InterfaceTest(){

			@Override
			public int test3() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int test2() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int test1() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
}