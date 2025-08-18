package hk.edu_20250801.day22;

public class D1_BaseballGameMain {

	public static void main(String[] args) {
		D1_BaseballGame game = new D1_BaseballGame();
		System.out.print("투수: ");
		game.print(game.bowler);
		System.out.println();
		
		while (true) {
			//3스트라이크일 경우
			if (game.isStrike(game.bowler, game.batter) == 3) {
				System.out.print("타자: ");
				game.print(game.batter);
				System.out.println("-> 3S 모두 일치!! 타자 승리!!");
				break;
			}
			//아웃일 경우
			else if (game.isOut(game.bowler, game.batter)) {
				System.out.print("타자: ");
				game.print(game.batter);
				System.out.println("-> out");
			}
			else {
				System.out.print("타자: ");
				game.print(game.batter);
				System.out.println("-> " + game.isStrike(game.bowler, game.batter) + "S " + game.isBall(game.bowler, game.batter) + "B");
			}
			game.makeRandom(game.batter);
		}
		

	}

}
