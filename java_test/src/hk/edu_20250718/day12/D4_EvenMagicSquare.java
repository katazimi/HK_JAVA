package hk.edu_20250718.day12;

//짝수 4의 배수 마방진
public class D4_EvenMagicSquare {
	public int[][] magicSquare;
	public int length;

	public D4_EvenMagicSquare(int n) {
		magicSquare = new int[n][n];
		length = n;
		int x=0;
		int y=0;

		for (int i=0; i<n*n; i++) {
			magicSquare[x][y] = i+1;
			y++;
			if (y==n) {
				x++;
				y=0;
			}
		}
	}

	//두개의 인수의 자리를 바꿈
	public void swap(int x1, int y1, int x2, int y2) {
		int temp = magicSquare[x1][y1];
		magicSquare[x1][y1] = magicSquare[x2][y2];
		magicSquare[x2][y2] = temp;
	}

	//기능1: 0,3번째 행, 1,2번째 열의 위치 값을 역순으로 변경
	public void function01() {
		int x = 0;
		int y = length/4;

		for (int i=0; i<length/4; i++) {
			y = length/4;
			for (int j=0; j<length/2; j++) {
				swap(x,y,length-1-x,length-1-y);
				y++;
			}
			x++;
		}
	}

	//기능2: 1,2번째 행, 0,3번째 열의 위치 값을 역순으로 변경
	public void function02() {
		int x = length/4;
		int y = 0;

		for (int i=0; i<length/4; i++) {
			x = length/4;
			for (int j=0; j<length/2; j++) {
				swap(x,y,length-1-x,length-1-y);
				x++;
			}
			y++;
		}
	}

	//마방진 가로/세로/대각선 합 출력 -> 마방진 조건에 부합하는지 확인
	public void sumPrint() {
		int[] sumU = new int[length+length+2];
		//1. 가로/세로합 확인
		for (int i=0; i<length; i++) {
			sumU[i] = sumCol(i);
			sumU[i+length] = sumRow(i);
		}
		//2. 대각선 확인
		sumU[sumU.length-2] = sumDia();
		sumU[sumU.length-1] = sumReverseDia();

		for (int i=0; i<sumU.length; i++) {
			System.out.print(sumU[i] + "\t");

			if ((i+1) % length == 0) {
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
		for (int i=0; i<length; i++) {
			sum += magicSquare[i][j];
		}

		return sum;
	}
	//가로의 합
	public int sumCol(int j) {
		int sum=0;
		for (int i=0; i<length; i++) {
			sum += magicSquare[j][i];
		}
		return sum;
	}
	//대각선의 합
	public int sumDia() {
		int sum = 0;
		for (int i=0; i<length; i++) {
			sum+= magicSquare[i][i];
		}
		return sum;
	}
	//역대각선의 합
	public int sumReverseDia() {
		int sum=0;
		int x=length-1;
		int y=0;

		for (int i=0; i<length; i++) {
			sum+=magicSquare[x][y];
			x--;
			y++;
		}

		return sum;
	}

	public void magicSquarePrint() {
		for (int i=0; i<length; i++) {
			for (int j=0; j<length; j++) {
				System.out.print(magicSquare[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

}
