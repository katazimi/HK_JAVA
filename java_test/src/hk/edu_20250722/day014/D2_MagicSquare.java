package hk.edu_20250722.day014;

//추상 클래스
public abstract class D2_MagicSquare implements D2_IMagic{
	
	protected int[][] magic;
	
	public D2_MagicSquare(int n) {
		this.magic=new int[n][n];
	}
	
	@Override
	public abstract void make(); //추상 메서드

	@Override
	public void magicPrint() {
		int n = magic.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(magic[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
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
		boolean isM = true;

		for (int i=0; i<sumU.length-1; i++) {
			if (sumU[i] != sumU[i+1]) {
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
