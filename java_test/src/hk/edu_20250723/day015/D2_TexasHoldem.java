package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

public class D2_TexasHoldem {
	
	private D2_CardCase cards = new D2_CardCase();
	private D2_Divide divide = new D2_Divide();
	
	public List<D2_Player> players;
	public D2_Player table;
		
	public D2_TexasHoldem() {
		players = new ArrayList<D2_Player>();
		table = new D2_Player();
		for (int i=0; i<2; i++)
			players.add(new D2_Player());
		dealingCards();
	}
	
	public void dealingCards() {
		divide.divide(cards, players.get(0), 2);
		divide.divide(cards, players.get(1), 2);
		divide.divide(cards, table, 3);
	}
	
	public void flop() {
		System.out.println("[Table]");
		System.out.println(table.getCardList());
	}
	
	public void turn() {
		addCard();
		System.out.println("[Table]");
		System.out.println(table.getCardList());
	}
	
	public void river() {
		addCard();
		System.out.println("[Table]");
		System.out.println(table.getCardList());
	}
	
	public void match() {
		
	}
	
	public void die() {
		
	}
	
	//테이블의 카드 한장을 추가
	public void addCard() {
		List<D2_Card> card = new ArrayList<D2_Card>();
		card = table.getCardList();
		card.add(cards.getCard());
		cards.removeCard();
	}
	
}
