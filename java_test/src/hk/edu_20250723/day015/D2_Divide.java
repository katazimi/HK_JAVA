package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

public class D2_Divide {
	
	//카드를 나눠주기
	public void divide(D2_CardCase cards, D2_Player p, int n) {
		List<D2_Card> divideCard = new ArrayList<D2_Card>();
		for (int i=0; i<n; i++) {
			divideCard.add(cards.getCard());
			cards.removeCard();
		}
		p.setCardList(divideCard);
	}
}