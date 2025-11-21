package com.hk.chart.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // 요청 객체를 쉽게 생성하기 위해 Builder 사용
public class TokenRequestDto {
    // grant_type은 "client_credentials"로 고정
    private final String grant_type = "client_credentials";
    private final String appkey;
    private final String appsecret;
}