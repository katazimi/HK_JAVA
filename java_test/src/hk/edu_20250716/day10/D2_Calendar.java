package hk.edu_20250716.day10;

import java.util.Calendar;

public class D2_Calendar {
	//윤년일 때 달의 마지막 날
	private static final int[] leap = {31,29,31,30,31,30,31,31,30,31,30,31};
	//평년일 때 달의 마지막 날
	private static final int[] plain = {31,28,31,30,31,30,31,31,30,31,30,31};

	//윤년을 판단하는 메서드: 반환타입은 true/false
	public boolean isLeapYear(int year) {
		boolean isS=false;
		if((year%4==0&&year%100!=0)||year%400==0) {
			isS=true;
		}
		return isS;
	}

	//1년1월1일 ~ 2025년7월1일 경과일 구하기
	//전년도까지의 일수 + 1월~6월까지 일수

	//1. 전년도까지의 경과일
	public int dates(int year) {
		int total=0;
		for (int i=1; i<year; i++) {
			if (isLeapYear(i)) {
				total+=366;
			} else {
				total+=365;
			}
		}

		return total;
	}

	//2. 해당 월까지 경과일
	public int dates(int year, int month) {
		int total = dates(year); //전년도까지 경과일을 초기값으로 설정
		for (int i=1; i<month; i++) {
			if (isLeapYear(year)) {
				total+=leap[i-1];
			} else {
				total+=plain[i-1];
			}
		}

		return total;
	}

	//3. 전년도 경과일 + 해당 월까지 경과일 + 현재일
	public int dates(int year, int month, int date) {
		return dates(year,month) + date;
	}

	//4. 해당 달의 마지막날을 반환
	public int lastDay(int year, int month) {
		return isLeapYear(year)?leap[month-1]:plain[month-1];
	}

	//한달을 출력하는 메서드
	public void calendarPrint(int year, int month) {
		System.out.printf("%d년 %d월\n",year,month);
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n","일","월","화","수","목","금","토");
		int dayOfWeek = dates(year,month,1) % 7;
		//달력의 공백을 출력
		for (int i=0; i<dayOfWeek; i++) {
			System.out.print("\t");
		}

		//달력 그리기
		for (int i=1; i<=lastDay(year, month); i++) {
			System.out.print(i+"\t");
			//토요일이 되면 줄을 바꿈
			if ((i+dayOfWeek) % 7 == 0) { //또는 i % 7 == 7-dayOfWeek
				System.out.println();
			}
		}
	}

	public void calendarPrint(int year, int month1, int month2) {
		for (int i=month1; i<=month2; i++) {
			calendarPrint(year, i);
			System.out.println();
			System.out.println();
		}
	}

	public int dDay(int syear, int smonth, int sday,  int eyear, int emonth, int eday) {
		int day =  dates(eyear, emonth, eday)- dates(syear,smonth,sday);

		return day;
	}

	public static void main(String[] args) {
		D2_Calendar cal = new D2_Calendar();
		cal.calendarPrint(2025, 1, 12);

		System.out.println("살아온 일수: " + cal.dDay(2001, 8, 26, 2025, 7, 17));
		System.out.println();

		//API로 구현
		for (int i=1; i<=12; i++) {
			cal.calendarApiPrint(2025, i);
		}

	}

	public void calendarApiPrint(int year, int month) {
		//Calendar : 추상클래스 --> new 객체생성이 불가 --> getInstance()를 통해 객체를 얻어옴
		Calendar cal = Calendar.getInstance();	//객체를 생성하면 오늘 날짜 기준
//		System.out.println(cal.get(Calendar.DATE));

		//Calendar API에서는 월을 나타낼때 0~11로 관리함
		cal.set(year, month-1, 1); //특정 날짜로 세팅
		//해당 달의 마지막날 값을 가져오기 : 1-31중 가장 큰값
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		System.out.printf("%d년 %d월\n",year,month);
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n","일","월","화","수","목","금","토");
		for (int i=0; i<dayOfWeek-1; i++) {
			System.out.print("\t");
		}
		for (int i=1; i<=lastDay; i++) {
			System.out.print(i + "\t");
			if ((i+dayOfWeek-1) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println("\n");
	}
}
