package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

//Card 52장이 담긴 List 객체를 가지고 있는 클래스를 구현
public class D2_CardCase {
	
	//Card 객체를 저장할 List 멤버필드 정의
	private List<D2_Card> cards;
	
	//카드의 총 장수 저장
	private static final int NUMOFCARDS = D2_Card.DECK.length * D2_Card.STECK.length;
	
	public D2_CardCase() {
		cards = new ArrayList<D2_Card>();
		shuffle();
	}
	
	//카드를 생성해서 card(List 객체)에 저장하는 메서드
	public void shuffle() {
		int i=0;
		while (true) {
			D2_Card cc = new D2_Card(); //카드 한장 만들어지는 개념
			if (!cards.contains(cc)) { //Cards 에 중복된 card 가 없다면
				cards.add(cc); //카드를 추가
				i++;
			}
			if (i==NUMOFCARDS)
				break;
		}
	}

	public List<D2_Card> getCards() {
		return cards;
	}
	
	//카드뭉치의 맨 앞장을 리턴
	public D2_Card getCard() {
		return cards.get(0);
	}
	//카드뭉치의 맨 앞장을 제거
	public void removeCard() {
		cards.remove(0);
	}
}