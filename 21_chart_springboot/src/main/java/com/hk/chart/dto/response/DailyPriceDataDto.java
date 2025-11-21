package com.hk.chart.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyPriceDataDto {
    private String stck_bsop_date; // 날짜
    private String stck_clpr;      // 종가
    private String stck_oprc;      // 시가
    private String stck_hgpr;      // 고가
    private String stck_lwpr;      // 저가
    
    // ⭐️ [수정] API마다 거래량 키값이 다를 수 있어 Alias(별칭) 처리
    // acc_trd_qty: 주식 현재가 일자별 API용
    // acml_vol: 국내주식 기간별 시세 API용
    @JsonAlias({"acc_trd_qty", "acml_vol"}) 
    private String acc_trd_qty; 
}