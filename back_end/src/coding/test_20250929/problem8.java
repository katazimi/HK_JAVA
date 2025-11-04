package coding.test_20250929;

import java.util.HashMap;

public class problem8 {
	public String cut(String s, int idx) {
        String result = "";
        if (idx%2==0) {
            result = s.substring(0,idx+1);
        }else {
            result = s.substring(idx);
        }
        
        return result;
    }
    
    public int[] solution(int[] arr, int[] query) {
        int[] answer;
        String s = "";
        
        for (int i=0; i<arr.length; i++) {
            s += arr[i];
        }
        
        for(int i=0; i<query.length; i++) {
            s = cut(s,query[i]);
            System.out.println(s);
        }
        
        answer = new int[s.length()];
        
        for (int i=0; i<s.length(); i++) {
            answer[i] = Integer.parseInt(s.substring(i,i+1));
        }
        
        return answer;
    }
    public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5};
		int[] query = {4,1,2};
		
		problem8 p = new problem8();
		
		int[] answer = p.solution(arr, query);
		
		for (int i=0;i<answer.length; i++) {
			System.out.print(answer[i]);
		}
		String[] strs = new String[10];
		HashMap<String,Integer>map = new HashMap<>();
		map.put("a", 1);
		System.out.println(map.get("a"));
	}
}
