package hk.edu_20250704.day03;

public class D1_ControlFlow {

	public static void main(String[] args) {
		//if문
		//유형 3가지
		//1. if(조건){실행코드}
		//2. if(조건){실행코드}else{실행코드}
		//3. if(조건){실행코드}else if(조건){실행코드}else if(조건){실행코드}
		//4. 중첩if

		int num1=10;
		int num2=5;

		//if 문을 독립적으로 사용할 경우 : if 문 끼리는 영향 없
		if (num1>num2) {
			System.out.println("실행결과: "+num1+" > "+num2);
		}
		if (num1<num2) {
			System.out.println("실행결과: "+num1+" < "+num2);
		}

		//조건이 true or false에 따라 반드시 하나만 실행
		if (num1>num2) {
			System.out.println("실행결과: "+num1+" > "+num2);
		}
		else {
			System.out.println("실행결과: "+num1+" < "+num2);
		}

		//switch-case문 : 대상이 정수형 or String 타입
		//break를 작성하지 않으면 만족하는 case 이후 모든 case가 실행됨
		int num=10;
		switch (num) {
		case 1: System.out.println("1입니다."); break;
		case 5: System.out.println("5입니다."); break;
		case 10: System.out.println("10입니다."); break;
		default:
			System.out.println("일치하는 값이 없습니다."); break;
		}

		//반복문
		//for : 기본형식(index 기반의 실행), 향상된 for(기본형식보다 간단)
		//1. 초기값 선언	2. 조건확인	3. 코드실행	4. 스탭증가 --> 반복
		for (int i = 0; i < 10; i++) {
			if (i==5) {
				continue;
			}
				//break;
			System.out.print(i+" ");
		}

		//향상된 for
		int[] i = {1,2,3,4,5,6};
		for (int val : i) {
			System.out.print(val+" ");
		}
		for (int j=0; j<i.length; j++) {
			System.out.print(i[j]+" ");
		}

		//while
		int w=0;
		while (true) {
			System.out.println("while문 실행");
			if(w>5) {
				break;
			}
			w++;
		}

		//do-while : 최초 한번은 무조건 실행
		do {
			System.out.println("조건에 해당되지 않아도 모조건 한번은 실행");
		} while (10<5);

	}

}
