package com.hk.chart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stock_candle", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"stockCode", "date"}) // 종목+날짜는 유니크해야 함
})
public class StockCandle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockCode; // 종목코드 (005930)
    private String date;      // 날짜 (20241120)
    
    private Integer open;     // 시가
    private Integer high;     // 고가
    private Integer low;      // 저가
    private Integer close;    // 종가
    private Long volume;      // 거래량
    
    // ⭐️ 이동평균선 필드
    private Integer ma5;
    private Integer ma20;
    private Integer ma60;
    
    // ⭐️ Setter 추가 (계산 후 값을 넣기 위해)
    public void setMa(Integer ma5, Integer ma20, Integer ma60) {
        this.ma5 = ma5;
        this.ma20 = ma20;
        this.ma60 = ma60;
    }

    @Builder
    public StockCandle(String stockCode, String date, Integer open, Integer high, Integer low, Integer close, Long volume) {
        this.stockCode = stockCode;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}