package com.hk.emr.config; 

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.MemberMapper; 

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberMapper memberMapper; 

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                        HttpServletResponse response, 
                                        Authentication authentication) throws IOException, ServletException {
        
        // 1. (중요) Security가 인증한 사용자 이름을 가져옴
        String username = authentication.getName();
        
        // 2. (중요) HTML 템플릿(Thymeleaf)이 사용할 수 있도록 'mdto'를 세션에 저장
        MemberDto mdto = memberMapper.loginUser(username);
        HttpSession session = request.getSession();
        session.setAttribute("mdto", mdto);

        // 3. 권한(Role)에 따라 리다이렉트 경로 설정
        String redirectUrl = "/";
        // ===== [디버깅 로그 추가] =====
        System.out.println("--- LoginSuccessHandler ---");
        System.out.println("사용자 '" + username + "'의 권한 목록:");
        
        for (GrantedAuthority auth : authentication.getAuthorities()) {
        	System.out.println("발견된 권한: [" + auth.getAuthority() + "]");
        	
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                redirectUrl = "/admin/";
                break;
            } else if ("ROLE_DOCTOR".equals(auth.getAuthority())) {
                redirectUrl = "/doctor/";
                break;
            } else if ("ROLE_STAFF".equals(auth.getAuthority())) {
                redirectUrl = "/staff/";
                break;
            }
        }
        
        // 4. 설정된 경로로 리다이렉트
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}