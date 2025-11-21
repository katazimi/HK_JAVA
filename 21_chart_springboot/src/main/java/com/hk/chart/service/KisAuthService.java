package com.hk.chart.service;

import com.hk.chart.config.KisApiConfig;
import com.hk.chart.dto.request.TokenRequestDto;
import com.hk.chart.dto.response.TokenResponseDto;
import com.hk.chart.entity.KisToken; // Entity import
import com.hk.chart.repository.KisTokenRepository; // Repository import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.transaction.annotation.Transactional; // DB 쓰기 작업을 위한 트랜잭션
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KisAuthService {

	private final WebClient kisWebClient;
    private final KisApiConfig kisApiConfig;
    private final KisTokenRepository kisTokenRepository; // ⭐️ Repository 주입

    /**
     * DB에서 유효한 토큰을 확인하고, 만료되었거나 없으면 새로 발급받아 DB를 갱신합니다.
     */
    @Transactional // DB 쓰기 작업이 포함되므로 트랜잭션 적용
    public String getAccessToken() {
        // 1. DB에서 토큰 레코드 (ID=1) 조회
        Optional<KisToken> optionalToken = kisTokenRepository.findById(1L);

        if (optionalToken.isPresent()) {
            KisToken token = optionalToken.get();
            
            // 2. 토큰 만료 여부 확인
            if (!token.isExpired()) {
                // 3. 만료되지 않았다면 기존 토큰 반환
                System.out.println(">>> [Token] DB 캐시 토큰 사용. 만료 시각: " + token.getExpiredAt());
                return token.getAccessToken();
            } else {
                // 4. 만료된 경우: 새로 발급 요청
                System.out.println(">>> [Token] 만료됨. 새로운 토큰 재발급 요청...");
                return issueAndSaveToken(token);
            }
        } else {
            // 5. DB에 레코드가 없는 경우: 최초 발급 요청 (ID=1로 저장)
            System.out.println(">>> [Token] DB 레코드 없음. 최초 토큰 발급 요청...");
            return issueAndSaveToken(null);
        }
    }
    
    /**
     * KIS API에 토큰 발급을 요청하고 DB에 저장/업데이트합니다.
     */
    private String issueAndSaveToken(KisToken existingToken) {
        TokenResponseDto response = issueToken(); // KIS API 호출
        String newAccessToken = response.getAccess_token();
        int expiresIn = response.getExpires_in();

        if (existingToken != null) {
            // 기존 레코드 갱신
            existingToken.updateToken(newAccessToken, expiresIn); 
            kisTokenRepository.save(existingToken);
        } else {
            // 새 레코드 생성 (ID=1로 저장됨)
            KisToken newToken = new KisToken();
            newToken.updateToken(newAccessToken, expiresIn);
            kisTokenRepository.save(newToken);
        }
        
        System.out.println(">>> [Token] 토큰 발급 및 DB 갱신 완료.");
        return newAccessToken;
    }
    /**
     * KIS API 서버에 토큰 발급을 요청하는 실제 통신 메서드
     */
    private TokenResponseDto issueToken() {
        // 1. 요청 본문(Body) 생성
        TokenRequestDto requestBody = TokenRequestDto.builder()
                .appkey(kisApiConfig.getAppKey())
                .appsecret(kisApiConfig.getAppSecret())
                .build();

        // 2. WebClient를 사용하여 POST 요청 실행
        return kisWebClient.post()
                .uri("/oauth2/tokenP") // 토큰 발급 경로
                .bodyValue(requestBody) // 요청 본문 (JSON 자동 변환)
                .retrieve()
                .bodyToMono(TokenResponseDto.class) // 응답 DTO로 매핑
                .block(); // 동기식으로 처리 (테스트용)
    }
}
