package hk.edu_20250722.day014;

public class D3_Card {
	private final String[] num = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "k"};
	private final String[] symbol = {"♥︎", "♣︎", "♠︎", "♦︎"};
	
	public String cardNum;
	public String cardSymbol;
	
	public D3_Card() {
		cardNum = num[random(12)];
		cardSymbol = symbol[random(3)];
	}
	
	//0~n 까지의 수 중에서 랜덤한 숫자 하나를 반환
	public int random(int n) {
		int random = (int)(Math.random() * (n+1));
		return random;
	}
}
