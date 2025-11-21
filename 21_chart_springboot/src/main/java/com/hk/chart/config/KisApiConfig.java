package com.hk.chart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "kis")
@Getter
@Setter
public class KisApiConfig {

	private String appKey;
    private String appSecret;
    private String baseUrl;

    // 설정이 정상적으로 로드되었는지 확인하는 메서드 (테스트용)
    public void printConfig() {
        System.out.println("KIS Base URL: " + baseUrl);
    }
}
