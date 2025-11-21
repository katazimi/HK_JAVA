package com.hk.chart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor // JPA는 기본 생성자를 요구합니다.
@Table(name = "kis_token_cache") // 테이블명
public class KisToken {
    
    // KIS 토큰은 하나만 관리하므로 ID를 1로 고정하여 관리합니다.
    @Id
    private final Long id = 1L; 
    @Column(length = 1024)
    private String accessToken;
    private LocalDateTime expiredAt; // 토큰 만료 시각

    // 갱신 메서드: 서비스 레이어에서 호출하여 DB 데이터를 업데이트합니다.
    public void updateToken(String newAccessToken, int expiresIn) {
        this.accessToken = newAccessToken;
        // KIS API 만료 시간(초)을 LocalDateTime으로 변환하여 저장
        // 안전을 위해 5분 정도 짧게 만료 시각을 설정합니다.
        this.expiredAt = LocalDateTime.now().plusSeconds(expiresIn).minusMinutes(5); 
    }
    
    // 만료 여부 확인 로직
    public boolean isExpired() {
        // 현재 시각이 만료 시각을 지났다면 true 반환
        return LocalDateTime.now().isAfter(this.expiredAt);
    }
}