package hk.edu_20250725.day017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class D2_StreamTest {
	
	public static void main(String[] args) {
		//List 객체 -> Stream 객체로 생성 -> 증강연산, 최종연산
		List<String> list = Arrays.asList("홍길동", "임꺽정", "김홍도");
//		list.add("이순신"); //기존 List와 다르게 add()사용이 불가
		
		Stream<String> streamList = list.stream(); //Stream 생성
		streamList.filter(s->s.contains("홍")).sorted().forEach(s->System.out.println(s));
//		streamList.filter(s->s.startsWith("홍")).forEach(s->System.out.println(s)); //필터는 한번만 사용 가능
		
		System.out.println("--Stream을 사용하지 않을 경우--");
		List<String> list2 = new ArrayList<String>();
		for (String s: list) {
			if (s.contains("홍")) {
				list2.add(s);
			}
		}
		
		Collections.sort(list2);
		for (String s : list2) {
			System.out.println(s);
		}
		
		//List 객체를 Stream 객체로 생성한 후 맵핑과 수집 처리
		List<Integer> listNum = list.stream().map(s->s.length()).collect(Collectors.toList()); //.map(String::length).collect(Collectors.toList());도 가능함
		System.out.println(listNum.toString());
		
		List<String> list3 = List.of("A","B","C","D");
		
		//일반 스트림
		list3.stream().forEach(s->{System.out.println(s+" - "+Thread.currentThread().getName());});
		
		//병렬 스트림
		list3.parallelStream().forEach(s->{System.out.println(s+" - "+Thread.currentThread().getName());});
	}//main 종료
	
}
