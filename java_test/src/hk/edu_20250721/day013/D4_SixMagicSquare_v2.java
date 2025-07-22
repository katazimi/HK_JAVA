package hk.edu_20250721.day013;

import hk.edu_20250717.day11.D3_MagicSquare;

public class D4_SixMagicSquare_v2 {
	public int[][] magic;

	public D4_SixMagicSquare_v2() {
		this(10);
	}

	public D4_SixMagicSquare_v2(int n) {
		magic = new int[n][n];
	}

	public void make() {
		makeA();
		makeB();
		makeC();
		makeD();
		func1();
		func2();
		magicSquarePrint();
		sumPrint();
	}
	//A영역 채우기
	public void makeA() {
		int n = magic.length;
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/4; j++) {
				if (i == n/4) {
					magic[i][j+1] = 3;
				} else {
					magic[i][j] = 3;
				}
			}
		}
	}
	//B영역 채우기
	public void makeB() {
		int n = magic.length;
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				if (j<n/2 - (n/4-1)) {
					magic[i][j+n/2] = 2;
				} else {
					magic[i][j+n/2] = 1;
				}
			}
		}
	}
	//C영역 채우기
	public void makeC() {
		int n = magic.length;
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				if (magic[i][j] == 3) {
					magic[i+n/2][j] = 0;
				} else {
					magic[i+n/2][j] = 3;
				}
			}
		}
	}
	//D영역 채우기
	public void makeD() {
		int n = magic.length;
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				if (magic[i][j+n/2] == 2) {
					magic[i+n/2][j+n/2] = 1;
				} else {
					magic[i+n/2][j+n/2] = 2;
				}
			}
		}
	}
	//기능1: ABCD 각각의 값들에 (n/2)*(n/2)를 곱한다.
	public void func1() {
		int n = magic.length;
		for (int i = 0; i < n; i++) {
			for (int j=0; j<n; j++) {
				if (magic[i][j] != 0) {
					magic[i][j] *= (n/2)*(n/2);
				}
			}
		}
	}
	//기능2: n/2홀수 마방진을 구하여 각각의 값들에 더해준다.
	public void func2() {
		int n = magic.length;
		D3_MagicSquare ms2 = new D3_MagicSquare(n/2);
		ms2.make();

		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				this.magic[i][j] += ms2.magic[i][j];
				this.magic[i][j+n/2] += ms2.magic[i][j];
				this.magic[i+n/2][j] += ms2.magic[i][j];
				this.magic[i+n/2][j+n/2] += ms2.magic[i][j];
			}
		}
	}

	//마방진 가로/세로/대각선 합 출력 -> 마방진 조건에 부합하는지 확인
	public void sumPrint() {
		int n = magic.length;
		int[] sumU = new int[n+n+2];
		//1. 가로/세로합 확인
		for (int i=0; i<n; i++) {
			sumU[i] = sumCol(i);
			sumU[i+n] = sumRow(i);
		}
		//2. 대각선 확인
		sumU[sumU.length-2] = sumDia();
		sumU[sumU.length-1] = sumReverseDia();

		for (int i=0; i<sumU.length; i++) {
			System.out.print(sumU[i] + "\t");

			if ((i+1) % n == 0) {
				System.out.println();
			}
		}
		System.out.println();
		//마방진 조건확인
		magicSquareCheck(sumU);
	}

	//마방진 조건 확인 -> 가로/세로/대각선의 합이 모두 같아야함
	public void magicSquareCheck(int[] sum) {
		boolean isM = true;

		for (int i=0; i<sum.length-1; i++) {
			if (sum[i] != sum[i+1]) {
				System.out.println("마방진 조건에 맞지 않습니다.");
				isM = false;
				break;
			}
		}
		System.out.println();
		if (isM) {
			System.out.println("마방진 조건에 성립합니다.");
		}
	}

	//세로의 합
	public int sumRow(int j) {
		int sum=0;
		int n = magic.length;
		for (int i=0; i<n; i++) {
			sum += magic[i][j];
		}

		return sum;
	}
	//가로의 합
	public int sumCol(int j) {
		int sum=0;
		int n = magic.length;
		for (int i=0; i<n; i++) {
			sum += magic[j][i];
		}
		return sum;
	}
	//대각선의 합
	public int sumDia() {
		int sum = 0;
		int n = magic.length;
		for (int i=0; i<n; i++) {
			sum+= magic[i][i];
		}
		return sum;
	}
	//역대각선의 합
	public int sumReverseDia() {
		int sum=0;
		int n = magic.length;
		int x=n-1;
		int y=0;

		for (int i=0; i<n; i++) {
			sum+=magic[x][y];
			x--;
			y++;
		}

		return sum;
	}

	public void magicSquarePrint() {
		int n = magic.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(magic[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
