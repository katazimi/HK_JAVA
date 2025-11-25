package com.hk.chart.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class CandleDataDto {
    private String x; // 날짜 (X축)
    private List<Integer> y; // [시가, 고가, 저가, 종가] (Y축 데이터 배열)
    
    // ⭐️ 보조지표 데이터
    private Integer ma5;
    private Integer ma20;
    private Integer ma60;
    
    private Long volume;     //거래량
}
