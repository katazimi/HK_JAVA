package hk.edu_20250723.day015;

import java.util.Scanner;

public class D2_TexasHoldemMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int round = 1;

        while (true) {
            System.out.println("=== ROUND " + round + " ===");
            D2_TexasHoldem game = new D2_TexasHoldem();

            D2_Player user = game.players.get(0);
            D2_Player bot = game.players.get(1);

            // 카드 및 테이블 출력
            System.out.print("[User Cards] ");
            for (D2_Card card : user.getCardList()) System.out.print(card + " ");
            System.out.println();

            System.out.print("[Bot Cards] ");
            for (D2_Card card : bot.getCardList()) System.out.print(card + " ");
            System.out.println();

            // 베팅/다이 결정
            String userAction;
            while (true) {
                System.out.print("베팅하시겠습니까? (bet/die): ");
                userAction = sc.next().trim().toLowerCase();
                if (userAction.equals("bet") || userAction.equals("die")) break;
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
            String botAction = Math.random() < 0.8 ? "bet" : "die";
            System.out.println("Bot의 선택: " + botAction);

            // 둘 중 하나라도 die면, 상대가 점수 획득 (판 점수는 flop(1)+turn(1)+river(1) = 3점)
            boolean userAlive = userAction.equals("bet");
            boolean botAlive = botAction.equals("bet");

            if (!userAlive && botAlive) {
                bot.setScore(bot.getScore() + game.potScore);
                System.out.println("사용자가 다이! Bot이 +" + game.potScore + "점 획득");
            } else if (userAlive && !botAlive) {
                user.setScore(user.getScore() + game.potScore);
                System.out.println("Bot이 다이! 사용자가 +" + game.potScore + "점 획득");
            } else if (!userAlive && !botAlive) {
                System.out.println("둘 다 다이! 점수 없음.");
            } else {
                // 둘 다 베팅 참여: 턴 진행 후 match()에서 승자 결정
                game.flop();
                game.turn();
                game.river();
                game.match(user, bot);
            }

            // 점수 출력
            System.out.println("현재 점수 - 사용자: " + user.getScore() + " / Bot: " + bot.getScore());

            // 승리 조건 체크
            if (user.getScore() >= 10) {
                System.out.println("축하합니다! 사용자가 승리했습니다!");
                break;
            }
            if (bot.getScore() >= 10) {
                System.out.println("Bot이 승리했습니다. 아쉽네요!");
                break;
            }
            round++;
            System.out.println();
        }
        sc.close();
    }
}