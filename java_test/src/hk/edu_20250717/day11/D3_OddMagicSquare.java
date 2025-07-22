package hk.edu_20250717.day11;

import java.util.Arrays;

public class D3_OddMagicSquare {

	public int [][] magic;
	
	public D3_OddMagicSquare() {
		this.magic=new int[3][3];
	}
	
	public D3_OddMagicSquare(int n) {
		this.magic=new int[n][n];
	}
	
	public void make() {
		int n=magic.length;
		int x=0;
		int y=n/2;// 3/2=1   5/2=2    0 1 2 3 4 
		magic[x][y]=1;//0번째 행에 중간인덱스 위치에 1값을 넣는다.
		
		//2~9까지의 숫자를 배열에 넣기
		for (int i = 2; i <= n*n; i++) {
			//값 변경 전에 원본값을 저장
			int tempX=x;//기본타입은 값을 복사
			int tempY=y;// 값이 바뀌지 않음
			
			if(x-1<0) {//-1 이동했는데 음수이면
				x=n-1; // x인덱스의 최대값으로 이동
			}else {
				x--;//음수가 아니라면 -1을 적용하자
			}
			if(y-1<0) {
				y=n-1;
			}else {
				y--;
			}
			
			if(magic[x][y]!=0) {// 이동한 위치에 값이 존재한다면.
				x=tempX+1;//원래 위치로 돌아와서 x+1
				y=tempY;//원래 위치로 돌아오기
			}
			
			magic[x][y]=i;//최종 위치로 증가된 값을 적용
			
		}
	}
	
	//---마방진 증명 확인---
	//가로의 합,  1차원배열내용:[6  1  8] , 2차원배열이기때문에 [0] : [0]->[1]->[2]  
	public int sumCol(int i) {
		int tot=0;
		//중간위치 코드 구현 : tot에 값을 더해가는 코드
		for (int j = 0; j < magic.length; j++) {
			tot+=magic[i][j];
		}
		return tot;
	}
	//세로의 합 : [0]->[1]->[2] : [0] 
	public int sumRow(int j) {
		int tot=0;
		for (int i = 0; i < magic.length; i++) {
			tot+=magic[i][j];
		}
		return tot;
	}
	//대각선의 합(왼쪽에서 내려가는)
	public int sumDia() {
		int tot=0;
		for (int i = 0; i < magic.length; i++) {
			tot+=magic[i][i];
		}
		return tot;
	}
	//대각선의 합(오른쪽에서 내려가는)
	public int sumReverseDia() {
		int tot=0;
		for (int i = 0; i < magic.length; i++) {
			tot+=magic[i][magic.length-1-i];//[i]:[2]->[1]->[0]
		}
		return tot;
	}
	//각각의 합을 구해서 같은지 확인하는 메서드
	//sum*메서드들을 활용해서 각각의 합이 같은지 확인
	public boolean isCheck() {
		boolean isC=true;
		
		int n=magic.length;
		int [] ma=new int[n*2+2];// 3*2+2=8  n*2+2=12
		
		//각각의 결과를 배열ma에 저장하자
		for (int i = 0; i < n; i++) {
			ma[i]=sumCol(i);//ma[0],[1],[2] 에 sumCol결과값을 저장
			ma[i+n]=sumRow(i);//ma[3],[4],[5] 에 sumRow결과값을 저장
		}
		ma[n*2]=sumDia();//ma[6]에 저장
		ma[n*2+1]=sumReverseDia();//ma[7]에 저장
		
		System.out.println(Arrays.toString(ma));
		
		//다른 값이 있는지 확인하자
		for (int i = 1; i < ma.length; i++) {
			if(ma[0]!=ma[i]) {//0번째 값과 다음 값들을 비교해서 다른 값이 있는 지 확인
				isC=false;
				break;
			}
		}
		return isC;
	}
	
	public void magicPrint() {
		int n=magic.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(magic[i][j]+"\t");
			}
			System.out.println(sumCol(i));//가로의 합
			System.out.println();
		}
		for (int i = 0; i < n; i++) {
			System.out.print(sumRow(i)+"\t");
		}
		System.out.println("\n대각선의 합:"+sumDia()+","+sumReverseDia());
		System.out.println("마방진 확인:"+isCheck());
	}
}