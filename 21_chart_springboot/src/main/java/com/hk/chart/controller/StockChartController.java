package com.hk.chart.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.chart.dto.CandleDataDto;
import com.hk.chart.entity.StockCandle;
import com.hk.chart.entity.StockInfo;
import com.hk.chart.repository.StockCandleRepository;
import com.hk.chart.repository.StockInfoRepository;
import com.hk.chart.service.KisMarketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StockChartController {
    
    private final KisMarketService marketService;
    private final StockCandleRepository candleRepository;
    private final StockInfoRepository stockInfoRepository;

    // 1. [관리자용] 데이터 수집 트리거 (브라우저에서 한번 호출해주면 됨)
    // 예: http://localhost:8080/api/collect/005930
    @GetMapping("/api/collect/{code}")
    @ResponseBody
    public String collectData(@PathVariable String code) {
        new Thread(() -> { // 오래 걸리므로 별도 스레드에서 실행
            marketService.collectAllHistory(code);
        }).start();
        return "데이터 수집을 백그라운드에서 시작했습니다. 콘솔 로그를 확인하세요.";
    }
    
    @GetMapping("/api/stock/search")
    @ResponseBody
    public List<StockInfo> searchStock(@RequestParam String keyword) {
        return stockInfoRepository.findByNameContainingOrCodeContaining(keyword, keyword);
    }

    // 2. 차트 데이터 (이제 DB에서 가져옴!)
    @GetMapping("/api/stock/{code}/candle-data") // URL 변경됨!
    @ResponseBody
    public List<CandleDataDto> getStockCandleData(
            @PathVariable String code,  // URL에서 종목코드 받음
            @RequestParam(required = false) String lastDate) {
        
        List<StockCandle> entities;
        int limit = 500;

        try {
            if (lastDate == null || lastDate.isEmpty()) {
                entities = candleRepository.findRecentCandles(code, limit); // code 변수 사용
            } else {
                String dbDate = lastDate.replace("-", "").trim();
                entities = candleRepository.findCandlesBeforeDate(code, dbDate, limit); // code 변수 사용
            }
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

        if (entities == null) return List.of();

        Collections.reverse(entities);

        return entities.stream()
                .map(e -> {
                    String rawDate = e.getDate();
                    String formattedDate = rawDate.substring(0, 4) + "-" + 
                                         rawDate.substring(4, 6) + "-" + 
                                         rawDate.substring(6, 8);
                    return CandleDataDto.builder()
                        .x(formattedDate)
                        .y(List.of(e.getOpen(), e.getHigh(), e.getLow(), e.getClose()))
                        .ma5(e.getMa5())
                        .ma20(e.getMa20())
                        .ma60(e.getMa60())
                        .volume(e.getVolume()) 
                        .build();
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/chart") // ⭐️ 주소 매핑 확인
    public String chartView() {
        return "chart"; // templates/chart.html 을 찾음
    }
}