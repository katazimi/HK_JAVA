// src/main/java/com/hk/chart/config/DataInitializer.java

package com.hk.chart.config;

import com.hk.chart.repository.StockInfoRepository;
import com.hk.chart.service.KisMasterService; // 서비스 임포트
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StockInfoRepository stockInfoRepository;
    private final KisMasterService kisMasterService; // 서비스 주입

    @Override
    public void run(String... args) throws Exception {
        // DB가 비어있을 때만 마스터 파일 다운로드 및 동기화 실행
        if (stockInfoRepository.count() == 0) {
            System.out.println(">>> [Init] DB가 비어있습니다. KIS 서버에서 종목 정보를 다운로드합니다.");
            kisMasterService.syncStockMasterData();
        } else {
            System.out.println(">>> [Init] 종목 데이터가 이미 존재합니다. (건수: " + stockInfoRepository.count() + ")");
        }
    }
}