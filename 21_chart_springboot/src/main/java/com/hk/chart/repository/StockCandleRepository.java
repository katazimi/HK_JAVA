package com.hk.chart.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hk.chart.entity.StockCandle;

public interface StockCandleRepository extends JpaRepository<StockCandle, Long> {
    
	// 1. 중복 수집 방지용
    boolean existsByStockCodeAndDate(String stockCode, String date);
    
    // 2. 차트 초기 로딩용 (최신순 N개)
    @Query(value = "SELECT * FROM stock_candle WHERE stock_code = :stockCode ORDER BY date DESC LIMIT :limit", nativeQuery = true)
    List<StockCandle> findRecentCandles(@Param("stockCode") String stockCode, @Param("limit") int limit);

    // 3. 무한 스크롤 추가 로딩용 (특정 날짜 이전 데이터 N개)
    @Query(value = "SELECT * FROM stock_candle WHERE stock_code = :stockCode AND date < :lastDate ORDER BY date DESC LIMIT :limit", nativeQuery = true)
    List<StockCandle> findCandlesBeforeDate(@Param("stockCode") String stockCode, @Param("lastDate") String lastDate, @Param("limit") int limit);

    // 4. 스케줄러용: 관리 중인 모든 종목 코드 조회
    @Query("SELECT DISTINCT s.stockCode FROM StockCandle s")
    List<String> findAllStockCodes();

    // 5. 스케줄러용: 특정 종목의 마지막 저장 날짜 조회
    @Query("SELECT MAX(s.date) FROM StockCandle s WHERE s.stockCode = :stockCode")
    String findLatestDate(@Param("stockCode") String stockCode);

    // ⭐️ 6. [오류 해결] 이동평균선 계산용: 전체 데이터를 날짜 오름차순(과거->현재)으로 조회
    List<StockCandle> findAllByStockCodeOrderByDateAsc(String stockCode);
}
