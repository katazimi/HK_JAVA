package com.hk.chart.repository;

import com.hk.chart.entity.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockInfoRepository extends JpaRepository<StockInfo, String> {
    // 종목명 또는 코드로 검색 (SQL의 LIKE %keyword% 기능)
    List<StockInfo> findByNameContainingOrCodeContaining(String name, String code);
}