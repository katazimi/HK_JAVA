package coding.test_20250929;

public class problem3 {
	
	 public static int solution(int n) {
	        int answer = 0;
	        String v;
	        boolean bool;
	        
	        for (int i=1; i<=n; i++) {
	        	answer++;
	        	bool=true;
	        	while (bool==true) {
	        		v = answer+"";
	        		if (answer%3==0) {
		        		answer++;
		        		continue;
		        	}
		        	else if (v.contains("3")) {
		        		answer++;
		        		continue;
		        	}
		        	else {
		        		bool = false;
		        	}
	        		
	        	}
	        	
	        }
	        
	        return answer;
	   }

	public static void main(String[] args) {
		System.out.println(solution(15));
	}

}
