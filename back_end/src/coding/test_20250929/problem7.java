package coding.test_20250929;

public class problem7 {
	public String swap(String s, int a, int b) {
        String s1 = s.substring(0,a);
        String s2 = "";
        String s3 = s.substring(b+1);
        
        for (int i=b; i>=a; i--) {
            s2 += s.substring(i,i+1);
        }
        
        String result = s1+s2+s3;
        
        System.out.println(s1+"+"+s2+"+"+s3);
        return result;
    }
    
    public String solution(String my_string, int[][] queries) {
        String answer = "";
        
        for (int i=0; i<queries.length; i++) {
            my_string = swap(my_string,queries[i][0],queries[i][1]);
            System.out.println(my_string);
        }
        answer = my_string;
        return answer;
    }
    
    public static void main(String[] args) {
		String my_string = "rermgorpsam";
		int[][] queries = {{2,3},{0,7},{5,9},{6,10}};
		
		problem7 p = new problem7();
		
		String result = p.solution(my_string, queries);
		
		System.out.println(result);
		
		System.out.println(my_string.substring(1));
	}
}
