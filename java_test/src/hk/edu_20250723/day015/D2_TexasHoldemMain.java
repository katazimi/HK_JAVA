package hk.edu_20250723.day015;

import java.util.Scanner;

public class D2_TexasHoldemMain {
	
	public static D2_CardCase cardCase = new D2_CardCase();
	public static D2_TexasHoldem th = new D2_TexasHoldem();

	public static void main(String[] args) {
		boolean die = false;
		while (th.players.get(0).getScore() < 10 && th.players.get(1).getScore() < 10) {
			gameStart();
			turn();
			river();
			th.match();
		}
	}
	
	public static void gameStart() {
		System.out.println("게임을 시작합니다.\t\t" + "|Score " + th.players.get(0).getScore() + ":" + th.players.get(1).getScore()+"|");
		System.out.println("===================================");
		th.flop();
		System.out.println("===================================");
		System.out.println("[자신의 패]\n"+th.players.get(0).getCardList());
		System.out.println("===================================");
		betting();
	}
	
	public static void turn() {
		System.out.println("===================================");
		th.turn();
		System.out.println("===================================");
		betting();
	}
	
	public static void river() {
		System.out.println("===================================");
		th.river();
		System.out.println("===================================");
		betting();
	}
	
	public static void betting() {
		System.out.print("베팅하시겠습니까? [y|n]");
		Scanner sc = new Scanner(System.in);
		String bet = sc.nextLine();
		
		if (bet == "n")
			th.die();
	}
}
