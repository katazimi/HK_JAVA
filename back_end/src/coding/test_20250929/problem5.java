package coding.test_20250929;

public class problem5 {
	
	public static int[] arr = new int[5010];
	
	public int tile(int n) {
		if (arr[n]!=0) {
			return (int) arr[n];
		}else if(n==0) {
			return 1;
		}else if(n==2) {
			return 3;
		}else if(n==4) {
			return 11;
		}else {
			return arr[n] = (4*tile(n-2)-tile(n-4))%1000000007;
		}
    }
    
    public int solution(int n) {
        int answer = 0;
        answer = tile(n);
        return answer;
    }

	public static void main(String[] args) {
		problem5 p = new problem5();
		System.out.println(p.solution(100));
	}
}
