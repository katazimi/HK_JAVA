package hk.edu_20250723.day015;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class D1_SetTest {
	
	public static void main(String[] args) {
//		Set<String> set = new HashSet<String>();
		Set<String> set = new LinkedHashSet<String>();
			set.add("한");
			set.add("국");
			set.add("경");
			set.add("제");
			set.add("신");
			set.add("문");
			set.add("문"); //중복된 값X
			
			Iterator<String> iter = set.iterator(); //Set -> Iterator로 변환
			while(iter.hasNext()) {
				String str = iter.next();
				System.out.print(str + " ");
			}
			System.out.println();
			
			//향상된 for
			for (String ss : set) {
				System.out.print(ss + " ");
			}
			System.out.println();
			
	}
}
