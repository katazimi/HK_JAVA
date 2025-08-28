package hk.edu_20250707.day04;

public class D4_GreatestCommonDivisor {

	public static void main(String[] args) {
		D4_GreatestCommonDivisor gcd = new D4_GreatestCommonDivisor();
		System.out.println("두수의 최대공약수 : " + gcd.way1(10, 20));	//최대공약수 방법1
		System.out.println("두수의 최대공약수 : " + gcd.way2(10, 20));	//최대공약수 방법2
		System.out.println("두수의 최소공배수 : " + gcd.leastCommonMultiple(10, 20, gcd.way2(10, 20)));	//최소공배수
		gcd.amicablePair(1, 4000); //친화수 구하기
		gcd.perfectNum(1,1000);
	}

	//문제2. 최대 공약수 구하기
	//방법1
	public int way1(int a, int b) {
		int greatestCommonDivisor = 1;
		int i=2;
		while (i<=Math.min(a, b)) {
			if (a%i==0 && b%i==0) {
				greatestCommonDivisor *= i;
				a/=i;
				b/=i;
				i=2;
			} else {
				i++;
			}
		}
		return greatestCommonDivisor;

	}

	//방법2
	public int way2(int a, int b) {
		while (true) {
			if (a > b) {
				a -= b;
			} else if (a < b) {
				b -= a;
			} else if (a == b) {
				return a;
			}
		}
	}

	//문제3. 최소공배수
	public int leastCommonMultiple(int a, int b, int c) {
		return a * b / c;
	}

	//문제4. 친화수 구하기
	public void amicablePair(int s, int e) {
		for (int i=s; i<e+1; i++) {
			if (aliquoSum(aliquoSum(i))==i && i!=aliquoSum(i)) {
				System.out.println(i+"의 친화수 : "+ aliquoSum(i));
			}
		}
	}

	public static int aliquoSum(int a) {	//진약수 구하는 메서드
		int sum = 0;

		for (int i=1; i<a; i++) {
			if (a%i == 0) {
				sum+= i;
			}
		}

		return sum;
	}

	//문제5. 완전수 구하기(자기자신을 제외한 약수들의 합이 자신과 같음)
	public void perfectNum(int a, int b) {
		for (int i=a; i<b+1; i++) {
			if (i == aliquoSum(i)) {
				System.out.println(i+"은(는) 완전수입니다.");
			}
		}
	}


}
