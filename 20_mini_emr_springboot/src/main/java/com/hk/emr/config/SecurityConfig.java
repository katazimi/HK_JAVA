package com.hk.emr.config; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // A. 인증(로그인) 없이 접근 허용할 경로
                .requestMatchers("/", "/member/login", "/login", "/user/logout", "/css/**", "/js/**").permitAll()
                // (참고: /admin/idChk 등 AJAX API도 열어줘야 할 수 있음)
                
                // B. 역할(Role) 기반 접근 제어
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/doctor/**").hasRole("DOCTOR")
                .requestMatchers("/staff/**").hasRole("STAFF")
                
                // C. 나머지 모든 요청은 인증(로그인)을 받아야 함
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // D. 사용자 정의 로그인 페이지 경로
                .loginPage("/member/login")  // ◀ 로그인 페이지 GET URL
                
                // E. 로그인 처리(POST) URL
                .loginProcessingUrl("/login") // ◀ HTML 폼의 <form action="/login">
                
                // F. [수정] defaultSuccessUrl 대신, 우리가 만든 핸들러를 사용
                .successHandler(customLoginSuccessHandler) 
                
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/member/login")
                .invalidateHttpSession(true)
            );
        
        // (개발 중 편의를 위해 CSRF 비활성화)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}