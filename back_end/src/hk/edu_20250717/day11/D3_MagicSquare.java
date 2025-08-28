package hk.edu_20250717.day11;

import java.util.Arrays;

public class D3_MagicSquare {
	public int[][] magic;
	public int x,y;
	public int num;
	public int max;
	public int n;

	public D3_MagicSquare(int n) {
		magic = new int[n][n];
		this.n = n;
		num = 1;
		x = 0;
		y = n/2;
		max = n*n;
	}

	//이동메서드
	public void move() {
		int preX = x;
		int preY = y;
		x -= 1;
		y -= 1;

		//0보다 작아지면 최대값으로
		if (x < 0) {
			x = n-1;
		}
		if (y < 0) {
			y = n-1;
		}

		//값이 있다면? 원래자리로 이동 -> x + 1
		if (magic[x][y] != 0) {
			x=preX;
			y=preY;
			while (x<n && y<n &&magic[x][y] != 0) {
				x+=1;
			}
		}

 	}

	//마방진에 숫자 삽입
	public void insert() {
		magic[x][y] = num;
		num++;
	}

	//마방진 그리기
	public void make() {
		for (int i=0; i<max; i++) {
			insert();
			if (i == max-1) {
				break;
			}
			move();
		}
		draw();
	}

	//마방진 출력
	public void draw() {
		for (int i=0; i<magic.length; i++) {
			System.out.println(Arrays.toString(magic[i]));
		}
		System.out.println();
	}

	//마방진 가로/세로/대각선 합 출력 -> 마방진 조건에 부합하는지 확인
	public void sumPrint() {
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

			if (i+1 % n == 0) {
				System.out.println();
			}
		}

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
		if (isM) {
			System.out.println("마방진 조건에 성립합니다.");
		}
	}

	//세로의 합
	public int sumRow(int j) {
		int sum=0;
		for (int i=0; i<n; i++) {
			sum += magic[i][j];
		}

		return sum;
	}
	//가로의 합
	public int sumCol(int j) {
		int sum=0;
		for (int i=0; i<n; i++) {
			sum += magic[j][i];
		}
		return sum;
	}
	//대각선의 합
	public int sumDia() {
		int sum = 0;
		for (int i=0; i<n; i++) {
			sum+= magic[i][i];
		}
		return sum;
	}
	//역대각선의 합
	public int sumReverseDia() {
		int sum=0;
		int x=n-1;
		int y=0;

		for (int i=0; i<n; i++) {
			sum+=magic[x][y];
			x--;
			y++;
		}

		return sum;
	}
}
