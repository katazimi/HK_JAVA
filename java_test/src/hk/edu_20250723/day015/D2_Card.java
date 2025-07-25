package hk.edu_20250723.day015;

//Card클래스는 카드 한장을 의미
public class D2_Card {
	//카드를 만들기 위해 필요한 필드 선언
	//[그림+숫자]: 카드객체를 생성하면 만들어지는 문자열 "그림+숫자"
	//			--> 랜덤하게 만들어지도록 하자
	public static final String[] DECK = {"♥︎", "♣︎", "♠︎", "♦︎"};
	public static final String[] STECK = 
			{"A","2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	
	//카드 한장을 저장할 필드: 그림 + 숫자
	private String card; //"♥︎3"
	
	public D2_Card() {
		init();
	}
	
	//카드 한장을 만드는 기능
	private void init() {
		int a = (int)(Math.random() * DECK.length); //index 0~3
		int b = (int)(Math.random() * STECK.length); //index 0~12
		card = DECK[a] + STECK[b];
	}


	//은닉화: 메서드를 통해 접근가능
	public String getCard() {
		return card;
	}
	
	public String getDECK() {
		for (String suit : DECK) {
            if (card.startsWith(suit)) {
                return suit;
            }
        }
        return card.substring(0, 1);
	}
	
	public String getSTECK() {
		for (String suit : DECK) {
            if (card.startsWith(suit)) {
                return card.substring(suit.length());
            }
        }
        return card.substring(1);
	}
	
	@Override
	public String toString() {
		return "["+card+"]";
	}
	
	//카드 객체 내부에 멤버필드인 card 끼리 비교하는 기능으로 재정의
	@Override
	public boolean equals(Object obj) {
		boolean isS = false;
		D2_Card ca = (D2_Card)obj; //Card --> object --> Card 형변환
		if (card.equals(ca.getCard())) {
			isS = true;
		}
		return isS;
	}
	
	//equals()를 오버라이딩하면 hashcode()도 오버라이딩해야함
	@Override
	public int hashCode() {
		return card.hashCode() + 137;
	}
}