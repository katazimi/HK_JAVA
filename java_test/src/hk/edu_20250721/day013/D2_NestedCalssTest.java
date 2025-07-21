package hk.edu_20250721.day013;

public class D2_NestedCalssTest {

	public int a=5;
	public int b=10;

	public static int aa=7;
	public static int bb=3;

	public static void main(String[] args) {
		//일반 클래스처럼 객체 생성해서 사용한다.
		StaticInnerClass sIc = new StaticInnerClass("정적내부클래스");
		System.out.println(sIc.getResult());

		InstanceInnerClass iIc = new D2_NestedCalssTest().new InstanceInnerClass("인스턴스내부클래스");
		System.out.println(iIc.getResult());

		D2_NestedCalssTest nCt = new D2_NestedCalssTest();
		nCt.nestMethod();
	}

	//정적 내부 클래스: 독립적으로 사용 가능
	static class StaticInnerClass {
		public String msg;

		public StaticInnerClass(String msg) {
			this.msg= msg;
		}

		public int getResult() {
//			int result = a+b; //static 필드만 접근이 가능함
			int result = aa+bb;
			return result;
		}
	}

	//인스턴스 내부 클래스: 외부 클래스를 객체 생성해야 사용이 가능
	class InstanceInnerClass {
		public String msg;

		public InstanceInnerClass(String msg) {
			this.msg = msg;
		}

		public int getResult() {
			int result = a+b;
			return result;
		}
	}

	public void nestMethod() {
		int c=5;
		class LocalInnerClass {
			public int number;

			public LocalInnerClass(int number) {
				this.number = number;
			}

			public int getLic() {
				int ss=c; //메서드의 변수에 접근
				return ss+number;
			}
		}//지역 내부 클래스 종료
//		c=50; //값이 변경되는 코드가 있다면 오류가 발생
		LocalInnerClass licObj = new LocalInnerClass(100);
		System.out.println("지역내부클래스:" + licObj.getLic());

		//익명 클래스: 인터페이스로 구현할때 그 위치에서 메서드등을 구현
		D2_Anonymous anonymous = new D2_Anonymous() {

			@Override
			public void anonyMethod2() {
				// TODO Auto-generated method stub

			}

			@Override
			public void anonyMethod1() {
				// TODO Auto-generated method stub

			}
		};

		anonymous.anonyMethod1();
	}//메서드 종료
}
