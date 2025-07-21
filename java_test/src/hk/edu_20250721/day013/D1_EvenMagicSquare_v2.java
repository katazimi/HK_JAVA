package hk.edu_20250721.day013;

public class D1_EvenMagicSquare_v2 {

	//클래스의 구성요소? 멤버필드,생성자,메서드
	//클래스의 내부코드는 절차식(X) -> 호출되는 순서
	//2차원배열에 구현 -> 2차원 배열이 필요
	public int[][] magic;

	public D1_EvenMagicSquare_v2() {
		this(4);
	}

	public D1_EvenMagicSquare_v2(int n) {
		this.magic=new int[n][n];
		make();
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

	//makeA(), makeB()
	//templete 메서드: 절차적으로 기능을 실행시키는 메서드
	public void make() {
		makeA();
		makeB();
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
