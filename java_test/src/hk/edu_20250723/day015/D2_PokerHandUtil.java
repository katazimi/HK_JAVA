package hk.edu_20250723.day015;

import java.util.*;

public class D2_PokerHandUtil {
    // 족보 등급 int (높을수록 강함)
    // 1: 하이카드, 2: 원페어, 3: 투페어, 4: 트리플, 5: 스트레이트,
    // 6: 플러시, 7: 풀하우스, 8: 포카드, 9: 스트레이트플러시
    public static int evaluate(List<D2_Card> cards) {
        List<Integer> values = new ArrayList<>();
        Map<String, Integer> suits = new HashMap<>();
        Map<Integer, Integer> valueCount = new HashMap<>();
        for (D2_Card card : cards) {
            int v = getValue(card.getSTECK());
            values.add(v);
            suits.put(card.getDECK(), suits.getOrDefault(card.getDECK(), 0) + 1);
            valueCount.put(v, valueCount.getOrDefault(v, 0) + 1);
        }
        Collections.sort(values);

        boolean isFlush = suits.values().stream().anyMatch(cnt -> cnt >= 5);
        boolean isStraight = isStraight(values);

        if (isFlush && isStraight && isStraightFlush(cards)) return 9; // 스트레이트 플러시
        if (valueCount.containsValue(4)) return 8; // 포카드
        if (valueCount.containsValue(3) && valueCount.containsValue(2)) return 7; // 풀하우스
        if (isFlush) return 6; // 플러시
        if (isStraight) return 5; // 스트레이트
        if (valueCount.containsValue(3)) return 4; // 트리플
        if (valueCount.values().stream().filter(cnt -> cnt == 2).count() >= 2) return 3; // 투페어
        if (valueCount.containsValue(2)) return 2; // 원페어
        return 1; // 하이카드
    }

    // 카드 숫자값 변환 (A:14, K:13, Q:12, J:11, T:10, 2~9:숫자)
    public static int getValue(String steck) {
        switch (steck) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            case "T": return 10;
            default: return Integer.parseInt(steck);
        }
    }
    //스트레이트 확인
    private static boolean isStraight(List<Integer> values) {
        Set<Integer> set = new HashSet<>(values);
        List<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        if (sorted.contains(14)) sorted.add(0, 1); // A가 1로도 사용 가능
        for (int i = 0; i <= sorted.size() - 5; i++) {
            boolean straight = true;
            for (int j = 0; j < 4; j++) {
                if (sorted.get(i + j) + 1 != sorted.get(i + j + 1)) {
                    straight = false;
                    break;
                }
            }
            if (straight) return true;
        }
        return false;
    }
    //스트레이트 플러시 확인
    private static boolean isStraightFlush(List<D2_Card> cards) {
        Map<String, List<Integer>> suitToValues = new HashMap<>();
        for (D2_Card card : cards) {
            String suit = card.getDECK();
            int value = getValue(card.getSTECK());
            suitToValues.computeIfAbsent(suit, k -> new ArrayList<>()).add(value);
        }
        for (List<Integer> vals : suitToValues.values()) {
            if (vals.size() >= 5 && isStraight(vals)) return true;
        }
        return false;
    }
}