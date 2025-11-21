package com.hk.chart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    // @ConfigurationProperties를 통해 설정값을 바인딩한 클래스를 주입
    private final KisApiConfig kisApiConfig;

    /**
     * 한국투자증권 API 통신을 위한 WebClient Bean 등록
     * Base URL 설정 및 기본 Content-Type 설정
     */
    @Bean
    public WebClient kisWebClient() {
        return WebClient.builder()
                // application.properties에서 읽은 Base URL 설정
                .baseUrl(kisApiConfig.getBaseUrl()) 
                // 모든 요청에 기본 Content-Type: application/json 설정
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // API 호출 시 디버깅을 위한 로깅 필터 추가 (옵션, 개발 시 유용)
                .filter(logRequest()) 
                .build();
    }
    
    // WebClient 요청 정보를 콘솔에 출력하는 필터 함수 (디버깅 목적)
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("--- KIS API Request ---");
            System.out.println("Method: " + clientRequest.method());
            System.out.println("URL: " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> {
                if (!name.equalsIgnoreCase(HttpHeaders.AUTHORIZATION)) { // 토큰은 보안상 숨김
                    values.forEach(value -> System.out.println("Header: " + name + " = " + value));
                }
            });
            System.out.println("-----------------------");
            return Mono.just(clientRequest);
        });
    }
}
