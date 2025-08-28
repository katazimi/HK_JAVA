package hk.edu_20250723.day015;

import java.util.List;

public class D2_CardMain {

	public static void main(String[] args) {
		D2_Card card = new D2_Card();
		System.out.println(card);
		System.out.println("=============================================================================");
		
		D2_CardCase cardCase = new D2_CardCase();
		List<D2_Card> cards = cardCase.getCards();
		
		for (int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i) + "\t");
			if ((i+1) % 10 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		
		D2_Player player1 = new D2_Player();
		D2_Player player2 = new D2_Player();
		D2_Player player3 = new D2_Player();
		D2_Divide di = new D2_Divide();
		
		di.divide(cardCase,player1,5);
		di.divide(cardCase,player2,5);
		di.divide(cardCase,player3,5);
		
		System.out.println("=============================================================================");
		System.out.println("user1: "+player1.getCardList());
		System.out.println("user2: "+player2.getCardList());
		System.out.println("user3: "+player3.getCardList());
		System.out.println("=============================================================================");
		
		D2_TexasHoldem texas = new D2_TexasHoldem();
		texas.flop();
		texas.turn();
		texas.river();
	}

}
