package hk.edu_20250722.day014;

import java.util.Arrays;
import java.util.Set;

public class D3_CardCase {
	
	public D3_Card[] cardCase;
	
	public D3_CardCase() {
		cardCase = new D3_Card[52];
	}
	
	public void make() {
		int idx;
		D3_Card card = new D3_Card();
		cardCase[0] = card;
		
		for (int i=1; i<52; i++) {
			idx = 0;
			while (idx < i) {
				if (!isOverlap(card, cardCase[idx])) {
					idx=0;
					card = new D3_Card();
					continue;
				}
				else
					idx++;
			}
			cardCase[i] = card;
		}
	}
	
	private boolean isOverlap(D3_Card card1, D3_Card card2) {
		if (card1.cardNum.equals(card2.cardNum) && card1.cardSymbol.equals(card2.cardSymbol))
			return false;
		else
			return true;
	}

	public static void main(String[] args) {
		D3_CardCase trump = new D3_CardCase();
		trump.make();
		String[] cardcase = new String[52];
		for (int i=0; i<52; i++) {
			cardcase[i] = trump.cardCase[i].cardSymbol + trump.cardCase[i].cardNum;
		}
		Arrays.sort(cardcase);
		
		for (int i=0; i<52; i++) {
			System.out.println(cardcase[i]);
		}
	}
	
}
