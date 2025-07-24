package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

public class D2_Player {
	
	//플레이어의 카드뭉치
	private List<D2_Card> cardList;
	
	public D2_Player() {
		cardList = new ArrayList<D2_Card>();
	}
	
	public List<D2_Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<D2_Card> cardList) {
		this.cardList = cardList;
	}
}
