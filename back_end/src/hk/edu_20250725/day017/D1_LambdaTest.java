package hk.edu_20250725.day017;

public class D1_LambdaTest {
	
	@FunctionalInterface
	public interface D1_IinnerLambda {
		public int add(int a);
	}
	
	public static void main(String[] args) {
		D1_ILambda lam = new D1_ILambda() {
			@Override
			public int add(int a, int b) {
				// TODO Auto-generated method stub
				return a+b;
			}
		};
		
		System.out.println(lam.add(5, 10));
		
		//람다식으로 구현: 코드를 간결하게 작성가능
		D1_ILambda lamFunc = (a,b)->{return a+b;};
		D1_ILambda lamFunc2 = (a,b)->a+b;
		D1_ILambda lamFunc3 = (a,b)->{System.out.println(a);return a+b;};
		
		D1_IinnerLambda innerLam = (a) -> {
			System.out.println(a);
			return a+10;
		};
		
	}
}
