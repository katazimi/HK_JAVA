package hk.edu_20250722.day014;

public class D2_OddMagicSquare extends D2_MagicSquare{

	public D2_OddMagicSquare() {
		super(3);
	}
	
	public D2_OddMagicSquare(int n) {
		super(n);
	}
	
	@Override
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
				x=n-1; // x 인덱스의 최대값으로 이동
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
	
	
}