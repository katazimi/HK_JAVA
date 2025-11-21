package com.hk.chart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stock_info")
public class StockInfo {
    @Id
    private String code; // 종목코드 (PK, 예: 005930)
    
    private String name; // 종목명 (예: 삼성전자)
}