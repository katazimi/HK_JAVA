package coding.test_20250929;

import java.util.ArrayList;
import java.util.List;

public class problem4 {
	
	public boolean solution(int x) {
        boolean answer = true;
        List<Integer> list = new ArrayList<Integer>();
        String str = String.valueOf(x);
        int sum=0;
        
        for (int i=0; i<str.length(); i++) {
        	list.add(Integer.parseInt(str.substring(i,i+1)) );
        }
        
        for (int i=0; i<list.size(); i++) {
        	sum += list.get(i);
        }
        System.out.println(x+"%"+sum+"="+x%sum);
        
        if (x%sum!=0) {
        	answer=false;
        }
        return answer;
    }

	public static void main(String[] args) {
		problem4 p = new problem4();
		System.out.println(p.solution(11)); 
	}

}
