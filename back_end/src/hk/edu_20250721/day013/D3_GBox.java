package hk.edu_20250721.day013;

import java.util.ArrayList;
import java.util.List;

public class D3_GBox<T> {

	public static void main(String[] args) {
		D3_GBox<String> strBox = new D3_GBox<>();
		strBox.set("hello Generic");
		System.out.println(strBox.get());

		D3_GBox<Integer>intBox = new D3_GBox<>();
		intBox.set(100);
		System.out.println(intBox.get());

		//타입 매개변수 규칙 T,E,K,V,M
//		List<E> list = new ArrayList<E>();
//		Map<String, String> map = new HashMap<>();

		//와일드 카드: <? extends T> T의 하위 타입들 : 읽기 전용
		List<? extends Number> numbers = new ArrayList<Number>();
//		numbers.add(500); //추가, 수정작업을 할수 없다.

		//데이터가 저장되어 있는 list객체 생성
		List<Integer> num = new ArrayList<Integer>();
		//데이터 추가
		num.add(100);num.add(101);num.add(102);num.add(103);
		//와일드 카드로 선언된 변수에 참조한다.
		numbers=num;
		System.out.println(numbers.toString());

		//<? super T> : T 또는 상위타입
		List<? super Integer> ints = new ArrayList<Number>();
		ints.add(222);

	}

	private T item;

	public void set(T item) {
		this.item = item;
	}

	public T get() {
		return item;
	}
}
