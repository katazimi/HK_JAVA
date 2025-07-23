package hk.edu_20250722.day014;

public class D3_Card {
	private final String[] num = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "T", "J", "Q", "k"};
	private final String[] symbol = {"♥︎", "♣︎", "♠︎", "♦︎"};
	
	public String cardNum;
	public String cardSymbol;
	
	public D3_Card() {
		cardNum = num[random(12)];
		cardSymbol = symbol[random(3)];
	}
	
	public int random(int n) {
		int random = (int)(Math.random() * (n+1));
		return random;
	}
}
