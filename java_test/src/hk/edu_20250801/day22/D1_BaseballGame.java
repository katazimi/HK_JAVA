package hk.edu_20250801.day22;

public class D1_BaseballGame {

	public int[] bowler; //투수 - 숫자 3개를 담고있음
	public int[] batter; //타자 - 숫자 3개를 담고 있음
	
	public D1_BaseballGame() {
		bowler = new int[3];
		batter = new int[3];
		makeRandom(bowler);
		makeRandom(batter);
	}
	
	//투수나 타자에 3개의 무작위 수를 입력
	public void makeRandom(int[] a) {
		for (int i=0; i<3; i++) {
			a[i] = (int)(Math.random() * 9 + 1);
		}
	}
	
	// 스트라이크인지 확인하는 메서드
	public static int isStrike(int[] bowler, int[] batter) {
		int count=0;
		for (int i=0; i<3; i++) {
			if(bowler[i] == batter[i]) {
				count++;
			}
		}
		
		return count;
	}
	
	// 볼인지 확인하는 메서드
	public static int isBall(int[] bowler, int[] batter) {
		int count=0;
		for (int i=0; i<3; i++) {
			if (i==0) {
				if (bowler[i] == batter[1])
					count++;
				if (bowler[i] == batter[2])
					count++;
			}
			else if (i==1) {
				if (bowler[i] == batter[0])
					count++;
				if (bowler[i] == batter[2])
					count++;
			}
			else if (i==2) {
				if (bowler[i] == batter[0])
					count++;
				if (bowler[i] == batter[1])
					count++;
			}
		}
		return count;
	}
	
	// 아웃인지 확인하는 메서드 - 스트라이크와 볼의 count 가 모두 0이라면 아웃임
	public static boolean isOut(int[] bowler, int[] batter) {
		if (isStrike(bowler,batter) == 0 && isBall(bowler,batter) == 0)
			return true;
		return false;
	}
	
	public void print(int[] a) {
		for (int i=0; i<3; i++) {
			System.out.print(a[i]+ " ");
		}
	}
}
