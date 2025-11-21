package com.hk.chart.service;

import com.hk.chart.dto.CandlePatternType;
import com.hk.chart.entity.StockCandle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatternAnalysisService {

    @lombok.Getter
    @lombok.Builder
    public static class AnalysisResult {
        private CandlePatternType type;
        private String date;
    }

    public List<AnalysisResult> analyzeAll(List<StockCandle> candles) {
    	List<AnalysisResult> foundPatterns = new ArrayList<>();
        // 추세 확인을 위해 최소 10일치 데이터는 있어야 안전합니다.
        if (candles == null || candles.size() < 10) return foundPatterns;

        int n = candles.size();
        StockCandle d0 = candles.get(n - 1); // 오늘
        StockCandle d1 = candles.get(n - 2); // 어제
        StockCandle d2 = candles.get(n - 3); // 엊그제

        // ⭐️ [추세 판단] 최근 5일~10일 데이터로 추세 확인
        boolean isDown = isDownTrend(candles, n - 1); // 하락 추세인가?
        boolean isUp = isUpTrend(candles, n - 1);     // 상승 추세인가?

        // === 1. 상승 반전형 (조건: 기존이 하락 추세여야 함) ===
        if (isDown) {
            if (isHammer(d0)) add(foundPatterns, CandlePatternType.HAMMER, d0);
            if (isInvertedHammer(d0)) add(foundPatterns, CandlePatternType.INVERTED_HAMMER, d0);
            if (isEngulfing(d1, d0, true)) add(foundPatterns, CandlePatternType.BULLISH_ENGULFING, d0);
            if (isMorningStar(d2, d1, d0)) add(foundPatterns, CandlePatternType.MORNING_STAR, d0);
            if (isPiercingLine(d1, d0)) add(foundPatterns, CandlePatternType.PIERCING_LINE, d0);
            if (isHarami(d1, d0, true)) add(foundPatterns, CandlePatternType.BULLISH_HARAMI, d0);
            if (isKicker(d1, d0, true)) add(foundPatterns, CandlePatternType.BULLISH_KICKER, d0);
            // 적삼병은 바닥권 탈출 신호이므로 하락세 끝자락에서 유효
            if (isThreeWhiteSoldiers(d2, d1, d0)) add(foundPatterns, CandlePatternType.THREE_WHITE_SOLDIERS, d0);
        }

        // === 2. 하락 반전형 (조건: 기존이 상승 추세여야 함) ===
        if (isUp) {
            if (isShootingStar(d0)) add(foundPatterns, CandlePatternType.SHOOTING_STAR, d0);
            if (isHangingMan(d0)) add(foundPatterns, CandlePatternType.HANGING_MAN, d0); // 모양은 망치형과 같으나 상승추세일 때
            if (isEngulfing(d1, d0, false)) add(foundPatterns, CandlePatternType.BEARISH_ENGULFING, d0);
            if (isEveningStar(d2, d1, d0)) add(foundPatterns, CandlePatternType.EVENING_STAR, d0);
            if (isDarkCloudCover(d1, d0)) add(foundPatterns, CandlePatternType.DARK_CLOUD_COVER, d0);
            if (isHarami(d1, d0, false)) add(foundPatterns, CandlePatternType.BEARISH_HARAMI, d0);
            if (isKicker(d1, d0, false)) add(foundPatterns, CandlePatternType.BEARISH_KICKER, d0);
            if (isThreeBlackCrows(d2, d1, d0)) add(foundPatterns, CandlePatternType.THREE_BLACK_CROWS, d0);
        }

        // === 3. 중립/지속형 (추세 무관하거나 자체 의미가 강함) ===
        if (isDoji(d0)) add(foundPatterns, CandlePatternType.DOJI, d0);
        if (isSpinningTop(d0)) add(foundPatterns, CandlePatternType.SPINNING_TOP, d0);
        
        // 마루보주는 추세 강화 신호이므로 추세와 방향이 같으면 추가
        if (isMarubozu(d0) && d0.getClose() > d0.getOpen()) add(foundPatterns, CandlePatternType.WHITE_MARUBOZU, d0);
        if (isMarubozu(d0) && d0.getClose() < d0.getOpen()) add(foundPatterns, CandlePatternType.BLACK_MARUBOZU, d0);

        return foundPatterns;
    }

    private void add(List<AnalysisResult> list, CandlePatternType type, StockCandle c) {
        list.add(AnalysisResult.builder().type(type).date(c.getDate()).build());
    }

    // === 🛠️ 계산 로직 (Helpers) ===

    private double body(StockCandle c) { return Math.abs(c.getClose() - c.getOpen()); }
    private double range(StockCandle c) { return c.getHigh() - c.getLow(); }
    private boolean isBull(StockCandle c) { return c.getClose() > c.getOpen(); }
    private boolean isBear(StockCandle c) { return c.getClose() < c.getOpen(); }

    // 1. 도지 (몸통이 전체 길이의 3% 미만)
    private boolean isDoji(StockCandle c) { return range(c) > 0 && body(c) / range(c) < 0.03; }

    // 2. 마루보주 (꼬리가 거의 없음)
    private boolean isMarubozu(StockCandle c) {
        return range(c) > 0 && body(c) / range(c) > 0.9;
    }

    // 3. 팽이형 (몸통 작고 꼬리 긺)
    private boolean isSpinningTop(StockCandle c) {
        return range(c) > 0 && body(c) / range(c) < 0.3 && !isDoji(c);
    }

    // 4. 망치/교수형 (아래꼬리 2배 이상, 위꼬리 작음)
    private boolean isHammerShape(StockCandle c) {
        double lowerTail = Math.min(c.getOpen(), c.getClose()) - c.getLow();
        double upperTail = c.getHigh() - Math.max(c.getOpen(), c.getClose());
        return lowerTail >= body(c) * 2 && upperTail <= body(c) * 0.5;
    }
    private boolean isHammer(StockCandle c) { return isHammerShape(c); } // 보통 하락추세 확인 필요하나 여기선 모양만
    private boolean isHangingMan(StockCandle c) { return isHammerShape(c); }

    // 5. 역망치/유성형 (위꼬리 2배 이상, 아래꼬리 작음)
    private boolean isInvertedHammerShape(StockCandle c) {
        double lowerTail = Math.min(c.getOpen(), c.getClose()) - c.getLow();
        double upperTail = c.getHigh() - Math.max(c.getOpen(), c.getClose());
        return upperTail >= body(c) * 2 && lowerTail <= body(c) * 0.5;
    }
    private boolean isInvertedHammer(StockCandle c) { return isInvertedHammerShape(c); }
    private boolean isShootingStar(StockCandle c) { return isInvertedHammerShape(c); }

    // 6. 장악형 (Engulfing)
    private boolean isEngulfing(StockCandle d1, StockCandle d0, boolean bullish) {
        if (bullish) {
            return isBear(d1) && isBull(d0) && d0.getClose() > d1.getOpen() && d0.getOpen() < d1.getClose();
        } else {
            return isBull(d1) && isBear(d0) && d0.getOpen() > d1.getClose() && d0.getClose() < d1.getOpen();
        }
    }

    // 7. 잉태형 (Harami)
    private boolean isHarami(StockCandle d1, StockCandle d0, boolean bullish) {
        boolean inside = d0.getHigh() < d1.getHigh() && d0.getLow() > d1.getLow(); // d0가 d1 안에 포함
        if (bullish) return isBear(d1) && isBull(d0) && inside;
        else return isBull(d1) && isBear(d0) && inside;
    }

    // 8. 관통형 (Piercing Line)
    private boolean isPiercingLine(StockCandle d1, StockCandle d0) {
        return isBear(d1) && isBull(d0) 
                && d0.getOpen() < d1.getLow() // 갭하락 시작
                && d0.getClose() > (d1.getOpen() + d1.getClose()) / 2.0 // 몸통 절반 이상 회복
                && d0.getClose() < d1.getOpen(); // 전일 시가보단 아래
    }

    // 9. 흑운형 (Dark Cloud Cover)
    private boolean isDarkCloudCover(StockCandle d1, StockCandle d0) {
        return isBull(d1) && isBear(d0)
                && d0.getOpen() > d1.getHigh() // 갭상승 시작
                && d0.getClose() < (d1.getOpen() + d1.getClose()) / 2.0 // 몸통 절반 이하 침투
                && d0.getClose() > d1.getOpen(); // 전일 시가보단 위
    }

    // 10. 박차형 (Kicker) - 급격한 갭 반전
    private boolean isKicker(StockCandle d1, StockCandle d0, boolean bullish) {
        if (bullish) return isBear(d1) && isBull(d0) && d0.getLow() > d1.getHigh(); // 갭상승
        else return isBull(d1) && isBear(d0) && d0.getHigh() < d1.getLow(); // 갭하락
    }

    // 11. 샛별형 (Morning Star)
    private boolean isMorningStar(StockCandle d2, StockCandle d1, StockCandle d0) {
        boolean gapDown = Math.max(d1.getOpen(), d1.getClose()) < d2.getClose();
        boolean recovery = d0.getClose() > (d2.getOpen() + d2.getClose()) / 2.0;
        return isBear(d2) && gapDown && isBull(d0) && recovery;
    }

    // 12. 석별형 (Evening Star)
    private boolean isEveningStar(StockCandle d2, StockCandle d1, StockCandle d0) {
        boolean gapUp = Math.min(d1.getOpen(), d1.getClose()) > d2.getClose();
        boolean fall = d0.getClose() < (d2.getOpen() + d2.getClose()) / 2.0;
        return isBull(d2) && gapUp && isBear(d0) && fall;
    }

    // 13. 적삼병
    private boolean isThreeWhiteSoldiers(StockCandle d2, StockCandle d1, StockCandle d0) {
        return isBull(d2) && isBull(d1) && isBull(d0)
                && d1.getClose() > d2.getClose() && d0.getClose() > d1.getClose();
    }

    // 14. 흑삼병
    private boolean isThreeBlackCrows(StockCandle d2, StockCandle d1, StockCandle d0) {
        return isBear(d2) && isBear(d1) && isBear(d0)
                && d1.getClose() < d2.getClose() && d0.getClose() < d1.getClose();
    }
    
    //상승형 추세인지 확인
    private boolean isDownTrend(List<StockCandle> candles, int currentIndex) {
        if (currentIndex < 5) return false; // 데이터 부족
        
        // 간단 로직: 5일 전 종가보다 현재 시가가 낮으면 하락세로 간주
        // (더 정교하게 하려면 MA5 이동평균선이 하락 중인지 체크)
        double price5DaysAgo = candles.get(currentIndex - 5).getClose();
        double currentOpen = candles.get(currentIndex).getOpen();
        
        return currentOpen < price5DaysAgo;
    }
    
    //하락형 추세인지 확인
    private boolean isUpTrend(List<StockCandle> candles, int currentIndex) {
        if (currentIndex < 5) return false;
        
        double price5DaysAgo = candles.get(currentIndex - 5).getClose();
        double currentOpen = candles.get(currentIndex).getOpen();
        
        return currentOpen > price5DaysAgo;
    }
}