package hk.edu_20250723.day015;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class D1_MapTest {

	public static void main(String[] args) {
		//Map -> key1:value
		//		 key2:value
		//js -> json 형식을 사용하는 객체가 있다
		//		{key1:value,k2:value...}
		//Map -> json 형식으로 변환 가능
		
		Map<String, String> map = new HashMap<>();
		map.put("하나", "한경");
		map.put("둘", "닷컴");
		map.put("셋", "교육센터");
		map.put("셋", "센터교육"); //Key 값은 중복되면 안됨!
		
		System.out.println(map.get("하나") + ", " +map.get("둘") + ", " + map.get("셋"));
		
		//Map 에서 일괄적으로 모든 데이터를 가져오려면?
		//1. Iterator 이용
		Set<String> setKeyMap = map.keySet();
		Iterator<String> iter = setKeyMap.iterator();
		while (iter.hasNext()) {
			String str = iter.next();
			System.out.println(map.get(str));
		}
		//2. 향상된 for 이용
		for (String str : setKeyMap) {
			System.out.println(map.get(str));
		}
	}
}
