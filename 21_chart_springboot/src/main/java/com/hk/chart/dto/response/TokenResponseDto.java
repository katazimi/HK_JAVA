package com.hk.chart.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private String access_token; // 실제 접근 토큰
    private String token_type;   // 토큰 타입 (Bearer)
    private int expires_in;      // 만료 시간 (초 단위)
    private String access_token_token_issued_path; // 발급 경로 (사용 안 함)
}
