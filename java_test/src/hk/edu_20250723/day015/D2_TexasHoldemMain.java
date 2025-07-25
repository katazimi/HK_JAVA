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

            // 사용자 카드 출력
            System.out.print("[Your Cards] ");
            for (D2_Card card : user.getCardList()) System.out.print(card + " ");
            System.out.println();

            boolean userAlive = true;
            boolean botAlive = true;

            // 1. Flop
            System.out.print("[Table] ");
            for (D2_Card card : game.table.getCardList()) System.out.print(card + " ");
            System.out.println();

            // 사용자 베팅 여부
            userAlive = askUserBet(sc, "플롭(Flop)에서 베팅하시겠습니까? (bet/die): ");
            if (!userAlive) {
                bot.setScore(bot.getScore() + game.potScore);
                System.out.println("당신이 다이! Bot이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            // Bot의 결정
            botAlive = Math.random() < 0.8;
            if (!botAlive) {
                user.setScore(user.getScore() + game.potScore);
                System.out.println("Bot이 다이! 당신이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            // 턴 진행
            game.turn();

            // 2. Turn
//            System.out.print("[Table] ");
//            for (D2_Card card : game.table.getCardList()) System.out.print(card + " ");
//            System.out.println();

            userAlive = askUserBet(sc, "턴(Turn)에서 베팅하시겠습니까? (bet/die): ");
            if (!userAlive) {
                bot.setScore(bot.getScore() + game.potScore);
                System.out.println("당신이 다이! Bot이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            botAlive = Math.random() < 0.8;
            if (!botAlive) {
                user.setScore(user.getScore() + game.potScore);
                System.out.println("Bot이 다이! 당신이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            // 리버 진행
            game.river();

            // 3. River
//            System.out.print("[Table] ");
//            for (D2_Card card : game.table.getCardList()) System.out.print(card + " ");
//            System.out.println();

            userAlive = askUserBet(sc, "리버(River)에서 베팅하시겠습니까? (bet/die): ");
            if (!userAlive) {
                bot.setScore(bot.getScore() + game.potScore);
                System.out.println("당신이 다이! Bot이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            botAlive = Math.random() < 0.8;
            if (!botAlive) {
                user.setScore(user.getScore() + game.potScore);
                System.out.println("Bot이 다이! 당신이 +" + game.potScore + "점 획득\n");
                showScore(user, bot);
                if (isGameEnd(user, bot)) break;
                round++;
                continue;
            }
            // 모두 끝까지 살아남으면 match로 승부
            game.match(user, bot);
            showScore(user, bot);
            if (isGameEnd(user, bot)) break;
            round++;
        }
        sc.close();
    }

    private static boolean askUserBet(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String userAction = sc.next().trim().toLowerCase();
            if (userAction.equals("bet")) return true;
            if (userAction.equals("die")) return false;
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    private static void showScore(D2_Player user, D2_Player bot) {
        System.out.println("|현재 점수 - 사용자: " + user.getScore() + " / Bot: " + bot.getScore() + "|\n");
    }

    private static boolean isGameEnd(D2_Player user, D2_Player bot) {
        if (user.getScore() >= 10) {
            System.out.println("축하합니다! 사용자가 승리했습니다!");
            return true;
        }
        if (bot.getScore() >= 10) {
            System.out.println("Bot이 승리했습니다. 아쉽네요!");
            return true;
        }
        return false;
    }
}