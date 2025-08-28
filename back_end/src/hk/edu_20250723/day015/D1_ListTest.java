package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

import hk.edu_20250714.day08.D3_Lotto;

public class D1_ListTest {

	public static void main(String[] args) {
		//list 사용하기
		//제네릭 개념: 타입을 나중에 정의하도록 선언하는 방법
		List<String> list = new ArrayList<String>();
//		List<Integer> aa = new ArrayList<Integer>();
		list.add("한"); //값만 추가한다면, 마지막 index+1 위치에 자동으로 추가
		list.add("경");
		list.add("닷");
		list.add("컴");
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i));
		}
		System.out.println();
		
//		//제네릭을 사용하지 않은 경우
//		List list2 = new ArrayList();
//		list.add("A");
//		String s = (String)list2.get(0); //String 타입으로 변환해야함
//		System.out.println(s);
		
		System.out.println(list.contains("한"));
		List<D3_Lotto>lot = new ArrayList<>();
		D3_Lotto lot2 = new D3_Lotto();
		lot.add(lot2);
		lot.add(lot2);
		lot.add(new D3_Lotto());
		System.out.println(lot.contains(new D3_Lotto()));
		//ArrayList의 경우 추가 등의 작업은 성능이 낮다
		list.add(1,"국");
		list.add(3,"제");
		list.remove(0);//삭제
//		list.clear(); //모두 삭제
		System.out.println(list);
		
		//목록 조회: 향상된 for문 사용
		for (String ss: list) {
			System.out.print(ss);
		}
	}
}
