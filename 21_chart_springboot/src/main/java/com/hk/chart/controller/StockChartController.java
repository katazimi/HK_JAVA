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
import com.hk.chart.service.PatternAnalysisService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StockChartController {
    
    private final KisMarketService marketService;
    private final StockCandleRepository candleRepository;
    private final StockInfoRepository stockInfoRepository;
    private final PatternAnalysisService patternService;

    // 1. [관리자용] 데이터 수집 트리거
    @GetMapping("/api/collect/{code}")
    @ResponseBody
    public String collectData(@PathVariable String code) {
        new Thread(() -> { 
            marketService.collectAllHistory(code);
        }).start();
        return "데이터 수집을 백그라운드에서 시작했습니다. 콘솔 로그를 확인하세요.";
    }
    
    @GetMapping("/api/stock/search")
    @ResponseBody
    public List<StockInfo> searchStock(@RequestParam String keyword) {
        return stockInfoRepository.findByNameContainingOrCodeContaining(keyword, keyword);
    }

    // 2. 차트 데이터 API (일/주/월 지원)
    @GetMapping("/api/stock/{code}/candle-data")
    @ResponseBody
    public List<CandleDataDto> getStockCandleData(
            @PathVariable String code,
            @RequestParam(required = false) String lastDate,
            @RequestParam(required = false, defaultValue = "D") String type) { // ⭐️ type 추가 (기본값 D)
        
        List<StockCandle> entities;
        int limit = 500;

        try {
            if ("D".equals(type)) {
                // --- [일봉] 기존 로직 (무한 스크롤 지원) ---
                if (lastDate == null || lastDate.isEmpty()) {
                    entities = candleRepository.findRecentCandles(code, limit);
                } else {
                    String dbDate = lastDate.replace("-", "").trim();
                    entities = candleRepository.findCandlesBeforeDate(code, dbDate, limit);
                }
                
                // DB에서 최신순(DESC)으로 가져오므로 과거순(ASC)으로 뒤집기
                if (entities != null) {
                    Collections.reverse(entities);
                }
            } else {
                // --- [주봉/월봉] 서비스 집계 로직 사용 ---
                // (주봉/월봉은 무한 스크롤 없이 최근 데이터 기준으로 집계해서 반환)
                entities = marketService.getCandleDataByPeriod(code, type, limit);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

        if (entities == null) return List.of();

        // DTO 변환
        return entities.stream()
                .map(e -> {
                    String rawDate = e.getDate();
                    // 날짜 포맷 (YYYYMMDD -> YYYY-MM-DD)
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
    
    @GetMapping("/chart")
    public String chartView() {
        return "chart";
    }
    
    // 3. 패턴 분석 API (일/주/월 지원)
    @GetMapping("/api/stock/{code}/analysis")
    @ResponseBody
    public List<PatternAnalysisService.AnalysisResult> getStockAnalysis(
            @PathVariable String code,
            @RequestParam(required = false, defaultValue = "D") String type) { // ⭐️ type 추가
        
        // 분석을 위해 해당 주기(일/주/월)의 최근 20개 데이터를 가져옴
        List<StockCandle> candles = marketService.getCandleDataByPeriod(code, type, 20);
        
        // Service가 이미 날짜 오름차순(ASC)으로 주므로 바로 분석
        return patternService.analyzeAll(candles);
    }
}