package coding.test_20250929;

public class problem1 {
	
	public static int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int x=0;
        int y=0;
        int w=1; //방향: 1-오른쪽, 2-아래, 3-왼쪽, 4-위
        
        for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				answer[i][j]=0;
			}
		}
        
        for (int i=1; i<n*n+1; i++) {
        	answer[x][y] = i;
        	switch (w) {
			case 1:
				if (y+1 != n && answer[x][y+1]==0) {
					y++;
				}else{
					x++;
					w++;
				}
				break;
			case 2:
				if (x+1 != n && answer[x+1][y]==0) {
					x++;
				}else {
					y--;
					w++;
				}
				break;
			case 3:
				if (y != 0 && answer[x][y-1]==0) {
					y--;
				}else {
					x--;
					w++;
				}
				break;
			case 4:
				if (x != 0 && answer[x-1][y]==0) {
					x--;
				}else {
					y++;
					w=1;
				}
				break;
        	}
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[][] array = solution(4);
		for (int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}

	}

}
