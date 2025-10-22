package coding.test_20250929;

import java.util.ArrayList;
import java.util.List;

public class problem6 {
	
	public static int[] solution(int l, int r) {
        int[] answer;
        List<Integer>list = new ArrayList<Integer>();
        for (int i=l; i<=r; i++) {
            String num = i+"";
            if(!(num.contains("1")||num.contains("2")||num.contains("3")||
               num.contains("4")||num.contains("6")||num.contains("7")||
               num.contains("8")||num.contains("9"))) {
			    list.add(i);
		    }
        }
        
        if (list.size()>0) {
        	answer = new int[list.size()];
        	for (int i=0; i<list.size(); i++) {
            	answer[i] = list.get(i);
            }
        }else {
        	answer = new int[1];
        	answer[0] = -1; 
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[] number = solution(10, 20);
		for (int i=0; i<number.length; i++) {
			System.out.println(number[i]);
		}
		
	}

}
