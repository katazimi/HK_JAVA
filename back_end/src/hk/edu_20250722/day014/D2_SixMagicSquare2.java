package hk.edu_20250722.day014;

import hk.edu_20250717.day11.D3_OddMagicSquare;

public class D2_SixMagicSquare2 extends D2_MagicSquare{
	
	public D2_SixMagicSquare2() {
		super(6);
	}
	public D2_SixMagicSquare2(int n) {
		super(n);
	}
	
	@Override
	public void make() {
		makeA();
		makeB();
		makeCD();
		multi();
		makeAdd();
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
}
