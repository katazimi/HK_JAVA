package coding.test_20250929;

public class problem9 {
	public int solution(int n, int w, int num) {
        int answer = 0;
        int number = 1;
        int idx=0;
        int step = (int)Math.ceil((n+0.0)/w);
        int num_step = (num-1)/w;
        int num_idx = num_step%2==0?(num-1)%w : w - 1 - (num-1)%w; //7->5,8->4 
        int[][] storage = new int[step][w];
        
        while (number <= n) {
            if (((number-1)/w)%2==0) {
                storage[(number-1)/w][idx] = number;
                number++;
                if (idx<w-1) {
                	idx++;
                }
            }else {
                storage[(number-1)/w][idx] = number;
                number++;
                if (idx>0) {
                	idx--;
                }
            }
        }
        
        for (int i=0; i<step; i++) {
        	for (int j=0; j<w; j++) {
        		System.out.print(storage[i][j]);
        	}
        	System.out.println();
        }
        
        while (num_step < step) {
        	if (storage[num_step][num_idx] != 0) {
        		answer++;
        	}
        	num_step++;
        }
        
        return answer;
    }

	public static void main(String[] args) {
		problem9 p = new problem9();
		
		System.out.println(p.solution(22, 6, 8)-1);

	}

}
