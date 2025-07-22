package hk.edu_20250722.day014;

public class D2_EvenMagicSquare_v2 extends D2_MagicSquare{

	public D2_EvenMagicSquare_v2() {
		super(4);
	}

	public D2_EvenMagicSquare_v2(int n) {
		super(n);
	}
	
	@Override
	public void make() {
		makeA();
		makeB();
	}

	//배열에 순서대로 입력하는 메서드
	public void makeA() {
		//2차원배열 <-- 값을 저장, 2차원 배열 <-- 1차원배열
		//방법1
//		int count=1;
//		for (int i=0; i<magic.length; i++) {
//			for (int j=0; j<magic.length; j++)
//				magic[i][j] = count++;
//		}

		//방법2
		//[i/col][i%col]
		int n = magic.length;
		for (int i=0; i<n*n; i++) {
			magic[i/n][i%n] = i+1;
		}
	}

	public void makeB() {
		int n = magic.length;

		//16~1까지의 숫자를 넣어줌
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i>=0 && i <n/4) || (i>=n/4*3 && i<n)) {
					if (j>=n/4 && j<n/4*3) {
						magic[i][j]= (n*n)-(i*n+j);
					}
				}
				else {
					if ((j>=0 && j<n/4) || (j>=n/4*3 && j<n)) {
						magic[i][j]= (n*n)-(i*n+j);
					}
				}
			}
		}
	}
}
