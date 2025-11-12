package com.hk.emr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 1. 메시지 브로커가 /topic 으로 시작하는 주소를 구독(SUBSCRIBE)하도록 설정
        // (e.g., 의사가 /topic/doctor/1 을 구독)
        registry.enableSimpleBroker("/topic");
        
        // (참고: /app 으로 시작하는 주소는 @MessageMapping 컨트롤러로 라우팅됨)
        // registry.setApplicationDestinationPrefixes("/app"); 
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 2. JavaScript (SockJS)가 최초로 WebSocket에 연결할 엔드포인트(주소) 설정
        // (e.g., var socket = new SockJS('/ws-stomp');)
        registry.addEndpoint("/ws-stomp").withSockJS();
    }
}
