package hk.edu_20250710.day07;

public class D1_StringMethodTest {

	//String 주요 메서드 연습

	//1. 문자 하나를 반환
	//"문자열에서 문자 하나를 인덱스로 추출하는 기능" --> "".charAt(6) -> '문'
	//charAt(index)
	public String sTest01(String s, int idx) {
		char c = s.charAt(idx);
		String ss = c+"";//문자열을 만나면 문자가 됨
		String ss2 = String.valueOf(c);//문자열로 변환시켜주는 메서드

		//예시) 숫자형태의 문자열을 숫자로 변환
		int i =Integer.parseInt("100"); //"100" -> 100

		return ss;
	}

	//2. 문자열에서 원하는 문자의 인덱스를 반환 : indexOf()
	// "ABCD" -> "BC" 검색 --> "ABCD".indexOf("BC")
	// 반환값 : 해당 문자의 첫번째 인덱스를 반환
	// 종류 : indexOf() / lastIndexOf() --> 차이점은 앞에서부터 or 뒤에서부터
	public void sTest02(String s) {
		int s1 = s.indexOf("AB"); //단어를 처음부터 검색
		int s2 = s.indexOf("C",2); // 검색 시작 인덱스도 지정 가능
		int s3 = s.indexOf("DF",2,5); //범위 지정 가능
		int s4 = s.lastIndexOf("F");

		System.out.printf("%d, %d, %d, %d\n",s1,s2,s3,s4);

		//해당 단어가 존재하는지 확인하는 용도
		//해당 단어가 존재하지 않으면 -1을 반환
		if (s.indexOf("A")!=-1) {
			System.out.println("A가 존재합니다.");
		}
	}

	//3. 문자열 길이를 반환 : length();
	public int sTest03(String s) {
		return s.length();
	}

	//4. 문자열의 내용 변환 : replace("원본", "새로운 내용")
	public void sTest04() {
		String s = "자바프로그래밍자바웹개발자자바스크립트";
		s.replace("자바", "java"); //원본 s의 내용은 바뀌지 않음(immutable)
		s = s.replace("자바", "java");//따라서 다시 대입해야함
		System.out.println(s);
	}

	//5. 문자열 추출 : substring();
	//substring(idx) : idx부터 끝까지 추출
	//substring(sidx, eidx) : sidx부터 eidx까지 추출
	public void sTest05(String s) {
		String ss = s.substring(2);
		String ss2 = s.substring(0, 2);

		System.out.println(ss+", "+ss2);
	}

	//예제:
	//문자열에서 해당 검색어가 존재하는지 판단하여 존재한다면 해당 검색어를 추출하여
	//출력하고, "###"으로 변경하여 처리하고, 계속 검색어가 존재하는지 확인하여
	//앞에 작업을 진행한다.
	//
	//1.해당 검색어가 존재하는 여부 판단해보기,해당검색어가 없으면 "검색어가 존재하지 않습니다."출력
	//2.해당 검색어의 인덱스를 구해보기: 검색어 인덱스 출력하기
	//3.해당 검색어를 추출해서 출력해보기: substring()을 사용해서 추출한뒤  출력하기
	//4.해당 검색어를 문자열에서 ###으로 바꿔주기
	//5.해당 검색어의 검색된 개수 출력하기[indexOf("검색어",검색시작인덱스)]

	public void search(String str) {//str = "카카오"
		String s="카카오페이가 소상공인 상생 캠페인 '오래오래 함께가게'의 올해 두 번째 팝업스토어를 여의도 IFC몰에 오픈했다고 10일 밝혔다.\r\n"
				+ "\r\n"
				+ "오래오래 함께가게는 카카오페이와 함께일하는재단이 소상공인의 지속가능한 사업 성장을 위해 지원하는 상생 카카오캠페인이다.\r\n"
				+ "\r\n"
				+ "2023년부터 시작한 오래오래 함께가게는 지난 5월 올해 첫 카카오팝업스토어를 현대백화점에서 2주간 운영했다. 리빙, 패션잡화, 식품, 친환경제품 등 254개 소상공인 브랜드가 23만여명의 방문객을 만날 수 있도록 지원해 왔다.";

		int count = 0; //검색어 갯수를 담는 변수

		//1. 존재여부 확인
		if (s.indexOf(str) == -1) {
			System.out.println("검색어가 존재하지 않습니다.");
		}
		else {
			int idx = s.indexOf(str);
			count++;
			System.out.println(s.substring(idx, idx + str.length())); //3. 검색어 추출
			System.out.println("인덱스 : "+ idx);//인덱스 출력
			//5. 검색어 갯수 구하기
			while(s.indexOf(str,idx + str.length()) != -1) {
				idx = s.indexOf(str, idx+ str.length());
				System.out.println("인덱스 : " + idx);
				count++;
			}
			//4. 문자열에서 ###으로 바꿔주기
			s = s.replace(str, "###");
			System.out.println(s);
			System.out.println(str+" 검색 갯수 : "+count);
		}
	}

}
