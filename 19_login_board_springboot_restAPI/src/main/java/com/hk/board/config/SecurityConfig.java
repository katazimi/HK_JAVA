package com.hk.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	authorizeRequests() : 시큐리티 처리에 HttpServletRequest를 사용한다는 것을 의미
//	requestMatchers("주소") : 지정한 주소에 대해 예외를 두어 설정
//	permitAll() : 앞에 지정한 주소를 모두에게 접근 허가
//	anyRequest().authenticated() : 다른 어떤 접근에도 보안 검사를 한다.
//	formLogin().loginPage("주소") : 로그인 페이지로 주소값으로 이동 설정
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        http.csrf(csrf -> csrf.disable()); // CSRF 비활성화
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));// CORS 설정
        http.formLogin(AbstractHttpConfigurer::disable);//loginform 비활성화
		return http.build();
	}
	
	//CORS 허용 설정
	@Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // React 개발 서버 주소
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.setAllowCredentials(true); // 쿠키 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
	
	
	//패스워드 암호화 객체
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}




