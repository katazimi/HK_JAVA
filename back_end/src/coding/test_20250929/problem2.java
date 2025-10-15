package coding.test_20250929;

public class problem2 {
	
	public static int[] solution(int[] arr, int[][] queries) {
        int[] answer = arr;
        
        for (int i=0; i<queries.length; i++) {
        	for (int j=queries[i][0]; j<=queries[i][1]; j++) {
        		if (j%queries[i][2]==0) {
        			arr[j]+=1;
        			System.out.println(j+"%"+queries[i][2]+"="+j%queries[i][2]);
        		}
        	}
        }
        return answer;
    }

	public static void main(String[] args) {
		int[] arr = {0,1,2,4,3};
		int[][] queries = {{0,4,1},{0,3,2},{0,3,3}};
		
		int[] result = solution(arr, queries);
		
		for(int i=0; i<5; i++) {
			System.out.println(result[i]);
		}
	}

}
