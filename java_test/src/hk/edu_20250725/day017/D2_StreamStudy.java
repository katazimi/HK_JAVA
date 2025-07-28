package hk.edu_20250725.day017;

import java.util.Arrays;
import java.util.List;

public class D2_StreamStudy {
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		numbers.stream().forEach(n->System.out.print(n));
		
		List<String> names = Arrays.asList("홀길동", "고길동", "홍준표");
		names.stream().filter(name->name.startsWith("홍")).forEach(name->System.out.println(name));

	}

}
