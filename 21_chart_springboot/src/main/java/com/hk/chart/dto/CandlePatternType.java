package com.hk.chart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CandlePatternType {
    // --- [강력] 상승 반전형 (Strong Bullish) ---
    BULLISH_ENGULFING("상승 장악형", "전일 음봉을 금일 양봉이 완전히 감싸며, 강력한 매수세 유입을 의미합니다.", "상승", 5),
    MORNING_STAR("샛별형", "하락세 끝에서 갭하락 후 양봉이 등장하여 추세가 반전됨을 알립니다.", "상승", 5),
    THREE_WHITE_SOLDIERS("적삼병", "세 개의 양봉이 연속적으로 고점을 높이며 강력한 상승 추세를 만듭니다.", "상승", 5),
    PIERCING_LINE("관통형", "전일 장대음봉의 중심선(50%) 이상으로 금일 양봉이 치고 올라옵니다.", "상승", 4),
    THREE_INSIDE_UP("상승 잉태 확인형", "하라미(잉태형) 패턴 발생 후 다음날 양봉이 상승을 확정 짓습니다.", "상승", 4),
    THREE_OUTSIDE_UP("상승 장악 확인형", "장악형 패턴 발생 후 다음날 양봉이 상승을 확정 짓습니다.", "상승", 4),

    // --- [약함/중간] 상승 반전형 (Weak/Mod Bullish) ---
    HAMMER("망치형", "하락 추세 바닥에서 긴 아래꼬리가 발생하여 매수세가 들어왔음을 암시합니다.", "상승", 3),
    INVERTED_HAMMER("역망치형", "바닥권에서 상승을 시도한 흔적입니다. 다음날 확인이 필요합니다.", "상승", 2),
    BULLISH_HARAMI("상승 잉태형 (Harami)", "긴 음봉 안에 작은 양봉이 갇힌 형태. 하락세가 멈추고 있습니다.", "상승", 2),
    BULLISH_KICKER("상승 박차형", "음봉 다음날 갭상승하여 장대양봉이 발생하는 급격한 반전 신호입니다.", "상승", 4),

    // --- [강력] 하락 반전형 (Strong Bearish) ---
    BEARISH_ENGULFING("하락 장악형", "전일 양봉을 금일 음봉이 완전히 감싸며, 강력한 매도세 출현을 의미합니다.", "하락", 5),
    EVENING_STAR("석별형", "상승세 끝에서 갭상승 후 음봉이 등장하여 추세가 하락 반전됨을 알립니다.", "하락", 5),
    THREE_BLACK_CROWS("흑삼병", "세 개의 음봉이 연속적으로 저점을 낮추며 강력한 하락 추세를 만듭니다.", "하락", 5),
    DARK_CLOUD_COVER("흑운형", "전일 장대양봉의 중심선(50%) 이하로 금일 음봉이 내려꽂습니다.", "하락", 4),
    THREE_INSIDE_DOWN("하락 잉태 확인형", "하라미(잉태형) 패턴 발생 후 다음날 음봉이 하락을 확정 짓습니다.", "하락", 4),
    THREE_OUTSIDE_DOWN("하락 장악 확인형", "장악형 패턴 발생 후 다음날 음봉이 하락을 확정 짓습니다.", "하락", 4),

    // --- [약함/중간] 하락 반전형 (Weak/Mod Bearish) ---
    SHOOTING_STAR("유성형", "고점에서 긴 위꼬리가 발생하여 매도세에 밀렸음을 암시합니다.", "하락", 3),
    HANGING_MAN("교수형", "고점에서 긴 아래꼬리가 발생. 매수세가 약화되고 있습니다.", "하락", 2),
    BEARISH_HARAMI("하락 잉태형 (Harami)", "긴 양봉 안에 작은 음봉이 갇힌 형태. 상승세가 멈추고 있습니다.", "하락", 2),
    BEARISH_KICKER("하락 박차형", "양봉 다음날 갭하락하여 장대음봉이 발생하는 급격한 하락 신호입니다.", "하락", 4),

    // --- 지속형/중립 (Continuation/Neutral) ---
    WHITE_MARUBOZU("장대 양봉 (Marubozu)", "위아래 꼬리가 없는 강력한 상승. 매수세가 시장을 지배했습니다.", "상승", 3),
    BLACK_MARUBOZU("장대 음봉 (Marubozu)", "위아래 꼬리가 없는 강력한 하락. 매도세가 시장을 지배했습니다.", "하락", 3),
    DOJI("도지형", "시가와 종가가 일치합니다. 매수와 매도 세력이 팽팽하게 맞서고 있습니다.", "중립", 1),
    SPINNING_TOP("팽이형", "몸통이 짧고 꼬리가 깁니다. 시장이 방향을 탐색 중입니다.", "중립", 1),
    
    NONE("분석 패턴 없음", "현재 식별된 특이 패턴이 없습니다.", "중립", 0);

    private final String name;
    private final String description;
    private final String trend;
    private final int reliability;
}