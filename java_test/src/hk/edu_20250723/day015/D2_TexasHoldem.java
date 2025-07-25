package hk.edu_20250723.day015;

import java.util.ArrayList;
import java.util.List;

public class D2_TexasHoldem {
    // 카드 뭉치와 카드 나누기 도구
    private D2_CardCase cards = new D2_CardCase();
    private D2_Divide divide = new D2_Divide();

    // 플레이어와 테이블(공용 카드 저장용)
    public List<D2_Player> players;
    public D2_Player table;

    // 판의 점수 (턴이 지날수록 증가)
    public int potScore = 0;

    public D2_TexasHoldem() {
        players = new ArrayList<D2_Player>();
        table = new D2_Player();
        for (int i = 0; i < 2; i++)
            players.add(new D2_Player());
        dealingCards();
    }

    // 플레이어와 테이블에 카드 배분(플레이어: 2장씩, 테이블: 3장)
    public void dealingCards() {
        divide.divide(cards, players.get(0), 2);
        divide.divide(cards, players.get(1), 2);
        divide.divide(cards, table, 3);
        potScore = 1; // 플롭 공개 시 1점으로 시작
    }

    // 플롭(공용 카드 3장 공개)
    public void flop() {
        System.out.println("[Table]");
        for (D2_Card card : table.getCardList()) {
            System.out.print(card + " ");
        }
        System.out.println();
        potScore = 1;
    }

    // 턴(공용 카드 1장 추가)
    public void turn() {
        addCard();
        System.out.println("[Table]");
        for (D2_Card card : table.getCardList()) {
            System.out.print(card + " ");
        }
        System.out.println();
        potScore = 2;
    }

    // 리버(공용 카드 1장 추가)
    public void river() {
        addCard();
        System.out.println("[Table]");
        for (D2_Card card : table.getCardList()) {
            System.out.print(card + " ");
        }
        System.out.println();
        potScore = 3;
    }

    // 테이블 카드 한 장 추가
    public void addCard() {
        List<D2_Card> card = table.getCardList();
        card.add(cards.getCard());
        cards.removeCard();
    }

    // 족보를 확인하고 승자 결정 (int 타입 족보)
    // 승자에게 potScore 점수 부여, 무승부는 점수 없음
    public int match(D2_Player player1, D2_Player player2) {
        List<D2_Card> tableCards = table.getCardList();
        List<D2_Card> p1Hand = new ArrayList<>(player1.getCardList());
        List<D2_Card> p2Hand = new ArrayList<>(player2.getCardList());
        p1Hand.addAll(tableCards);
        p2Hand.addAll(tableCards);

        int rank1 = D2_PokerHandUtil.evaluate(p1Hand);
        int rank2 = D2_PokerHandUtil.evaluate(p2Hand);

        System.out.println("당신의 족보: " + handName(rank1));
        System.out.println("bot의 족보: " + handName(rank2));

        int winner;
        if (rank1 > rank2) {
            player1.setScore(player1.getScore() + potScore);
            System.out.println("사용자가 이번 판을 승리했습니다! +" + potScore + "점 획득");
            winner = 1;
        } else if (rank1 < rank2) {
            player2.setScore(player2.getScore() + potScore);
            System.out.println("Bot이 이번 판을 승리했습니다! +" + potScore + "점 획득");
            winner = 2;
        } else {
            // 동점일 때 하이카드 비교
            int max1 = getMaxCard(p1Hand);
            int max2 = getMaxCard(p2Hand);
            if (max1 > max2) {
                player1.setScore(player1.getScore() + potScore);
                System.out.println("사용자가 하이카드로 승리! +" + potScore + "점");
                winner = 1;
            } else if (max2 > max1) {
                player2.setScore(player2.getScore() + potScore);
                System.out.println("Bot이 하이카드로 승리! +" + potScore + "점");
                winner = 2;
            } else {
                System.out.println("무승부! 점수 없음.");
                winner = 0;
            }
        }
        return winner;
    }

    // die 기능: 현재 판 포기(점수 부여는 main에서 처리)
    public void die() {
        // 따로 로직 필요 없음. main에서 판별 후 점수 부여
    }

    // int 타입 족보 → 이름 변환
    public static String handName(int rank) {
        switch (rank) {
            case 9: return "스트레이트플러시";
            case 8: return "포카드";
            case 7: return "풀하우스";
            case 6: return "플러시";
            case 5: return "스트레이트";
            case 4: return "트리플";
            case 3: return "투페어";
            case 2: return "원페어";
            default: return "하이카드";
        }
    }

    // 하이카드 값 반환
    private int getMaxCard(List<D2_Card> cards) {
        int max = 0;
        for (D2_Card card : cards) {
            int v = D2_PokerHandUtil.getValue(card.getSTECK());
            max = Math.max(max, v);
        }
        return max;
    }
}