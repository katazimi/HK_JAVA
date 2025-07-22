package hk.edu_20250722.day014;

import hk.edu_20250717.day11.D3_OddMagicSquare;

public class D1_SixMagicSquare {
	public static void main(String[] args) {
		D1_SixMagicSquare sms = new D1_SixMagicSquare(10);
		sms.make();
	}

	public int[][] magic;
	
	public D1_SixMagicSquare() {
		this(6);
	}
	public D1_SixMagicSquare(int n) {
		this.magic = new int[n][n];
	}
	
	public void make() {
		makeA();
		makeB();
		makeCD();
		multi();
		makeAdd();
		magicPrint();
		sumPrint();
	}
	
	//A영역에 n/4이 되는 영역을 3으로 채우는 기능
	public void makeA() {
		int n = magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/4; j++) {
				if (i == n/4)
					magic[i][j+1] = 3;
				else 
					magic[i][j] = 3;
			}
		}
	}
	//B영역에 마지막 열만 1로 채우자
	public void makeB() {
		int n = magic.length;
		//B영역에 우선 1을 채우자
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				magic[i][j+n/2] = 1;
			}
		}
		//마지막 열을 2로 수정
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2-(n/4-1); j++) {
				magic[i][j+n/2] = 2;
			}
		}
		
	}
	public void makeCD() {
		int n = magic.length;
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				//A영역에 접근해서 값을 출력
				if (magic[i][j] == 3) 
					magic[i+n/2][j]=0;
				else
					magic[i+n/2][j]=3;
				
				//B영역에 접근해서 값을 출력
				if (magic[i][j+n/2] == 1)
					magic[i+n/2][j+n/2] = 2;
				else
					magic[i+n/2][j+n/2] = 1;
			}
		}
	}
	
	//모든 값에 (n/2)*(n/2)를 곱해준다
	public void multi() {
		int n = magic.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				magic[i][j] *= (n/2)*(n/2);
			}
		}
	}
	
	//모든영역에 n/2홀수 마방진 값을 더해준다
	public void makeAdd() {
		int n = magic.length;
		D3_OddMagicSquare odd = new D3_OddMagicSquare(n/2);
		odd.make();
		int[][] oddMagic = odd.magic;
		
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n/2; j++) {
				magic[i][j] += oddMagic[i][j]; //A영역에 홀수 마방진을 더함
				magic[i][j+n/2] += oddMagic[i][j]; //B영역에 홀수 마방진을 더함
				magic[i+n/2][j] += oddMagic[i][j]; //C영역에 홀수 마방진을 더함
				magic[i+n/2][j+n/2] += oddMagic[i][j]; //D영역에 홀수마방진을 더함
			}
		}
	}
	
	public void magicPrint() {
		int n = magic.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(magic[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
		
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
}
