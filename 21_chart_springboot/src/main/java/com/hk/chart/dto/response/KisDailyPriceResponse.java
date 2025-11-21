package com.hk.chart.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class KisDailyPriceResponse {
    private String rt_cd;
    private String msg_cd;
    private String msg1;

    // 기존 API (inquire-daily-price)용 출력
    private List<DailyPriceDataDto> output;

    // ⭐️ 추가: 기간별 시세 조회 API (inquire-daily-itemchartprice)용 출력
    private List<DailyPriceDataDto> output2; 
}