package com.hk.chart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.chart.entity.KisToken;

public interface KisTokenRepository extends JpaRepository<KisToken, Long> {
	
}
