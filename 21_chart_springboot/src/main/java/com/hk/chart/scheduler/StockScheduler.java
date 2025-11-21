package com.hk.chart.scheduler;

import com.hk.chart.repository.StockCandleRepository;
import com.hk.chart.service.KisMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockScheduler {

    private final KisMarketService marketService;
    private final StockCandleRepository candleRepository;

    /**
     * 매일 평일(월~금) 오후 3시 40분(15:40)에 실행
     * Cron 표현식: 초 분 시 일 월 요일
     */
    @Scheduled(cron = "0 40 15 * * MON-FRI")
    public void dailyStockUpdate() {
        System.out.println("======================================");
        System.out.println(">>> [Scheduler] 일별 주가 데이터 업데이트 시작");
        
        // 1. 관리 대상 종목(DB에 데이터가 있는 종목) 리스트 가져오기
        List<String> watchedStocks = candleRepository.findAllStockCodes();
        System.out.println(">>> 대상 종목 수: " + watchedStocks.size() + "개");

        // 2. 각 종목별로 업데이트 실행
        for (String stockCode : watchedStocks) {
            try {
                marketService.updateStockData(stockCode);
                
                // API 호출 제한(초당 횟수) 고려하여 0.5초 대기
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.err.println(">>> 스케줄링 오류 (" + stockCode + "): " + e.getMessage());
            }
        }
        
        System.out.println(">>> [Scheduler] 업데이트 작업 종료");
        System.out.println("======================================");
    }
}